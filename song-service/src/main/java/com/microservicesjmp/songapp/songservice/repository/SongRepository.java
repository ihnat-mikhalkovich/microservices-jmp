package com.microservicesjmp.songapp.songservice.repository;

import com.microservicesjmp.songapp.songservice.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SongRepository extends JpaRepository<Song, Integer> {
}
