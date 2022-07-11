package com.microservicesjmp.songapp.resourceservice.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.http.HttpMethod;

import java.io.Serializable;
import java.util.List;

public record Message(
        @JsonProperty HttpMethod method,
        @JsonProperty String url
        ) implements Serializable {}
