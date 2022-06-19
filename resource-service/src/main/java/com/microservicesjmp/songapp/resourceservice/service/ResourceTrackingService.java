package com.microservicesjmp.songapp.resourceservice.service;

import com.microservicesjmp.songapp.resourceservice.entity.ResourceTracking;

import java.util.Optional;

public interface ResourceTrackingService {
    void create(ResourceTracking resourceTracking);
    void delete(ResourceTracking resourceTracking);
    Optional<ResourceTracking> getById(int resourceId);
    ResourceTracking update(ResourceTracking tracking);
}
