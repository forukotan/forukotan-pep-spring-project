package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.service.AccountService;
import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
public AccountService accountService;
// private MessageService messageService;
@Autowired
public SocialMediaController(AccountService accountService){
    this.accountService =accountService;}
    


@PostMapping(value = "/register")
public ResponseEntity<Account> register(@RequestBody Account account) {
    Account existAccount = accountService.findAccountByUsername(account.getUsername());
    if (existAccount != null)
        return ResponseEntity.status(409).body(null);

    Account verifiedAccount = accountService.addUser(account);
    if (verifiedAccount != null) {
        return ResponseEntity.ok(verifiedAccount);
    } else {
        return ResponseEntity.status(400).body(null);
    }
    
}
}
