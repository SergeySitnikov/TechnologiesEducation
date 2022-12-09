package ru.education.technologiesEducation.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    public static final String SIGN_UP_URL = "/api/v1/auth/registration";
    public static final String SIGN_IN_URL = "/api/v1/auth/login";


    @Bean
    public SecurityFilterChain configure(HttpSecurity http) throws Exception {
        http
                .httpBasic().disable()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeHttpRequests()
                .requestMatchers(SIGN_UP_URL).permitAll()
                .requestMatchers(SIGN_IN_URL).permitAll()
                .anyRequest().authenticated();
        return http.build();

    }
}
