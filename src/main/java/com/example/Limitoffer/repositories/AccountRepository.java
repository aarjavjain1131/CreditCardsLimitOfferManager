package com.example.Limitoffer.repositories;

import com.example.Limitoffer.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<Account, Long> {
    Account findByAccountId(Long accountId);
}
