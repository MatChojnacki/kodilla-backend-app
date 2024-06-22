package com.example.eventmanager.controller;

import com.example.eventmanager.service.TomTomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TomTomController {

    private final TomTomService tomtomService;

    @Autowired
    public TomTomController(TomTomService tomtomService) {
        this.tomtomService = tomtomService;
    }

    @GetMapping("/api/location")
    public String getLocation(@RequestParam String query) {
        return tomtomService.getLocation(query);
    }
}

