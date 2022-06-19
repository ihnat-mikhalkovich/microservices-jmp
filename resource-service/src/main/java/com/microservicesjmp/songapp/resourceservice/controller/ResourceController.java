package com.microservicesjmp.songapp.resourceservice.controller;

import com.microservicesjmp.songapp.resourceservice.entity.ResourceTracking;
import com.microservicesjmp.songapp.resourceservice.repository.ResourceRepository;
import com.microservicesjmp.songapp.resourceservice.service.ResourceTrackingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/resources")
public class ResourceController {

//    private final ResourceRepository resourceRepository;
    private final ResourceTrackingService resourceTrackingService;

    @PostMapping(path = "",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createResource(@RequestParam MultipartFile audioBinary) {
        final Map<String, Integer> responseMap = new HashMap<>();
//        final int id = resourceRepository.save ...
        responseMap.put("id", 3);
        try {
            final byte[] bytes = audioBinary.getBytes();
            System.out.println("");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ResponseEntity.ok(responseMap);
    }

    @GetMapping(path = "/{id}",
            produces = MediaType.APPLICATION_OCTET_STREAM_VALUE)
    public ResponseEntity<byte[]> getResourceById(@PathVariable int id) {
        final byte[] bytes = new byte[10];
        bytes[0] = 3;
        return ResponseEntity.ok(bytes);
    }

    @DeleteMapping(path = "",
            produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteResources(@RequestParam("id") String songIdsCsv) {
        final Map<String, List<Integer>> responseMap = new HashMap<>();
        responseMap.put("ids", List.of(2,3,4,5));
        return ResponseEntity.ok(responseMap);
    }
}
