package com.example.Limitoffer.Controllers;

import com.example.Limitoffer.Services.CustomerAccountService;
import com.example.Limitoffer.dto.CustomerDTO;
import com.example.Limitoffer.dto.LimitOfferDTO;
import com.example.Limitoffer.entity.Account;
import com.example.Limitoffer.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/customer")
public class CustomerAccountController {

    @Autowired
    private CustomerAccountService customerAccountService;

    @Autowired
    private AccountRepository customerRepository;

    @GetMapping("/")
    public ResponseEntity<String> sayhello(){
        return ResponseEntity.status(HttpStatus.CREATED).body("hello!API is live");
    }
    @PostMapping("/create-account")
    public ResponseEntity<Account> createAccount(@RequestBody CustomerDTO customerDTO) {

            Account createdAccount = customerAccountService.createAccount(customerDTO);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<Account> getAccountDetails(@PathVariable Long accountId){
        Optional<Account> account = customerRepository.findById(accountId);

        return account.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

}
