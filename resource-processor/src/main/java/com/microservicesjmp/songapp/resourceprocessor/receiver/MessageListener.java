package com.microservicesjmp.songapp.resourceprocessor.receiver;

import com.microservicesjmp.songapp.resourceprocessor.entity.Message;
import com.microservicesjmp.songapp.resourceprocessor.entity.SongMetadata;
import com.microservicesjmp.songapp.resourceprocessor.service.binary.BinarySongService;
import com.microservicesjmp.songapp.resourceprocessor.service.metadata.MetadataService;
import com.microservicesjmp.songapp.resourceprocessor.util.Mp3MetadataExtractor;
import com.microservicesjmp.songapp.resourceprocessor.util.json.JsonMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
@Slf4j
public class MessageListener extends AbstractJsonMqListener {
    private final Mp3MetadataExtractor mp3MetadataExtractor;
    private final BinarySongService binarySongService;
    private final MetadataService metadataService;

    public MessageListener(JsonMapper jsonMapper, Mp3MetadataExtractor mp3MetadataExtractor, BinarySongService binarySongService, MetadataService metadataService) {
        super(jsonMapper);
        this.mp3MetadataExtractor = mp3MetadataExtractor;
        this.binarySongService = binarySongService;
        this.metadataService = metadataService;
    }

    @Override
    protected void receiveMessage(Message message) {
        final File file = binarySongService.getBinarySong(message.url());
        final SongMetadata metadata = mp3MetadataExtractor.extract(file);
        metadataService.sendMetadata(metadata);
    }
}
