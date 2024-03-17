package com.example.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.Message;
import com.example.repository.MessageRepository;
@Service
@Transactional
public class MessageService {
public  MessageRepository messageRepository;

@Autowired
public  MessageService(MessageRepository messageRepository){
    this.messageRepository =messageRepository;
}

public Message newMessage(Message message){
    int posted_by = message.getPosted_by();
    String message_text =message.getMessage_text();
    Long time_posted_epoch = message.getTime_posted_epoch();

    if (message_text== null|| message_text.trim().isEmpty())
    {
        return null;
    }
    if (message_text.length() > 255)
    {
        return null;
    }
    if(!messageRepository.existsById(posted_by)) // just seeing if works out side the repo
    {
        return null;
    }
    return messageRepository.save(message);




}

public List<Message> getAllMessages() {
    return messageRepository.findAll();
}

public Optional<Message> getMessageById(int messageid){
    return messageRepository.findById(messageid);
}

public boolean deleteMessage(int messageid){
    if(messageRepository.existsById(messageid)){
        messageRepository.deleteById(messageid);
        return true;
    }
    return false;
}

public 



}

