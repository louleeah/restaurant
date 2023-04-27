package com.training.restaurant.user;

import com.training.restaurant.order.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table( name = "users",
        uniqueConstraints = {@UniqueConstraint(columnNames = "code")}
      )
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.SEQUENCE)
    private Long id;

    @Column(nullable = false, columnDefinition = "varchar(7) default 'ba01234'")
    private String code;

    @Column(nullable = false, columnDefinition = "varchar(30) default 'Ana Ban'")
    private String name;

    @OneToMany(mappedBy = "id")
    private List<Order> orders = new ArrayList<>();

    @Override
    public String toString(){
        return "name: " + name;
    }

    public User(String code, String name) {
        this.code = code;
        this.name = name;
    }
}