package com.microservicesjmp.songapp.resourceservice.service.tracking;

import com.microservicesjmp.songapp.resourceservice.entity.ResourceTracking;
import org.springframework.lang.NonNull;

import java.util.List;
import java.util.Optional;

public interface ResourceTrackingService {

    @NonNull
    ResourceTracking save(@NonNull ResourceTracking resourceTracking);

    void delete(@NonNull ResourceTracking resourceTracking);

    @NonNull
    Optional<ResourceTracking> getById(int resourceId);

    int inQueue();

    void inQueue(int id);

    void inProgress(int id);

    void done(int id);

    void delete(int id);

    void deleteAllById(List<Integer> ids);
}
