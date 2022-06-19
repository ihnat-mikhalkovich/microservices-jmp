package com.microservicesjmp.songapp.resourceservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "RESOURCE_TRACKING")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ResourceTracking {

    @Column(name = "resource_id")
    private int resourceId;
    private TrackingStatus status;
}
