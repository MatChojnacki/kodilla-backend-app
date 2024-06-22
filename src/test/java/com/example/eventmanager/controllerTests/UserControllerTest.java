package com.example.eventmanager.controllerTests;

import com.example.eventmanager.model.Event;
import com.example.eventmanager.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class UserControllerTest {

    private User user;

    @BeforeEach
    void setUp() {
        user = new User(1L, "Imie", "email", "password", new HashSet<>());
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals(1L, user.getId().longValue());
        assertEquals("Imie", user.getName());
        assertEquals("email", user.getEmail());
        assertEquals("password", user.getPassword());
        assertNotNull(user.getEvents());
        assertEquals(0, user.getEvents().size());
    }

    @Test
    void testSetters() {
        Set<Event> events = new HashSet<>();
        Event event = new Event(1L, "Event1", "Location1", null, null, "Desc1", 100.0, 0, null);
        events.add(event);

        user.setId(2L);
        user.setName("Mateusz");
        user.setEmail("mateusz@email.pl");
        user.setPassword("Mateusz1!");
        user.setEvents(events);

        assertEquals(2L, user.getId().longValue());
        assertEquals("Mateusz", user.getName());
        assertEquals("mateusz@email.pl", user.getEmail());
        assertEquals("Mateusz1!", user.getPassword());
        assertEquals(1, user.getEvents().size());
        assertEquals(event, user.getEvents().iterator().next());
    }
}
