package com.microservicesjmp.songapp.resourceservice.controller;

import com.microservicesjmp.songapp.resourceservice.entity.BinaryResource;
import com.microservicesjmp.songapp.resourceservice.service.storage.TrackedStorageService;
import com.microservicesjmp.songapp.resourceservice.validation.MP3Part;
import com.microservicesjmp.songapp.resourceservice.validation.ResourceId;
import lombok.RequiredArgsConstructor;
import org.hibernate.validator.constraints.Length;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

@RestController
@RequiredArgsConstructor
@RequestMapping("/resources")
@Validated
public class ResourceController {
    private final TrackedStorageService storageService;
    public static final int MAX_IDS_CSV_LENGTH = 200;

    @PostMapping(path = "",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createResource(@MP3Part @RequestPart MultipartFile audioBinary) {
        final int id = storageService.save(audioBinary);

        final Map<String, Integer> responseMap = new HashMap<>();
        responseMap.put("id", id);
        return ResponseEntity.ok(responseMap);
    }

    @GetMapping(path = "/{id}",
            produces = "audio/mpeg")
    public ResponseEntity<Resource> getResourceById(@ResourceId @PathVariable int id) {
        final BinaryResource resource = storageService.getById(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + resource.getName())
                .contentLength(resource.getSize())
                .body(new InputStreamResource(resource.getInputStream()));
    }

    @DeleteMapping(path = "",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteResources(@Length(min = 1, max = MAX_IDS_CSV_LENGTH) @RequestParam("id") String songIdsCsv) {
        final List<Integer> ids = this.getIds(songIdsCsv);

        storageService.delete(ids);

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
