package com.training.restaurant.order;

import com.training.restaurant.ResourceNotFoundException;
import com.training.restaurant.item.ItemInterface;
import com.training.restaurant.user.UserInterface;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class OrderTest {
    @Mock
    private OrderRepo repo;

    @InjectMocks
    private OrderService orderService;

    @Mock
    private UserInterface userService;

    @Mock
    private ItemInterface itemService;


//    @Test
//    void createOk() {
//        String userCode = "ba012345";
//        Map<Long, Integer> menuItems = new HashMap<>() {{
//            put(1L, 2);
//        }};
//
//        Order userOrder = new Order(userCode, menuItems);
//
//        User u = new User(userCode, "Ban Ana");
//        Item i = new Item(1L, "food", "meal", "house", 5F);
//        Order o = new Order(u);
//        o.addOrderItem(i, 2);
//        o.updatePrice();
//
//        when(userService.getByCode(userCode)).thenReturn(u);
//        when(itemService.getItem(1L)).thenReturn(i);
//        when(repo.save(o)).thenReturn(o);
//
//        var createdOrder = orderService.createOrder(userOrder);
//        assertThat(createdOrder, equalTo(o));
//    }

    @Test
    void getById() {
        Order o = new Order();

        when(repo.findById(1L)).thenReturn(Optional.of(o));

        assertThat(orderService.getOrder(1L), equalTo(o));
    }

    @Test
    void getByInvalidId() {
        Optional<Order> o = Optional.empty();

        when(repo.findById(1L)).thenReturn(o);

        ResourceNotFoundException exc = assertThrows("order not found", ResourceNotFoundException.class,
                () -> orderService.getOrder(1L));
        assertEquals("Order not found for id:: 1", exc.getMessage());
    }

    @Test
    void getAll() {
        Order o = new Order();
        List<Order> orderList = new ArrayList<>();
        orderList.add(o);

        when(repo.findAll()).thenReturn(orderList);

        assertThat(orderService.getOrders(), equalTo(orderList));
    }

    @Test
    void getAllEmpty() {
        List<Order> orderList = new ArrayList<>();

        when(repo.findAll()).thenReturn(orderList);

        assertThat("no orders", orderService.getOrders().isEmpty());
    }

}
