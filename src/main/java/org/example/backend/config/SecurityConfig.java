package org.example.backend.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic()

                .and()
                .authorizeHttpRequests(auth -> auth
                        .antMatchers("/").permitAll()
                        .antMatchers("/client-confirmation").hasRole("CLIENT")
                        .antMatchers("/admin-confirmation").hasRole("ADMIN")
                        .antMatchers("/clients", "/roles", "/privileges").hasAuthority("CAN_READ")
                        .antMatchers("/h2-console/**").permitAll()
                        .anyRequest().authenticated())

                .csrf().disable()                   // Allows the H2 console to open properly
                .headers().frameOptions().disable() // Allows the H2 console to render properly

                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
