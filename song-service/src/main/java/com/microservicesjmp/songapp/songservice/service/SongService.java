package com.microservicesjmp.songapp.songservice.service;

import com.microservicesjmp.songapp.songservice.entity.Song;

import java.util.List;
import java.util.Optional;

public interface SongService {
    int create(Song song);
    Optional<Song> getById(int id);
    Song update(Song song);
    void deleteById(int id);
    void deleteByIds(List<Integer> ids);
    boolean existsById(int id);
}
