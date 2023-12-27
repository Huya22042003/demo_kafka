package com.example.producer.controller;

import com.example.producer.entity.Users;
import com.example.producer.kafka.producer.ProducerSendMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
public class UsersController {

    @Autowired
    private ProducerSendMessage sendMessage;

    @PostMapping
    public boolean createUser(@RequestBody Users users) {
        try {
            sendMessage.sendMessage(users);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

}
