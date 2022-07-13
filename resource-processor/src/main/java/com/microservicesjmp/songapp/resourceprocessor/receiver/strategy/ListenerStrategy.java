package com.microservicesjmp.songapp.resourceprocessor.receiver.strategy;

import com.microservicesjmp.songapp.resourceprocessor.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;

public interface ListenerStrategy {
    void execute(Message message);

    HttpMethod getMethod();

    @Autowired
    default void register(ListenerStrategyManager manager) {
        manager.register(getMethod(), this);
    }
}
