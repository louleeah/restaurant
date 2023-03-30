package com.training.restaurant.item;

import com.training.restaurant.ResourceNotFoundException;

import java.util.List;

public interface ItemInterface {
    Item createItem(Item item);

    Item getItem(Long id) throws ResourceNotFoundException;

    List<Item> getMenu(String category);

    void deleteItem(Long id) throws ResourceNotFoundException;
}
