package com.sparta.springhw3.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Getter
@Entity
@Table(name = "orders_detail")
public class OrdersDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_detail_id")
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id")
    private Orders order;

    @ManyToOne
    @JoinColumn(name = "food_id")
    private Food food;

    private int quantity;

    private int price;

    public OrdersDetail(Orders order, Food food, int quantity, int price) {
        this.order = order;
        this.food = food;
        this.quantity = quantity;
        this.price = price;
    }
}
