package org.vietnamsea.authentication.config;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.vietnamsea.authentication.security.CustomAuthenticationEntryPoint;
import org.vietnamsea.authentication.security.CustomerJwtAuthenticationFilter;
import org.vietnamsea.authentication.security.PartnerJwtAuthenticationFilter;
import org.vietnamsea.authentication.security.SystemJwtAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
@RequiredArgsConstructor
public class SecurityConfig {
    @Qualifier("customAuthenticationEntryPoint")
    private final CustomAuthenticationEntryPoint customAuthenticationEntryPoint;
    private final CustomerJwtAuthenticationFilter customerJwtAuthenticationFilter;
    private final PartnerJwtAuthenticationFilter partnerJwtAuthenticationFilter;
    private final SystemJwtAuthenticationFilter systemJwtAuthenticationFilter;
    @Bean
    @Order(1)
    SecurityFilterChain authenticationFilterChain (HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests((auth) -> {
                   auth.requestMatchers("/auth/**",
                           "/registers/**",
                           "/passwords/**",
                           "/pings/**")
                           .permitAll().anyRequest().authenticated();
                }).exceptionHandling(exception -> {
                    exception.authenticationEntryPoint(customAuthenticationEntryPoint);
                })
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(this.customerJwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(this.partnerJwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(this.systemJwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
