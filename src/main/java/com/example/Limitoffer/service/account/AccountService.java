package com.example.Limitoffer.service.account;

import com.example.Limitoffer.dto.CustomerDTO;
import com.example.Limitoffer.entity.Account;

public interface AccountService {

    Account createAccount(CustomerDTO customerDTO);

    Account getAccountById(Long accountId);
}
