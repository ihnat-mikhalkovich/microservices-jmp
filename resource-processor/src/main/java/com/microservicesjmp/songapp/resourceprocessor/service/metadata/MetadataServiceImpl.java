package com.microservicesjmp.songapp.resourceprocessor.service.metadata;

import com.microservicesjmp.songapp.resourceprocessor.entity.SongMetadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@RequiredArgsConstructor
@Slf4j
public class MetadataServiceImpl implements MetadataService {

    public static final String ENDPOINT = "/songs";
    private final RestTemplate restTemplate;
    @Value("${communication.song-service-root-url}")
    private String songServiceUrl;

    @Override
    public void sendMetadata(SongMetadata metadata) {
        final String songsUrl = songServiceUrl + ENDPOINT;
        final ResponseEntity<String> response = restTemplate.postForEntity(songsUrl, metadata, String.class);
        log.debug(response.toString());
    }
}
