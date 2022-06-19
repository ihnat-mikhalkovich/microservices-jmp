package com.microservicesjmp.songapp.resourceservice.repository;

import com.microservicesjmp.songapp.resourceservice.entity.ResourceTracking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResourceRepository extends JpaRepository<ResourceTracking, Integer> {

}
