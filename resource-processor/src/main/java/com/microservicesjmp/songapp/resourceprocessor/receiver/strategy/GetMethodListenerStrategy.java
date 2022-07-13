package com.microservicesjmp.songapp.resourceprocessor.receiver.strategy;

import com.microservicesjmp.songapp.resourceprocessor.entity.Message;
import com.microservicesjmp.songapp.resourceprocessor.entity.SongMetadata;
import com.microservicesjmp.songapp.resourceprocessor.service.binary.BinarySongService;
import com.microservicesjmp.songapp.resourceprocessor.service.metadata.MetadataService;
import com.microservicesjmp.songapp.resourceprocessor.util.Mp3MetadataExtractor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@RequiredArgsConstructor
public class GetMethodListenerStrategy implements ListenerStrategy {

    private final Mp3MetadataExtractor mp3MetadataExtractor;
    private final BinarySongService binarySongService;
    private final MetadataService metadataService;

    @Override
    public void execute(Message message) {
        final File file = binarySongService.getBinarySong(message.url());
        final SongMetadata metadata = mp3MetadataExtractor.extract(file);
        metadata.setResourceId(message.ids().get(0));
        metadataService.sendMetadata(metadata);
    }

    @Override
    public HttpMethod getMethod() {
        return HttpMethod.GET;
    }
}
