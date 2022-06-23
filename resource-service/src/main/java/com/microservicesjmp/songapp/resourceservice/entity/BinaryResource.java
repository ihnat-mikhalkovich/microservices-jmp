package com.microservicesjmp.songapp.resourceservice.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BinaryResource {
    private int id;
    private String name;
    private String extension;
    private long size;
    private InputStream inputStream;
}
