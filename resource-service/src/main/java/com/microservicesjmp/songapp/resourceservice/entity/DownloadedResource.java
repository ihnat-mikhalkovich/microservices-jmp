package com.microservicesjmp.songapp.resourceservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DownloadedResource {
    private String id;
    private String fileName;
    private Long contentLength;
    private InputStream inputStream;
}
