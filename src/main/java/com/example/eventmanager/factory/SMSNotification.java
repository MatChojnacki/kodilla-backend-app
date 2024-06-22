package com.example.eventmanager.factory;

public class SMSNotification implements Notification {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending SMS notification: " + message);
    }
}