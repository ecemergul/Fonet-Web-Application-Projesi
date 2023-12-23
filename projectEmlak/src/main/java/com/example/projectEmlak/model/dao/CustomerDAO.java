package com.example.projectEmlak.model.dao;

import com.example.projectEmlak.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerDAO extends JpaRepository<Customer, Long> {

    Optional<Customer> findByUsernameIgnoreCase(String username);
    Optional<Customer> findByEmailIgnoreCase(String email);

    Customer findByUsername(String username);

}
