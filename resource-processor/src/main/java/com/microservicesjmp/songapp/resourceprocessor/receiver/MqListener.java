package com.microservicesjmp.songapp.resourceprocessor.receiver;

public interface MqListener {
    void receive(String jsonMessage);
}
