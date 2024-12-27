package com.woori.logincustom.Config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests((auth) -> {
            auth.requestMatchers("/").permitAll();
            auth.requestMatchers("/login", "/logout", "/regisert").permitAll();
            auth.requestMatchers("/user").hasAnyRole("USER", "ADMIN");
            auth.requestMatchers("/admin").hasAnyRole("ADMIN");
        });

        http.formLogin(login -> login
                .defaultSuccessUrl("/", true)   //로그인 성공시 메인페이지 이동
                .loginPage("/login")    //로그인폼은 사용자가 작성한 /login으로 이동
                .usernameParameter("userid")    //userid를 username에 적용
                .permitAll()
        );

        http.csrf(AbstractHttpConfigurer::disable);

        http.logout(logout -> logout
                .logoutUrl("/logout")   //로그아웃 맵핑명
                .logoutSuccessUrl("/login") //로그아웃 성공시 로그인 페이지로 이동
        );
        return http.build();
    }
}
