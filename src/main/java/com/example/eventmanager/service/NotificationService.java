package com.example.eventmanager.service;

import com.example.eventmanager.factory.Notification;
import com.example.eventmanager.factory.NotificationFactory;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    private final NotificationFactory notificationFactory;

    public NotificationService() {
        notificationFactory = new NotificationFactory();
    }

    public void sendNotification(String type, String message) {
        Notification notification = notificationFactory.createNotification(type);
        if (notification != null) {
            notification.sendNotification(message);
        } else {
            System.out.println("Invalid notification type");
        }
    }
}
