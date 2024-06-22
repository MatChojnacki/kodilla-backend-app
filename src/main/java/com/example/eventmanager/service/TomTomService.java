package com.example.eventmanager.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class TomTomService {

    @Value("${tomtom.api.key}")
    private String tomtomApiKey;

    private final RestTemplate restTemplate;

    public TomTomService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String getLocation(String query) {
        String endpoint = "https://api.tomtom.com/search/2/search/" + query + ".json";
        URI uri = UriComponentsBuilder.fromUriString(endpoint)
                .queryParam("key", tomtomApiKey)
                .build()
                .toUri();

        TomTomResponse response = restTemplate.getForObject(uri, TomTomResponse.class);

        if (response != null && response.getResults() != null && !response.getResults().isEmpty()) {
            return response.getResults().get(0).getPosition().toString();
        } else {
            return "Location not found";
        }
    }
}
