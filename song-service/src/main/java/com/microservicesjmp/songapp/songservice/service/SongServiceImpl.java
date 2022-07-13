package com.microservicesjmp.songapp.songservice.service;

import com.microservicesjmp.songapp.songservice.entity.Song;
import com.microservicesjmp.songapp.songservice.repository.SongRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SongServiceImpl implements SongService {

    private final SongRepository repository;

    @Override
    public int create(Song song) {
        return repository.save(song).getId();
    }

    @Override
    public Optional<Song> getById(int id) {
        return repository.findById(id);
    }

    @Override
    public Song update(Song song) {
        return repository.save(song);
    }

    @Override
    public void deleteById(int id) {
        repository.deleteById(id);
    }

    @Override
    public void deleteAllById(List<Integer> ids) {
        repository.deleteAllByIdInBatch(ids);
    }

    @Override
    public void deleteAllByResourceId(List<Integer> ids) {
        repository.deleteByResourceIdIn(ids);
    }

    @Override
    public boolean existsById(int id) {
        return repository.existsById(id);
    }
}
