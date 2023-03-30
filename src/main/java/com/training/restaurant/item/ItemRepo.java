package com.training.restaurant.item;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
interface ItemRepo extends JpaRepository<Item, Long>{
    List<Item> getAllByCategoryIsIn(ArrayList<String> categories);
}
