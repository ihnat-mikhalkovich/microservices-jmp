package com.microservicesjmp.songapp.resourceprocessor.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpMethod;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties
public record Message(
        @JsonProperty HttpMethod method,
        @JsonProperty String url,
        @JsonProperty List<Integer> ids
) implements Serializable {}
