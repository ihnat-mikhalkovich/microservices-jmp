package com.microservicesjmp.songapp.resourceprocessor.util.json;

import java.util.Optional;

public interface JsonMapper {
    Optional<?> fromJson(String jsonMessage, Class<?> clazz);
}
