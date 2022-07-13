package com.microservicesjmp.songapp.songservice.repository;

import com.microservicesjmp.songapp.songservice.entity.Song;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SongRepository extends JpaRepository<Song, Integer> {
    boolean deleteByResourceIdIn(List<Integer> resourceIdList);
}
