package com.ritik.scm.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.ritik.scm.repositories.UserRepository;

@Service
public class SecurityCustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // load our user
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password: " + username));
    }

}
