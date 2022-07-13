package com.microservicesjmp.songapp.resourceprocessor.receiver.strategy;

import com.microservicesjmp.songapp.resourceprocessor.entity.Message;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ListenerStrategyManagerImpl implements ListenerStrategyManager {

    private final Map<HttpMethod, ListenerStrategy> methodListenerStrategyMap;

    public ListenerStrategyManagerImpl() {
        methodListenerStrategyMap = new HashMap<>();
    }

    public ListenerStrategyManagerImpl(Map<HttpMethod, ListenerStrategy> methodListenerStrategyMap) {
        this.methodListenerStrategyMap = methodListenerStrategyMap;
    }

    @Override
    public void register(HttpMethod method, ListenerStrategy listenerStrategy) {
        methodListenerStrategyMap.put(method, listenerStrategy);
    }

    @Override
    public ListenerStrategy getStrategy(Message message) {
        return methodListenerStrategyMap.get(message.method());
    }
}
