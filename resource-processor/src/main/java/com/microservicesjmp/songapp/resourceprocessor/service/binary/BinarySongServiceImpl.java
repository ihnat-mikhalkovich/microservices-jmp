package com.microservicesjmp.songapp.resourceprocessor.service.binary;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.FileOutputStream;

@Service
@RequiredArgsConstructor
public class BinarySongServiceImpl implements BinarySongService {

    @Value("${communication.resource-service-root-url}")
    private String resourceServiceUrl;

    private final RestTemplate restTemplate;

    @Override
    public File getBinarySong(String relativePath) {
        final String url = resourceServiceUrl + relativePath;
        return restTemplate.execute(url, HttpMethod.GET, null, clientHttpResponse -> {
            final File ret = File.createTempFile("download", "tmp");
            StreamUtils.copy(clientHttpResponse.getBody(), new FileOutputStream(ret));
            return ret;
        });
    }
}
