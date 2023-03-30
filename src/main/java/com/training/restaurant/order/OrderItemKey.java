package com.training.restaurant.order;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Data
@Embeddable
@NoArgsConstructor
@AllArgsConstructor
class OrderItemKey implements Serializable {
    @Column(name = "item_id")
    private Long itemId;

    @Column(name = "order_id")
    private Long orderId;
}
