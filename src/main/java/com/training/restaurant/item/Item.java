package com.training.restaurant.item;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "item")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy=GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, columnDefinition = "varchar(255) default 'food'")
    private String category;

    @Column(nullable = false, columnDefinition = "varchar(255) default 'meal'")
    private String name;

    @Column(nullable = false, columnDefinition = "varchar(255) default 'house'")
    private String brand;

    private Float price;
}
