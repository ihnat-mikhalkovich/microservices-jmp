package com.microservicesjmp.songapp.resourceprocessor.receiver;

import com.microservicesjmp.songapp.resourceprocessor.entity.Message;
import com.microservicesjmp.songapp.resourceprocessor.receiver.strategy.ListenerStrategy;
import com.microservicesjmp.songapp.resourceprocessor.receiver.strategy.ListenerStrategyManager;
import com.microservicesjmp.songapp.resourceprocessor.util.json.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class MessageListener extends AbstractJsonMqListener {

    private final ListenerStrategyManager strategyManager;

    public MessageListener(JsonMapper jsonMapper, ListenerStrategyManager strategyManager) {
        super(jsonMapper);
        this.strategyManager = strategyManager;
    }

    @Override
    protected void receiveMessage(Message message) {
        ListenerStrategy strategy = strategyManager.getStrategy(message);
        strategy.execute(message);
    }
}
