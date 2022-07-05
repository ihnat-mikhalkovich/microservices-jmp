package com.microservicesjmp.songapp.resourceservice.service.storage.impl;

import com.microservicesjmp.songapp.resourceservice.entity.BinaryResource;
import com.microservicesjmp.songapp.resourceservice.service.storage.TrackedStorageService;
import com.microservicesjmp.songapp.resourceservice.service.tracking.ResourceTrackingService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequiredArgsConstructor
public abstract class AbstractTrackedStorageService implements TrackedStorageService {

    @Getter
    private final ResourceTrackingService trackingService;

    @Override
    public int save(MultipartFile resource) {
        final int id = trackingService.inQueue();
        trackingService.inProgress(id);
        processSave(id, resource);
        trackingService.done(id);
        return id;
    }

    @Override
    public void save(int id, MultipartFile resource) {
        trackingService.inQueue(id);
        trackingService.inProgress(id);
        processSave(id, resource);
        trackingService.done(id);
    }

    @Override
    public void save(BinaryResource resource) {
        final int id = trackingService.inQueue();
        trackingService.inProgress(id);
        processSave(resource);
        trackingService.done(id);
    }

    @Override
    public BinaryResource getById(int id) {
        return processGetById(id);
    }

    @Override
    public void delete(List<Integer> ids) {
        processDelete(ids);
    }

    @Override
    public void delete(int id) {
        processDelete(id);
    }

    protected abstract void processSave(int id, MultipartFile resource);

    protected abstract void processSave(BinaryResource resource);

    protected abstract BinaryResource processGetById(int id);

    protected abstract void processDelete(List<Integer> ids);

    protected abstract void processDelete(int id);
}
