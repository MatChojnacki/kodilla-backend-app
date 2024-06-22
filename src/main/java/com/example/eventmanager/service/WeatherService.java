package com.example.eventmanager.service;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private final RestTemplate restTemplate = new RestTemplate();

    public String getWeather(String city) {
        String weatherApiUrl = "https://api.openweathermap.org/data/2.5/weather?q={city}&appid={apiKey}";
        String apiKey = "48d0686a042e62ac0e55bde07164634e";
        return restTemplate.getForObject(weatherApiUrl, String.class, city, apiKey);
    }
}
