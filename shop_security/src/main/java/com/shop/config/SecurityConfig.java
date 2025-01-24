package com.shop.config;

import com.shop.service.MemberService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.FormLoginConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {


    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        // 폼 로그인 설정
        http.formLogin(form -> form
                .loginPage("/members/login") // 커스텀 로그인 페이지 경로 설정
                .defaultSuccessUrl("/", true) // 로그인 성공 시 리다이렉트할 기본 경로 설정
                .failureUrl("/members/login/error") // 로그인 실패 시 이동할 경로 설정
                .usernameParameter("email") // 로그인 시 사용자의 아이디로 사용할 파라미터 이름 설정
                .passwordParameter("password") // 로그인 시 사용자의 비밀번호로 사용할 파라미터 이름 설정
                .permitAll()); // 로그인 페이지에 모든 사용자가 접근할 수 있도록 허용

        // URL 접근 권한 설정
        http.authorizeHttpRequests(request -> request
                .requestMatchers("/css/**").permitAll() // CSS 파일 경로에 대한 접근을 모두 허용
                .requestMatchers("/", "/member/**").permitAll() // 루트 경로와 '/member/'로 시작하는 경로에 대한 접근을 모두 허용
                .anyRequest().authenticated()); // 그 외의 모든 요청은 인증된 사용자만 접근 가능

        http.logout(Customizer.withDefaults());

        return http.build(); // 설정을 기반으로 SecurityFilterChain 객체를 생성하여 반환
    }


    @Bean
    public PasswordEncoder passwordEncoder()   {
        return new BCryptPasswordEncoder();
    }
}
