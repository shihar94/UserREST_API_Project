package com.example.demo.service;

import java.util.Collection;
import java.util.Collections;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);

        if (user == null)
        {
            throw new UsernameNotFoundException("User not found with "+username);
        }

        return new org.springframework.security.core.userdetails.User(
                user.getUsername(),
                user.getPassword() ,
                Collections.singletonList(new SimpleGrantedAuthority(user.getRole())));
    }
}
