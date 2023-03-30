package com.training.restaurant.order;

import com.training.restaurant.ResourceNotFoundException;
import com.training.restaurant.item.Item;
import com.training.restaurant.item.ItemInterface;
import com.training.restaurant.user.User;
import com.training.restaurant.user.UserInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
class OrderService implements OrderInterface{
    @Autowired
    private OrderRepo repo;

    @Autowired
    private UserInterface userInterface;

    @Autowired
    private ItemInterface itemInterface;

    public Order createOrder(Order userOrder) throws ResourceNotFoundException {
        String userCode = userOrder.getUserCode();
        User user = userInterface.getByCode(userCode);
        Order order = new Order(user);

        for (Map.Entry<Long, Integer> menuItem : userOrder.getMenuItems().entrySet()) {
            Item item = itemInterface.getItem(menuItem.getKey());
            order.addOrderItem(item, menuItem.getValue());
        }

        order.updatePrice();

        return repo.save(order);
    }

    public Order getOrder (Long id) throws ResourceNotFoundException {
        return repo.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Order not found for id::" + id));
    }

    public List<Order> getOrders () {
        return repo.findAll();
    }

    public Order updateOrderItems(Long id, Map<Long, Integer> menuItems) throws ResourceNotFoundException {
        Order order = repo.getReferenceById(id);

        order.setOrderItems(null);
        for (Map.Entry<Long, Integer> menuItem : menuItems.entrySet()) {
            Item item = itemInterface.getItem(menuItem.getKey());
            order.addOrderItem(item, menuItem.getValue());
        }

        order.updatePrice();

        return repo.save(order);
    }
}
