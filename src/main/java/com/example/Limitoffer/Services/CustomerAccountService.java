package com.example.Limitoffer.Services;

import com.example.Limitoffer.dto.CustomerDTO;
import com.example.Limitoffer.entity.Account;
import com.example.Limitoffer.entity.Customer;
import com.example.Limitoffer.repositories.AccountRepository;
import com.example.Limitoffer.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CustomerAccountService {

    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private CustomerRepository customerRepository;

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
}
