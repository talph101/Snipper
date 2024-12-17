package com.snipper.Snipper.Snippets.config;

import com.snipper.Snipper.Snippets.filter.JwtAuthenticationFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {
//    @Bean
//    public SecurityFilterChain securityFilterChain(){
//        return http -> {
//            http.addFilterBefore(new JwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
//        };
//    }
}
