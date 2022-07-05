package com.microservicesjmp.songapp.resourceprocessor.util.json;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class JsonMapperImpl implements JsonMapper {
    private final ObjectMapper objectMapper;

    @Override
    public Optional<?> fromJson(String jsonMessage, Class<?> clazz) {
        Optional<?> result = Optional.empty();
        try {
            result = Optional.of(objectMapper.readValue(jsonMessage, clazz));
        } catch (JsonProcessingException e) {
            log.warn("Can't parse message: {}", jsonMessage);
        }
        return result;
    }
}
