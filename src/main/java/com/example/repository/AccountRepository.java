package com.example.repository;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.entity.Account;
public interface AccountRepository extends JpaRepository<Account,Integer>{
    Account findAccountByUsername(String username);
    Optional<Account> findByUsername(String username); // 31:55 in week 10 day 1 video
}
