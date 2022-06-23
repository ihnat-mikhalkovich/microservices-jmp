package com.microservicesjmp.songapp.songservice.entity;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.*;

import java.util.Objects;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Table(name = "song_metadata")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
public class Song {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private int id;
    private String name;
    private String artist;
    private String album;
    private String length;
    @Column(name = "resource_id")
    private int resourceId;
    private int year;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Song song)) return false;
        return getId() == song.getId() && getResourceId() == song.getResourceId() && getYear() == song.getYear() && Objects.equals(getName(), song.getName()) && Objects.equals(getArtist(), song.getArtist()) && Objects.equals(getAlbum(), song.getAlbum()) && Objects.equals(getLength(), song.getLength());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
