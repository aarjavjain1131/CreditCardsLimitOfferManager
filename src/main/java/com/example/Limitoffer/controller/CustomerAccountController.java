package com.example.Limitoffer.controller;

import com.example.Limitoffer.service.account.AccountService;
import com.example.Limitoffer.dto.CustomerDTO;
import com.example.Limitoffer.entity.Account;
import com.example.Limitoffer.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/customer")
public class CustomerAccountController {


    private final AccountService accountService;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    public CustomerAccountController(AccountService accountService) {
        this.accountService = accountService;
    }


    @PostMapping("/create-account")
    public ResponseEntity<Account> createAccount(@RequestBody CustomerDTO customerDTO) {

            Account createdAccount = accountService.createAccount(customerDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountDetails(@PathVariable Long accountId){

        return ResponseEntity.ok(accountService.getAccountById(accountId));
    }

}
