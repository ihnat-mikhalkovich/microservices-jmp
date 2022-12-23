package com.microservicesjmp.songapp.songservice;

import com.microservicesjmp.songapp.songservice.controller.SongController;
import com.microservicesjmp.songapp.songservice.entity.Song;
import com.microservicesjmp.songapp.songservice.repository.SongRepository;
import io.cucumber.core.exception.CucumberException;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

public class StepDefinitions {
    public static final String baseUrl = "http://localhost:8080";

    private final TestRestTemplate restTemplate = new TestRestTemplate();

    private ResponseEntity<Song> response;
    private ResponseEntity<Map<String, List<Integer>>> deleteResponse;
    private int lastResponseStatusCode;

    @When("the client calls GET \\/songs\\/{int}")
    public void the_client_calls_songs(Integer songId) {
        response = restTemplate.getForEntity(baseUrl + "/songs/1", Song.class);
        lastResponseStatusCode = response.getStatusCodeValue();
    }

    @Then("the client receives status code of {int}")
    public void the_client_receives_status_code_of(Integer statusCode) {
        assertEquals(statusCode, lastResponseStatusCode);
    }

    @Then("the client receives:")
    public void the_client_receives(DataTable songs) {
        final List<Map<String, String>> maps = songs.asMaps(String.class, String.class);

        final Optional<Song> expected = maps.stream().findFirst().map(map -> {
            final Song song = new Song();
            song.setId(Integer.parseInt(map.get("id")));
            song.setArtist(map.get("artist"));
            song.setName(map.get("name"));
            song.setAlbum(map.get("album"));
            song.setLength(map.get("length"));
            song.setResourceId(Integer.parseInt(map.get("resourceId")));
            song.setYear(Integer.parseInt(map.get("year")));

            return song;
        });

        if (expected.isEmpty()) {
            throw new CucumberException("song not found.");
        }

        assertEquals(expected.get(), response.getBody());
    }

    @When("the client calls DELETE \\/songs with id= {string}")
    public void the_client_calls_delete_songs(String songId) {
        final UriComponents uri = UriComponentsBuilder.fromHttpUrl(baseUrl)
                .path("songs")
                .queryParam("id", songId)
                .build();
        deleteResponse = restTemplate.exchange(uri.toUri(),
                HttpMethod.DELETE,
                null,
                new ParameterizedTypeReference<>() { });
        lastResponseStatusCode = deleteResponse.getStatusCodeValue();
    }

    @Then("the client receives {string} {string}")
    public void the_client_receives(String fieldName, String songId) {
        final Map<String, List<Integer>> map = deleteResponse.getBody();
        assertNotNull(map);
        assertTrue(map.containsKey(fieldName));
        assertEquals(songId, map.get(fieldName).stream().map(Object::toString).collect(Collectors.joining(",")));
    }
}
