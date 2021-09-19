package com.billy.testdemo.config;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<SimpleGrantedAuthority> roles = null;
        if(username.equals("admin"))
        {
            roles = List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
            return new User("admin","$2a$10$.ONLDYMHX6E6X.rfXOP9/ODLDczcnEmUZiZ7LFDZRjqypc/.g4s.W",roles);
        }
        if(username.equals("user"))
        {
            roles = List.of(new SimpleGrantedAuthority("ROLE_USER"));
            return new User("user","$2a$10$wxVwv05XwvbCraMrZAlWO.CxV9TXskcmqEWSyp9htiXlyV2lLRBBi",roles);
        }
        throw new UsernameNotFoundException("user not found with this " + username + " username");
    }
}
