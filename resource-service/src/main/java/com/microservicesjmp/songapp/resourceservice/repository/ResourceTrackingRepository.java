package com.microservicesjmp.songapp.resourceservice.repository;

import com.microservicesjmp.songapp.resourceservice.entity.ResourceTracking;
import org.springframework.data.repository.CrudRepository;

public interface ResourceTrackingRepository extends CrudRepository<ResourceTracking, Integer> {

}
