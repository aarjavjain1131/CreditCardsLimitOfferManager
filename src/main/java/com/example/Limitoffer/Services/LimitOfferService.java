package com.example.Limitoffer.Services;

import com.example.Limitoffer.exception.LimitOfferException;
import com.example.Limitoffer.dto.LimitOfferDTO;
import com.example.Limitoffer.entity.Account;
import com.example.Limitoffer.entity.LimitOfferDetails;
import com.example.Limitoffer.repositories.AccountRepository;
import com.example.Limitoffer.repositories.LimitOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LimitOfferService {

    private final AccountRepository accountRepository;

    private final LimitOfferRepository limitOfferRepository;

    @Autowired
    public LimitOfferService(AccountRepository accountRepository, LimitOfferRepository limitOfferRepository) {
        this.accountRepository = accountRepository;
        this.limitOfferRepository = limitOfferRepository;
    }


    public LimitOfferDetails createLimitOffer(LimitOfferDTO limitOfferDTO) {
        // Fetch the customer based on the accountId
        Optional<Account> optionalAccount = accountRepository.findById(limitOfferDTO.getAccountId());

        if(optionalAccount.isEmpty())
            throw new LimitOfferException("No account exist with the given accountId");
        Account account = optionalAccount.get();

        if(limitOfferDTO.getOfferExpiryTime()==null||limitOfferDTO.getLimitType()==null||limitOfferDTO.getNewLimit()==null)
            throw new LimitOfferException("Invalid Input: one or more field is null");

        //check if limits are more than current limits
        if((Objects.equals(limitOfferDTO.getLimitType(), "ACCOUNT_LIMIT") &&limitOfferDTO.getNewLimit()<=account.getAccountLimit())||(Objects.equals(limitOfferDTO.getLimitType(), "PER_TRANSACTION_LIMIT") &&limitOfferDTO.getNewLimit()<=account.getPerTransactionLimit()))
            throw new LimitOfferException("Invalid limits");

        //check if expiry time greater than current time and activation time
        LocalDateTime currentTime = LocalDateTime.now();

        if(limitOfferDTO.getOfferExpiryTime().isBefore(limitOfferDTO.getOfferActivationTime())||limitOfferDTO.getOfferExpiryTime().isBefore(currentTime))
            throw new LimitOfferException("Invalid offer expiry time");

        // create a limit offer entity
        LimitOfferDetails limitOfferDetails = getLimitOfferDetails(limitOfferDTO, account);

        // Save the limit offer to the database
        return limitOfferRepository.save(limitOfferDetails);
    }

    private static LimitOfferDetails getLimitOfferDetails(LimitOfferDTO limitOfferDTO, Account account) {
        LocalDateTime currentTime = LocalDateTime.now();
        LimitOfferDetails limitOfferDetails = new LimitOfferDetails();
        limitOfferDetails.setAccountId(account.getAccountId());
        limitOfferDetails.setLimitType(limitOfferDTO.getLimitType());
        limitOfferDetails.setNewLimit(limitOfferDTO.getNewLimit());

        if(limitOfferDTO.getOfferActivationTime()==null)
            limitOfferDetails.setOfferActivationTime(currentTime);
        else
            limitOfferDetails.setOfferActivationTime(limitOfferDTO.getOfferActivationTime());

        limitOfferDetails.setOfferExpiryTime(limitOfferDTO.getOfferExpiryTime());
        limitOfferDetails.setStatus("PENDING");
        return limitOfferDetails;
    }


    public List<LimitOfferDetails> getLimitOffersByAccountId(Long accountId, LocalDateTime activeDate) {

        return limitOfferRepository.findByAccountIdAndStatusAndOfferExpiryTimeGreaterThanAndOfferActivationTimeLessThan(accountId,"PENDING",activeDate,activeDate);
    }


    public LimitOfferDetails updateLimit(Long offerId, String status) {

        LocalDateTime activeDate = LocalDateTime.now();
        LimitOfferDetails limitOfferDetails = limitOfferRepository.findByOfferIdAndStatusAndOfferExpiryTimeGreaterThanAndOfferActivationTimeLessThan(offerId,"PENDING", activeDate,activeDate).orElse(null);
        if(limitOfferDetails==null)
            throw new LimitOfferException("Any active and pending offer doesn't exist with the given offerId");
        Account account = accountRepository.findById(limitOfferDetails.getAccountId()).orElse(null);
        if(account==null)
            throw new LimitOfferException("Account doesn't exist for this offerId");

        //Update limit offer status
        limitOfferDetails.setStatus(status);
        LimitOfferDetails updatedLimitOfferDetails = limitOfferRepository.save(limitOfferDetails);


        //Update account values like limits, last limits and update time if offer is accepted
        if(Objects.equals(updatedLimitOfferDetails.getStatus(), "ACCEPTED"))
        {
            System.out.println("accepted is correct");
            if(Objects.equals(limitOfferDetails.getLimitType(), "ACCOUNT_LIMIT"))
            {
                System.out.println("offer_type is account_limit");
                if(limitOfferDetails.getNewLimit()<=account.getAccountLimit())
                    throw new LimitOfferException("Account limit already greater or equal than offer limit");
                account.setLastAccountLimit(account.getAccountLimit());
                account.setAccountLimit(limitOfferDetails.getNewLimit());
                account.setAccountLimitUpdateTime(activeDate);
            }
            else{
                if(limitOfferDetails.getNewLimit()<=account.getPerTransactionLimit())
                    throw new LimitOfferException("Per Transaction limit already greater or equal than offer limit");
                account.setLastPerTransactionLimit(account.getPerTransactionLimit());
                account.setPerTransactionLimit(limitOfferDetails.getNewLimit());
                account.setPerTransactionLimitUpdateTime(activeDate);
            }
            Account updatedAccount = accountRepository.save(account);
        }


        return updatedLimitOfferDetails;
    }
}
