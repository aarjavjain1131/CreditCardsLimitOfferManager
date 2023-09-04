package com.example.Limitoffer.service.account;

import com.example.Limitoffer.dto.CustomerDTO;
import com.example.Limitoffer.entity.Account;
import com.example.Limitoffer.entity.Customer;
import com.example.Limitoffer.exception.LimitOfferException;
import com.example.Limitoffer.repository.AccountRepository;
import com.example.Limitoffer.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService{

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Account createAccount(CustomerDTO customerDTO){

        //creating account with default limits as 100000 and 10000
        LocalDateTime currentTime = LocalDateTime.now();

        Customer customer = new Customer();
        System.out.println(customer.getCustomerId());
        customer.setAge(customerDTO.getAge());
        customer.setFirstname(customerDTO.getFirstname());
        customer.setLastname(customerDTO.getLastname());

        customerRepository.save(customer);

        Account account = new Account();
        account.setCustomerId(customer.getCustomerId());
        account.setAccountLimit(100000);
        account.setLastAccountLimit(0);
        account.setAccountLimitUpdateTime(currentTime);
        account.setLastPerTransactionLimit(0);
        account.setPerTransactionLimitUpdateTime(currentTime);
        account.setPerTransactionLimit(10000);


        accountRepository.save(account);


        return account;
    }

    @Override
    public Account getAccountById(Long accountId) {
        Optional<Account> optionalAccount = accountRepository.findById(accountId);
        if(optionalAccount.isEmpty())
            throw new LimitOfferException("No account exist with given Account Id");

        return optionalAccount.get();

    }
}
