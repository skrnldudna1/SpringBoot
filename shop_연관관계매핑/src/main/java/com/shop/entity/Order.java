package com.shop.entity;

import com.querydsl.core.types.OrderSpecifier;
import com.shop.constant.OrderStatus;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@ToString
@Table(name = "orders")
public class Order {
    
    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;
    
    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
    
    private LocalDateTime orderDate; //주문일
    
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus; //주문상태

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)//양방향 관계 설정

    private List<OrderItem>orderItems = new ArrayList<>();

    private LocalDateTime regTime;

    private LocalDateTime updateTime;

}
