package com.example.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Account;
import com.example.repository.AccountRepository;
@Service
@Transactional // check what this means again
public class AccountService {
    
    public AccountRepository accountRepository;
    @Autowired
    public AccountService(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }


    public Account findAccountByUsername(String username){
        return accountRepository.findAccountByUsername(username);
    }

    public Account addUser(Account account){
        String username = account.getUsername();
        String password = account.getPassword();
         if( username.isEmpty() || password.length() < 4){
         return null;}

         return accountRepository.save(account);
    }

    public Account login(Account account){
        String username = account.getUsername();
        String password = account.getPassword();
        Optional<Account> existingAccount = accountRepository.findAccountByUsername(username);

        if (existingAccount.isPresent() && existingAccount.get().getPassword().equals(password)) {
            return existingAccount.get();
        }
        return null;
    }


}
