package com.training.restaurant.order;

import com.training.restaurant.item.Item;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        OrderItem that = (OrderItem) o;
        return Objects.equals(item, that.item) &&
                Objects.equals(order, that.order) &&
                Objects.equals(pieces, that.pieces);
    }

    @Override
    public int hashCode() {
        return Objects.hash(item, order, pieces);
    }

    @Override
    public String toString(){
        return "item: " + item.toString()
                + "pieces: " + pieces.toString();
    }

    Float getPrice() {
        return this.item.getPrice() * this.pieces;
    }
}
