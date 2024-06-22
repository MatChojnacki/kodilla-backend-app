package com.example.eventmanager.service;

import com.example.eventmanager.model.Category;
import com.example.eventmanager.model.Event;
import com.example.eventmanager.repository.CategoryRepository;
import com.example.eventmanager.repository.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private EventRepository eventRepository;

    public List<Category> getAllCategories() {
        return (List<Category>) categoryRepository.findAll();
    }

    public Optional<Category> getCategoryById(Long id) {
        return categoryRepository.findById(id);
    }

    public Category createCategory(Category category) {
        return categoryRepository.save(category);
    }

    public Optional<Category> updateCategory(Long id, Category updatedCategory) {
        return categoryRepository.findById(id)
                .map(category -> {
                    category.setName(updatedCategory.getName());
                    return categoryRepository.save(category);
                });
    }

    public boolean deleteCategory(Long id) {
        if (categoryRepository.existsById(id)) {
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<Event> getCategoryEvents(Long id) {
        return eventRepository.findByCategoryId(id);
    }

    public Event addEventToCategory(Long categoryId, Event event) {
        return categoryRepository.findById(categoryId).map(category -> {
            event.setCategory(category);
            return eventRepository.save(event);
        }).orElse(null);
    }

    public boolean removeEventFromCategory(Long categoryId, Long eventId) {
        return eventRepository.findById(eventId).map(event -> {
            if (event.getCategory() != null && event.getCategory().getId().equals(categoryId)) {
                event.setCategory(null);
                eventRepository.save(event);
                return true;
            }
            return false;
        }).orElse(false);
    }
}
