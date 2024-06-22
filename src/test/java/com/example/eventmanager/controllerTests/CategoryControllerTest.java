package com.example.eventmanager.controllerTests;

import com.example.eventmanager.model.Category;
import com.example.eventmanager.model.Event;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class CategoryControllerTest {

    private Category category;

    @Before
    public void setUp() {
        Set<Event> events = new HashSet<>(Arrays.asList(
                new Event(1L, "Event1", "Location1", null, null, "Desc1", 100.0, 0, null),
                new Event(2L, "Event2", "Location2", null, null, "Desc2", 150.0, 0, null)
        ));
        category = new Category(1L, "Test Category", events);
    }

    @Test
    public void testConstructorAndGetters() {
        assertEquals(1L, category.getId().longValue());
        assertEquals("Test Category", category.getName());
        assertNotNull(category.getEvents());
        assertEquals(2, category.getEvents().size());
    }

    @Test
    public void testSetters() {
        category.setId(10L);
        category.setName("New Category");
        category.setEvents(new HashSet<>(List.of(new Event(3L, "Event3", "Location3", null, null, "Desc3", 200.0, 0, null))));

        assertEquals(10L, category.getId().longValue());
        assertEquals("New Category", category.getName());
        assertEquals(1, category.getEvents().size());
    }
}
