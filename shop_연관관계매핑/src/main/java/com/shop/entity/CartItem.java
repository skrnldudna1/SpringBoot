package com.shop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

//중간 테이블

@Entity
@Getter
@Setter
@ToString
@Table(name="cart_item")
public class CartItem {

    @Id
    @Column(name= "cart_item_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY) //기본 자동생성(create됨) 전략 사용
    private Long id;

    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private int count;
}
