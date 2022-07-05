package com.microservicesjmp.songapp.resourceprocessor.receiver;

import com.microservicesjmp.songapp.resourceprocessor.entity.Message;
import com.microservicesjmp.songapp.resourceprocessor.entity.SongMetadata;
import com.microservicesjmp.songapp.resourceprocessor.util.Mp3MetadataExtractor;
import com.microservicesjmp.songapp.resourceprocessor.util.json.JsonMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageListener {

    @Value("${communication.song-service-root-url}")
    private String songServiceUrl;

    @Value("${communication.resource-service-root-url}")
    private String resourceServiceUrl;

    private final Mp3MetadataExtractor mp3MetadataExtractor;
    private final RestTemplate restTemplate;
    private final JsonMapper jsonMapper;

    public void receive(String jsonMessage) {
        final Optional<?> optMessage = jsonMapper.fromJson(jsonMessage, Message.class);
        if (optMessage.isPresent() && optMessage.get() instanceof Message message) {
            final File file = getFile(message);
            final SongMetadata metadata = getSongMetadata(file);
            sendMetadata(metadata);
        } else {
            // todo make dead letter logic
            log.warn("Wrong request from rabbitmq: {}", jsonMessage);
        }
    }

    private void sendMetadata(SongMetadata metadata) {
        final String songsUrl = songServiceUrl + "/songs";
        final ResponseEntity<String> response = restTemplate.postForEntity(songsUrl, metadata, String.class);
        log.debug(response.toString());
    }

    private SongMetadata getSongMetadata(File file) {
        final FileInputStream inputStream;
        try {
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        final SongMetadata metadata = mp3MetadataExtractor.extract(inputStream);
        log.debug("metadata: {}", metadata);
        return metadata;
    }

    private File getFile(Message message) {
        final String url = resourceServiceUrl + message.url();
        final File file = restTemplate.execute(url, message.method(), null, clientHttpResponse -> {
            final File ret = File.createTempFile("download", "tmp");
            StreamUtils.copy(clientHttpResponse.getBody(), new FileOutputStream(ret));
            return ret;
        });
        return file;
    }


}
