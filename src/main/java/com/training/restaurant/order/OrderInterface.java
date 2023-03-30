package com.training.restaurant.order;

import com.training.restaurant.ResourceNotFoundException;

import java.util.List;
import java.util.Map;

public interface OrderInterface {
    Order createOrder(Order order) throws ResourceNotFoundException;

    Order getOrder(Long id) throws ResourceNotFoundException;

    List<Order> getOrders();

    Order updateOrderItems(Long id, Map<Long, Integer> menuItems) throws ResourceNotFoundException;

}
