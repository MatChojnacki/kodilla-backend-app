package com.example.eventmanager.controller;

import com.example.eventmanager.model.Category;
import com.example.eventmanager.model.Event;
import com.example.eventmanager.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Category> createCategory(@RequestBody Category category) {
        Category savedCategory = categoryService.createCategory(category);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedCategory);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category updatedCategory) {
        return categoryService.updateCategory(id, updatedCategory)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        if (categoryService.deleteCategory(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/{id}/events")
    public List<Event> getCategoryEvents(@PathVariable Long id) {
        return categoryService.getCategoryEvents(id);
    }

    @PostMapping("/{id}/events")
    public ResponseEntity<Event> addEventToCategory(@PathVariable Long id, @RequestBody Event event) {
        Event addedEvent = categoryService.addEventToCategory(id, event);
        return ResponseEntity.status(HttpStatus.CREATED).body(addedEvent);
    }

    @DeleteMapping("/{id}/events/{eventId}")
    public ResponseEntity<Void> removeEventFromCategory(@PathVariable Long id, @PathVariable Long eventId) {
        if (categoryService.removeEventFromCategory(id, eventId)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
