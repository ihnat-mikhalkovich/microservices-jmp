package com.microservicesjmp.songapp.resourceprocessor.util;

import com.microservicesjmp.songapp.resourceprocessor.entity.SongMetadata;

import java.io.File;
import java.io.InputStream;

public interface Mp3MetadataExtractor {
    SongMetadata extract(InputStream inputStream);

    SongMetadata extract(File file);
}
