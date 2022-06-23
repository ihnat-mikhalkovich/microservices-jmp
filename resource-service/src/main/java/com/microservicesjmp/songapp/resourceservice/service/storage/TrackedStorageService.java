package com.microservicesjmp.songapp.resourceservice.service.storage;

import com.microservicesjmp.songapp.resourceservice.service.tracking.ResourceTrackingService;
import org.springframework.web.multipart.MultipartFile;

public interface TrackedStorageService extends StorageService {
    int save(MultipartFile resource);

    ResourceTrackingService getTrackingService();
}
