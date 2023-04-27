package com.training.restaurant.item;

import com.training.restaurant.ResourceNotFoundException;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class ItemTest {

    @Mock
    private ItemRepo repo;

    @InjectMocks
    private ItemService itemService;

    @Test
    void getMenu() {
        Item drink = new Item(1L, "drinks", "orange juice", "house", 2F);
        Item food = new Item(2L, "food", "lemon pie", "house", 5F);
        Item extras = new Item(3L, "extras", "napkins", "house", 0.5F);

        List<Item> menu = new ArrayList<>();
        menu.add(drink);
        menu.add(food);
        menu.add(extras);

        ArrayList<String> categories = new ArrayList<>();
        categories.add("food");
        categories.add("drinks");
        categories.add("extras");

        when(repo.getAllByCategoryIsIn(categories)).thenReturn(menu);

        assertThat(itemService.getMenu("all"), equalTo(menu));
    }

    @Test
    void getMenuOneCategory() {
        Item drink = new Item(1L, "drinks", "orange juice", "house", 2F);

        List<Item> menu = new ArrayList<>();
        menu.add(drink);

        ArrayList<String> categories = new ArrayList<>();
        categories.add("drinks");

        when(repo.getAllByCategoryIsIn(categories)).thenReturn(menu);

        assertThat(itemService.getMenu("drinks"), equalTo(menu));
    }

    @Test
    void getMenuEmpty() {
        ArrayList<String> categories = new ArrayList<>();
        categories.add("drinks");

        when(repo.getAllByCategoryIsIn(categories)).thenReturn(List.of());

        assertThat("no drinks in menu", itemService.getMenu("drinks").isEmpty());
    }

    @Test
    void getById() {
        Item drink = new Item(1L, "drinks", "orange juice", "house", 2F);

        when(repo.findById(1L)).thenReturn(Optional.of(drink));

        assertThat(itemService.getItem(1L), equalTo(drink));
    }

    @Test
    void getByInvalidId() {
        Optional<Item> item = Optional.empty();

        when(repo.findById(1L)).thenReturn(item);

        ResourceNotFoundException exc = assertThrows("item not found", ResourceNotFoundException.class,
                () -> itemService.getItem(1L));
        assertEquals("Menu item not found for id:: 1", exc.getMessage());
    }

    @Test
    void delete() {
        Item drink = new Item(1L, "drinks", "orange juice", "house", 2F);

        when(repo.findById(1L)).thenReturn(Optional.of(drink));

        itemService.deleteItem(1L);

        verify(repo, times(1)).delete(drink);
    }

    @Test
    void deleteInvalid() {
        Optional<Item> item = Optional.empty();

        when(repo.findById(1L)).thenReturn(item);

        ResourceNotFoundException exc = assertThrows("item not found", ResourceNotFoundException.class,
                () -> itemService.deleteItem(1L));
        assertEquals("Menu item not found for id:: 1", exc.getMessage());
    }
}
