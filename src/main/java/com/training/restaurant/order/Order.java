package com.training.restaurant.order;

import com.training.restaurant.item.Item;
import com.training.restaurant.user.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
public class Order {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    private Float price;

    private Boolean paid;

    @Transient
    private String userCode;

    @Transient
    private Map<Long, Integer> menuItems;

    @ManyToOne
    @JoinColumn(name = "fk_user")
    private User user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "item")
    private Set<OrderItem> orderItems = new HashSet<>();

    Order(User user) {
        this.user = user;
    }

    void addOrderItem(Item item, Integer pieces) {
        OrderItem orderItem = new OrderItem(item, this, pieces);
        this.orderItems.add(orderItem);
    }

    void updatePrice() {
        this.price = (float) 0;
        for (OrderItem item : this.orderItems) {
            this.price += item.GetPrice();
        }
    }
}
