package com.example.eventmanager.controllerTests;

import com.example.eventmanager.controller.EventController;
import com.example.eventmanager.model.Event;
import com.example.eventmanager.repository.EventRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EventControllerTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventController eventController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEvents() {
        // Given
        List<Event> events = List.of(
                new Event(1L, "Event1", "Location1", null, null, "Desc1", 100.0, 0, null),
                new Event(2L, "Event2", "Location2", null, null, "Desc2", 150.0, 0, null)
        );
        when(eventRepository.findAll()).thenReturn(events);

        // When
        List<Event> result = eventController.getAllEvents();

        // Then
        assertEquals(2, result.size());
    }

    @Test
    void testGetEventById() {
        // Given
        Event event = new Event(1L, "Event1", "Location1", null, null, "Desc1", 100.0, 0, null);
        when(eventRepository.findById(1L)).thenReturn(Optional.of(event));

        // When
        ResponseEntity<Event> response = eventController.getEventById(1L);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(event, response.getBody());
    }

    @Test
    void testCreateEvent() {
        // Given
        Event event = new Event(1L, "Event1", "Location1", null, null, "Desc1", 100.0, 0, null);
        when(eventRepository.save(event)).thenReturn(event);

        // When
        ResponseEntity<Event> response = eventController.createEvent(event);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(event, response.getBody());
    }

    @Test
    void testUpdateEvent() {
        // Given
        Event existingEvent = new Event(1L, "Event1", "Location1", null, null, "Desc1", 100.0, 0, null);
        Event updatedEvent = new Event(1L, "Updated Event", "Updated Location", null, null, "Updated Desc", 200.0, 10, null);
        when(eventRepository.findById(1L)).thenReturn(Optional.of(existingEvent));
        when(eventRepository.save(existingEvent)).thenReturn(updatedEvent);

        // When
        ResponseEntity<Event> response = eventController.updateEvent(1L, updatedEvent);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedEvent, response.getBody());
    }

    @Test
    void testDeleteEvent() {
        // Given
        when(eventRepository.existsById(1L)).thenReturn(true);

        // When
        ResponseEntity<Void> response = eventController.deleteEvent(1L);

        // Then
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(eventRepository, times(1)).deleteById(1L);
    }

    @Test
    void testDeleteEventNotFound() {
        // Given
        when(eventRepository.existsById(1L)).thenReturn(false);

        // When
        ResponseEntity<Void> response = eventController.deleteEvent(1L);

        // Then
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        verify(eventRepository, never()).deleteById(1L);
    }
}
