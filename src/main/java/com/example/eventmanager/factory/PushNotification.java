package com.example.eventmanager.factory;

public class PushNotification implements Notification {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending push notification: " + message);
    }
}