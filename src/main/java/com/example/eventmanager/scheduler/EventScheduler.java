package com.example.eventmanager.scheduler;

import com.example.eventmanager.model.Event;
import com.example.eventmanager.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class EventScheduler {

    private final EventRepository eventRepository;

    @Autowired
    public EventScheduler(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Scheduled(fixedRate = 60000) // Uruchamianie co minutę (60000 ms)
    public void countAttendeesForEvents() {
        List<Event> events = (List<Event>) eventRepository.findAll();
        for (Event event : events) {
            int count = (int) (Math.random() * 100); // Symulacja losowej liczby zgłoszeń
            event.setRegisteredAttendees(count);
            eventRepository.save(event);
            System.out.println("Updated number of attendees for event " + event.getId() + ": " + count);
        }
    }
}
