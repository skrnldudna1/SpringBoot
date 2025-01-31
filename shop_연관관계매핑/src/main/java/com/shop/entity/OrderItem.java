package com.shop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; //주문가격

    private int count; //주문수량

    private LocalDateTime regTime;

    private LocalDateTime updateTime;

    public void setItem(Item item) {
    }
}
