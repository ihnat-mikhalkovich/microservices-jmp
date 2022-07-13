package com.microservicesjmp.songapp.resourceservice.service.tracking;

import com.microservicesjmp.songapp.resourceservice.entity.ResourceTracking;
import com.microservicesjmp.songapp.resourceservice.entity.TrackingStatus;
import com.microservicesjmp.songapp.resourceservice.repository.ResourceTrackingRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ResourceTrackingServiceImpl implements ResourceTrackingService {

    private final ResourceTrackingRepository resourceTrackingRepository;

    @Override
    public ResourceTracking save(ResourceTracking resourceTracking) {
        return resourceTrackingRepository.save(resourceTracking);
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
    public int inQueue() {
        final ResourceTracking resourceTracking = new ResourceTracking(TrackingStatus.IN_QUEUE);
        return save(resourceTracking).getResourceId();
    }

    @Override
    public void inQueue(int id) {
        final ResourceTracking resourceTracking = new ResourceTracking(id, TrackingStatus.IN_QUEUE);
        save(resourceTracking);
    }

    @Override
    public void inProgress(int id) {
        final ResourceTracking resourceTracking = new ResourceTracking(id, TrackingStatus.IN_PROGRESS);
        save(resourceTracking);
    }

    @Override
    public void done(int id) {
        final ResourceTracking resourceTracking = new ResourceTracking(id, TrackingStatus.DONE);
        save(resourceTracking);
    }

    @Override
    public void delete(int id) {
        resourceTrackingRepository.deleteById(id);
    }

    @Override
    public void deleteAllById(List<Integer> ids) {
        resourceTrackingRepository.deleteAllByIdInBatch(ids);
    }
}
