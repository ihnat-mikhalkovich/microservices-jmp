package com.microservicesjmp.songapp.resourceservice.service;

import com.microservicesjmp.songapp.resourceservice.entity.ResourceTracking;
import com.microservicesjmp.songapp.resourceservice.repository.ResourceTrackingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResourceTrackingServiceImpl implements ResourceTrackingService {

    private final ResourceTrackingRepository resourceTrackingRepository;

    @Override
    public void create(ResourceTracking resourceTracking) {
        resourceTrackingRepository.save(resourceTracking);
    }

    @Override
    public void delete(ResourceTracking resourceTracking) {
        resourceTrackingRepository.deleteById(resourceTracking.getResourceId());
    }

    @Override
    public Optional<ResourceTracking> getById(int resourceId) {
        return resourceTrackingRepository.findById((resourceId));
    }

    @Override
    public ResourceTracking update(ResourceTracking tracking) {
        return null; // todo
    }
}
