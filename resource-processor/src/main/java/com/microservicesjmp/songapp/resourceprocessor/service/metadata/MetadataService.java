package com.microservicesjmp.songapp.resourceprocessor.service.metadata;

import com.microservicesjmp.songapp.resourceprocessor.entity.SongMetadata;

import java.util.List;

public interface MetadataService {
    void sendMetadata(SongMetadata songMetadata);

    void deleteMetadata(List<Integer> ids);
}