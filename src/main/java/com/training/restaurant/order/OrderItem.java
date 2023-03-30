package com.training.restaurant.order;

import com.training.restaurant.item.Item;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "order_item")
@Data
@NoArgsConstructor
class OrderItem {
    @EmbeddedId
    private OrderItemKey id;

    @ManyToOne
    @MapsId("itemId")
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne
    @MapsId("orderId")
    @JoinColumn(name = "order_id")
    private Order order;

    @Column(nullable = false)
    private Integer pieces;

    OrderItem(Item item, Order order, Integer pieces) {
        this.id = new OrderItemKey(item.getId(), order.getId());
        this.item = item;
        this.order = order;
        this.pieces = pieces;
    }

    Float GetPrice() {
        return this.item.getPrice() * this.pieces;
    }
}
