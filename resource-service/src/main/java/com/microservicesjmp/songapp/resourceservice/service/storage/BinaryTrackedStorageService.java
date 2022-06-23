package com.microservicesjmp.songapp.resourceservice.service.storage;

import com.microservicesjmp.songapp.resourceservice.entity.BinaryResource;
import com.microservicesjmp.songapp.resourceservice.service.tracking.ResourceTrackingService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class BinaryTrackedStorageService extends AbstractTrackedStorageService {

    private final BinaryStorageService storageService;

    public BinaryTrackedStorageService(ResourceTrackingService trackingService, BinaryStorageService storageService) {
        super(trackingService);
        this.storageService = storageService;
    }

    @Override
    protected void processSave(int id, MultipartFile resource) {
        storageService.save(id, resource);
    }

    @Override
    protected void processSave(BinaryResource resource) {
        storageService.save(resource);
    }

    @Override
    protected BinaryResource processGetById(int id) {
        return storageService.getById(id);
    }

    @Override
    protected void processDelete(List<Integer> ids) {
        storageService.delete(ids);
    }

    @Override
    protected void processDelete(int id) {
        storageService.delete(id);
    }

    @Override
    public boolean isResourceExist(int id) {
        return storageService.isResourceExist(id);
    }
}
