package com.microservicesjmp.songapp.songservice.service;

import com.microservicesjmp.songapp.songservice.entity.Song;
import com.microservicesjmp.songapp.songservice.repository.SongRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class SongServiceImplTest {

    @Test
    void create() {
        // given
        final SongRepository songRepositoryMock = Mockito.mock(SongRepository.class);
        final SongServiceImpl songService = new SongServiceImpl(songRepositoryMock);

        final Song song = new Song();
        song.setAlbum("theAlbum");
        song.setArtist("theArtist");
        song.setLength("theLength");
        song.setName("theName");
        song.setYear(2000);
        song.setResourceId(1);

        final Song returnSong = new Song();
        final int expectedId = 101;
        returnSong.setId(expectedId);
        Mockito.when(songRepositoryMock.save(song)).thenReturn(returnSong);

        // when
        final int id = songService.create(song);

        // then
        assertEquals(expectedId, id);
        Mockito.verify(songRepositoryMock).save(song);
    }
}