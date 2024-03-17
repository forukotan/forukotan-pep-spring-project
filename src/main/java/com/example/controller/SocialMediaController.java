package com.example.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.entity.Message;
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
public MessageService messageService;
@Autowired
public SocialMediaController(AccountService accountService, MessageService messageService){
    this.accountService =accountService;
    this.messageService = messageService;
}
    


@PostMapping(value = "/register")
public ResponseEntity<Account> register(@RequestBody Account account) {
    Account existAccount = accountService.findAccountByUsername(account.getUsername());
    if (existAccount != null)
        return ResponseEntity.status(409).body(null);// bo said to do this, dont understand this stuff yet

    Account verifiedAccount = accountService.addUser(account);
    if (verifiedAccount != null) {
        return ResponseEntity.ok(verifiedAccount);
    } else {
        return ResponseEntity.status(400).body(null);
    }
}
@PostMapping(value = "/login")
public ResponseEntity<Account> login(@RequestBody Account account){
    Account loginUser =accountService.login(account);

    if(loginUser!= null)
    {
        return ResponseEntity.ok(loginUser);
    }
    else
    {
        return ResponseEntity.status(401).body(null);
    }
}

@PostMapping(value = "/messages")
public ResponseEntity<Message> newMessage(@RequestBody Message message){
Message newMessaged =messageService.newMessage(message);

if(newMessaged!= null)
{
    return ResponseEntity.ok(newMessaged);
}
else
{
    return ResponseEntity.status(400).body(null);
}
}

@GetMapping(value = "/messages")
public ResponseEntity<List<Message>> getAllMessages() {
    List<Message> messages = messageService.getAllMessages();
    return ResponseEntity.ok(messages);
}

@GetMapping(value = "/messages/{message_id}")
public ResponseEntity<Message> getMessageById(@PathVariable Integer message_id) {
    Optional<Message> message = messageService.getMessageById(message_id);
    return ResponseEntity.ok(message.orElse(null));
}

@DeleteMapping(value = "/messages/{message_id}")
public ResponseEntity<?> deleteMessage(@PathVariable Integer message_id) {
    boolean wasDeleted = messageService.deleteMessage(message_id);

    if (wasDeleted) {
        return ResponseEntity.ok().body(1); // intellij put a 1 instead of empty so google that
    } else {
        return ResponseEntity.ok().body(null); 
    }
}

}
