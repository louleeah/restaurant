package com.training.restaurant.order;

import com.training.restaurant.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
class OrderController {
    @Autowired
    private OrderService service;

    @PostMapping("/orders")
    public Order createOrder(@RequestBody Order order) throws ResourceNotFoundException {
        return service.createOrder(order);
    }

    @GetMapping("/orders/{id}")
    public Order getOrder(@PathVariable(value = "id") Long id) throws ResourceNotFoundException{
        return service.getOrder(id);
    }

    @GetMapping("/orders")
    public List<Order> getOrders() {
        return service.getOrders();
    }

    @PatchMapping("/orders/{id}/items")
    public Order updateOrderItems(@PathVariable(value = "id") Long id,
                                  @RequestBody Map<Long, Integer> items)
            throws ResourceNotFoundException {
        return service.updateOrderItems(id, items);
    }
}
