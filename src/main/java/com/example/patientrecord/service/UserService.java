package com.example.patientrecord.service;

import com.example.patientrecord.model.User;
import com.example.patientrecord.model.UserRole;
import com.example.patientrecord.repo.PatientRepo;
import com.example.patientrecord.repo.UserRepo;
import com.example.patientrecord.repo.UserRoleRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRoleRepo userRoleRepo;
    private final UserRepo userRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRoleRepo userRoleRepo, UserRepo userRepo, PasswordEncoder passwordEncoder) {
        this.userRoleRepo = userRoleRepo;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
    }

    public User saveUser(User user){
        UserRole userRole = userRoleRepo.findByName("ROLE_USER");
        user.setUserRole(userRole);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepo.save(user);
    }

    public User findByUsername(String username){
        return userRepo.findByUsername(username);
    }

    public User findByUsernameAndPassword(String username, String password){
        User user = findByUsername(username);
        if(user != null){
            if(passwordEncoder.matches(password, user.getPassword())){
                return user;
            }
        }
        return null;
    }

}
