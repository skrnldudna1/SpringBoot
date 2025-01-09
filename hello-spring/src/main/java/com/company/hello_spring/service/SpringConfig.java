package com.company.hello_spring.service;

import com.company.hello_spring.repository.*;
import jakarta.persistence.EntityManager;
import org.hibernate.persister.entity.mutation.EntityTableMapping;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
public class SpringConfig {

//    private DataSource dataSource;
//
//    public SpringConfig(DataSource dataSource){
//        this.dataSource = dataSource;
//    }


    private EntityManager em;

    public SpringConfig(EntityManager em) {
        this.em = em;
    }

    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    @Bean
    public MemberRepository memberRepository(){
    //    return new MemoryMemberRepositoryImpl();
         // return new JdbcMemberRepository(dataSource);
        //return new JdbcTemplateMemberRepository(dataSource);
        return new JpaMemberRepository(em);
    }
}
