package com.training.restaurant.order;

import com.training.restaurant.item.Item;
import com.training.restaurant.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    private Float price;

    @Transient
    private String userCode;

    @Transient
    private Map<Long, Integer> menuItems;

    @ManyToOne
    @JoinColumn(name = "fk_user_id")
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    Order(User user) {
        this.user = user;
    }

    Order(String userCode, Map<Long, Integer> menuItems) {
        this.userCode = userCode;
        this.menuItems = menuItems;
    }

    void addOrderItem(Item item, Integer pieces) {
        var orderItem = new OrderItem(item, this, pieces);
        orderItems.add(orderItem);
    }

    void removeOrderItem(Item item) {
        for (Iterator<OrderItem> iterator = orderItems.iterator();
             iterator.hasNext(); ) {
            OrderItem orderItem = iterator.next();

            if (orderItem.getOrder().equals(this) &&
                    orderItem.getItem().equals(item)) {
                iterator.remove();
                orderItem.setOrder(null);
                orderItem.setItem(null);
            }
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        return id != null && id.equals(((Order) o).getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString(){
        return "price: " + Objects.toString(price, "")
                + "user: " + Objects.toString(user, "")
                + "items: " + orderItems.toString();
    }

    void updatePrice() {
        this.price = (float) 0;
        for (OrderItem item : this.orderItems) {
            this.price += item.getPrice();
        }
    }
}
