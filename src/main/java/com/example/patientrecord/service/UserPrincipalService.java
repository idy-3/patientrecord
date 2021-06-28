package com.example.patientrecord.service;

import com.example.patientrecord.model.User;
import com.example.patientrecord.config.UserPrincipal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class UserPrincipalService implements UserDetailsService {


    private final UserService userService;

    @Autowired
    public UserPrincipalService(@Lazy UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserPrincipal loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userService.findByUsername(s);
        return UserPrincipal.fromUserToUserPrincipal(user);
    }
}
