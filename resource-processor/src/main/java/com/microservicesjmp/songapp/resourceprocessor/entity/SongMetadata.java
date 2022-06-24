package com.microservicesjmp.songapp.resourceprocessor.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SongMetadata {
    private String name;
    private String artist;
    private String album;
    private String length;
    private int resourceId;
    private int year;
}
