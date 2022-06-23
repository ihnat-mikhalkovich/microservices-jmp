package com.microservicesjmp.songapp.songservice.controller;

import com.microservicesjmp.songapp.songservice.entity.Song;
import com.microservicesjmp.songapp.songservice.service.SongService;
import com.microservicesjmp.songapp.songservice.validation.SongId;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

@RestController
@RequestMapping("/songs")
@RequiredArgsConstructor
@Validated
public class SongController {
    public static final int MAX_IDS_CSV_LENGTH = 200;
    private final SongService songService;

    @PostMapping(path = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createSong(@RequestBody Song song) {
        final int id = songService.create(song);

        final Map<String, Integer> responseBody = new HashMap<>();
        responseBody.put("id", id);

        return ResponseEntity.ok(responseBody);
    }

    @GetMapping(path = "/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getSongById(@SongId @PathVariable int id) {
        final Optional<Song> song = songService.getById(id);
        return ResponseEntity.ok(song);
    }

    @DeleteMapping(path = "",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteSongsByIds(@Length(min = 1, max = MAX_IDS_CSV_LENGTH) @RequestParam("id") String songIdsCsv) {
        final List<Integer> ids = this.getIds(songIdsCsv);

        songService.deleteByIds(ids);

        final Map<String, List<Integer>> responseMap = new HashMap<>();
        responseMap.put("ids", ids);
        return ResponseEntity.ok(responseMap);
    }

    private List<Integer> getIds(String idsCsv) {
        return Stream.of(idsCsv.split(","))
                .map(Integer::parseInt)
                .toList();
    }
}
