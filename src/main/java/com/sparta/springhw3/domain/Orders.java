package com.sparta.springhw3.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "orders")
public class Orders {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "name")
    private Restaurant restaurant;

    @Column(name = "total_price")
    private int totalPrice;

    public Orders(Restaurant restaurant) {
        this.restaurant = restaurant;
    }

    public void update(int totalPrice) {
        this.totalPrice = totalPrice;
    }
}
