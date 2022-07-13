package com.microservicesjmp.songapp.resourceprocessor.receiver.strategy;

import com.microservicesjmp.songapp.resourceprocessor.entity.Message;
import org.springframework.http.HttpMethod;

public interface ListenerStrategyManager {
    void register(HttpMethod method, ListenerStrategy listenerStrategy);

    ListenerStrategy getStrategy(Message message);
}
