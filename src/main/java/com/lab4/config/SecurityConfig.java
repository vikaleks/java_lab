package com.lab4.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(
//                                "/v3/api-docs/**",          // JSON-спецификация OpenAPI
//                                "/swagger-ui/**",           // Swagger UI интерфейс
//                                "/swagger-ui.html",         // Старая версия UI
//                                "/swagger-resources/**",    // Ресурсы Swagger
//                                "/webjars/**"              // JS/CSS библиотеки
//                        ).permitAll()  // Разрешить без аутентификации
//                        .requestMatchers(HttpMethod.GET, "/api/pets/**", "/api/owners/**")
//                        .hasAnyRole("USER", "ADMIN")
//                        .anyRequest().authenticated()
//                )
                .authorizeHttpRequests(auth -> auth.anyRequest().permitAll())
                .formLogin(AbstractHttpConfigurer::disable)  // Отключить форму входа
                .httpBasic(AbstractHttpConfigurer::disable); // Отключить Basic Auth

        return http.build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
