package com.example.eventmanager.controller;

import com.example.eventmanager.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    @Autowired
    public NotificationService notificationService;

    @PostMapping
    public void sendNotification(@RequestParam String type, @RequestParam String message) {
        notificationService.sendNotification(type, message);
    }
}
