package com.microservicesjmp.songapp.resourceservice.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "resource_tracking")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class ResourceTracking {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "resource_id")
    private int resourceId;

    @Enumerated(EnumType.STRING)
    private TrackingStatus status;

    public ResourceTracking(TrackingStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ResourceTracking that)) return false;
        return getResourceId() == that.getResourceId() && getStatus() == that.getStatus();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getResourceId(), getStatus());
    }
}
