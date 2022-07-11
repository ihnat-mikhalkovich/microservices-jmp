package com.microservicesjmp.songapp.resourceprocessor.service.binary;

import java.io.File;

public interface BinarySongService {
    File getBinarySong(String relativePath);
}
