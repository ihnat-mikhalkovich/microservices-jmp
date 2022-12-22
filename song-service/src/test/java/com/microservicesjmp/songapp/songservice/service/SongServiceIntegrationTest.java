package com.microservicesjmp.songapp.songservice.service;

import com.microservicesjmp.songapp.songservice.entity.Song;
import com.microservicesjmp.songapp.songservice.repository.SongRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import javax.persistence.EntityManager;
import javax.sql.DataSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class SongServiceIntegrationTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private SongRepository songRepository;

    @Test
    void injectedComponentsAreNotNull(){
        assertThat(dataSource).isNotNull();
        assertThat(entityManager).isNotNull();
        assertThat(songRepository).isNotNull();
    }

    @Test
    void saveSong() {
        // given
        final Song song = new Song();
        song.setAlbum("theAlbum");
        song.setArtist("theArtist");
        song.setLength("theLength");
        song.setName("theName");
        song.setYear(2000);
        song.setResourceId(1);

        // when
        final Song savedSong = songRepository.save(song);

        //then
        assertThat(savedSong).matches(s ->
            song.getAlbum().equals(s.getAlbum())
                && song.getArtist().equals(s.getArtist())
                && song.getLength().equals(s.getLength())
        );
    }
}
