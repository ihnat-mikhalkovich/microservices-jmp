package com.microservicesjmp.songapp.resourceservice.service.message;

import com.microservicesjmp.songapp.resourceservice.entity.Message;

public interface MessageService {
    void send(Message message);
}
