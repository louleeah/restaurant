package com.training.restaurant.item;

import com.training.restaurant.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
class ItemController {
    @Autowired
    private ItemInterface service;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/menu")
    public Item createMenuItem(@RequestBody Item item) {
        return service.createItem(item);
    }

    @GetMapping("/menu/item/{id}")
    public Item getMenuItem(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        return service.getItem(id);
    }

    @GetMapping("/menu")
    public List<Item> getMenu() {
        return service.getMenu("all");
    }

    @GetMapping("/menu/{category}")
    public List<Item> getMenuByCategory(@PathVariable(value = "category") String category) {
        return service.getMenu(category);
    }

    @DeleteMapping("/menu/item/{id}")
    public void deleteMenuItem(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        service.deleteItem(id);
    }
}
