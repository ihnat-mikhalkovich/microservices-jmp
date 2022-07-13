package com.microservicesjmp.songapp.resourceprocessor.receiver.strategy;

import com.microservicesjmp.songapp.resourceprocessor.entity.Message;
import com.microservicesjmp.songapp.resourceprocessor.service.metadata.MetadataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class DeleteListenerStrategy implements ListenerStrategy {

    private final MetadataService metadataService;

    @Override
    public void execute(Message message) {
        metadataService.deleteMetadata(message.ids());
    }

    @Override
    public HttpMethod getMethod() {
        return HttpMethod.DELETE;
    }
}
