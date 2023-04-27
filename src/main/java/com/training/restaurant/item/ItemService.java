package com.training.restaurant.item;

import com.training.restaurant.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
class ItemService implements ItemInterface {
    @Autowired
    private ItemRepo repo;

    public List<Item> getMenu(String category) {
        ArrayList<String> categories = new ArrayList<>();
        if (category.equals("all")) {
            categories.add("food");
            categories.add("drinks");
            categories.add("extras");
        } else {
            categories.add(category);
        }

        return repo.getAllByCategoryIsIn(categories);
    }

    public Item createItem(Item item) {
        return repo.save(item);
    }

    public Item getItem(Long id) throws ResourceNotFoundException{
        return repo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Menu item not found for id:: " + id));
    }

    public void deleteItem(Long id) throws ResourceNotFoundException {
        var item = repo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Menu item not found for id:: " + id));

        repo.delete(item);
    }
}
