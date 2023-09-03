package com.example.Limitoffer.repositories;

import com.example.Limitoffer.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer,Long> {
}
