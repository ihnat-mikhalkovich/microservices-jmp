package com.microservicesjmp.songapp.resourceservice.service;

import com.microservicesjmp.songapp.resourceservice.entity.DownloadedResource;
import org.springframework.web.multipart.MultipartFile;

public interface StorageService {

    String upload(MultipartFile multipartFile);

    DownloadedResource download(String id);
}
