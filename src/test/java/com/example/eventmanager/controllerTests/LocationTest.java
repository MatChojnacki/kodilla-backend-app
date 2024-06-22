package com.example.eventmanager.controllerTests;

import com.example.eventmanager.model.Location;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LocationTest {

    private Location location;

    @BeforeEach
    void setUp() {
        location = new Location(1L, "Location1", "Address1");
    }

    @Test
    void testConstructorAndGetters() {
        assertEquals(1L, location.getId().longValue());
        assertEquals("Location1", location.getName());
        assertEquals("Address1", location.getAddress());
    }

    @Test
    void testSetters() {
        location.setId(2L);
        location.setName("NewLocation");
        location.setAddress("NewAddress");

        assertEquals(2L, location.getId().longValue());
        assertEquals("NewLocation", location.getName());
        assertEquals("NewAddress", location.getAddress());
    }
}
