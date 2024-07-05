package com.ritik.scm.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ritik.scm.services.impl.SecurityCustomUserDetailService;

@Configuration
public class SecurityConfig {
    // // user create and login using java with in memory service
    // @Bean
    // public UserDetailsService userDetailsService() {
    // UserDetails user =
    // User.withDefaultPasswordEncoder().username("ritik").password("ritik").roles("ADMIN",
    // "USER")
    // .build();
    // UserDetails user1 =
    // User.withDefaultPasswordEncoder().username("ritik1").password("ritik")
    // .roles("ADMIN", "USER").build();

    // var inMemoryUserDetailsManager = new InMemoryUserDetailsManager(user1, user);
    // return inMemoryUserDetailsManager;
    // }

    @Autowired
    private SecurityCustomUserDetailService userDetailService;

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        // object of UserDetailsServide
        daoAuthenticationProvider.setUserDetailsService(userDetailService);
        // object of PasswordEncoder
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
