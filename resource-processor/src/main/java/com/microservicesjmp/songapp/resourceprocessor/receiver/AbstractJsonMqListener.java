package com.microservicesjmp.songapp.resourceprocessor.receiver;

import com.microservicesjmp.songapp.resourceprocessor.entity.Message;
import com.microservicesjmp.songapp.resourceprocessor.entity.SongMetadata;
import com.microservicesjmp.songapp.resourceprocessor.util.json.JsonMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.util.Optional;

@RequiredArgsConstructor
@Slf4j
public abstract class AbstractJsonMqListener implements MqListener {

    private final JsonMapper jsonMapper;

    @Override
    public void receive(String jsonMessage) {
        final Optional<?> optMessage = jsonMapper.fromJson(jsonMessage, Message.class);
        if (optMessage.isPresent() && optMessage.get() instanceof Message message) {
            receiveMessage(message);
        } else {
            // todo make dead letter logic
            log.warn("Wrong request from rabbitmq: {}", jsonMessage);
        }
    }

    protected abstract void receiveMessage(Message message);
}
