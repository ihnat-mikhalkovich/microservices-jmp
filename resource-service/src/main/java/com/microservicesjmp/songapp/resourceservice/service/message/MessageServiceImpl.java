package com.microservicesjmp.songapp.resourceservice.service.message;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservicesjmp.songapp.resourceservice.entity.Message;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class MessageServiceImpl implements MessageService {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;
    private final Queue queue;

    @Override
    public void send(Message message) {
        final Optional<String> json = this.toJson(message);

        json.ifPresent(jsonValue ->
                this.rabbitTemplate
                        .convertAndSend(queue.getName(), jsonValue)
        );
    }

    private Optional<String> toJson(Message message) {
        Optional<String> result = Optional.empty();
        try {
            result = Optional.of(objectMapper.writeValueAsString(message));
        } catch (JsonProcessingException e) {
            // todo improve exception handling
            log.warn("Mapping error.", e);
        }
        return result;
    }
}
