package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.repository.MessageRepository;

public class MessageService {
    private MessageRepository messageRepository;
@Autowired
public void MessageService(MessageRepository messageRepository){
    this.messageRepository =messageRepository;
}

}

