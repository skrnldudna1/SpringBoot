package com.shop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Cart {

    @Id
    @Column(name= "cart_id")
    @GeneratedValue(strategy = GenerationType.AUTO) //기본 자동생성(create됨) 전략 사용
    private Long id;

    @OneToOne(fetch = FetchType.LAZY) //일대일 (1:1) 관계 설정 (한 명의 회원은 하나의 장바구니만 가질 수 있음)
    @JoinColumn(name="member_id") // member_id 컬럼을 외래키로 설정하여, Member엔티티와 연결
    private Member member;
}
