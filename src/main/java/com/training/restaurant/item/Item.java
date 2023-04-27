package com.training.restaurant.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, columnDefinition = "varchar(25) default 'food'")
    private String category;

    @Column(nullable = false, columnDefinition = "varchar(25) default 'meal'")
    private String name;

    @Column(nullable = false, columnDefinition = "varchar(55) default 'house'")
    private String brand;

    private Float price;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass())
            return false;

        Item that = (Item) o;
        return Objects.equals(category, that.category) &&
                Objects.equals(name, that.name) &&
                Objects.equals(brand, that.brand) &&
                Objects.equals(price, that.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, name, brand, price);
    }

    @Override
    public String toString(){
        return "category: " + category
                + "name: " + name
                + "brand: " + brand
                + "price: " + price.toString();
    }
}
