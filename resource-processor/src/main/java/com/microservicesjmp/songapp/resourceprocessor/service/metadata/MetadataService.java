package com.microservicesjmp.songapp.resourceprocessor.service.metadata;

import com.microservicesjmp.songapp.resourceprocessor.entity.SongMetadata;

public interface MetadataService {
    void sendMetadata(SongMetadata songMetadata);
}