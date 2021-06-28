package com.example.patientrecord.repo;

import com.example.patientrecord.model.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRoleRepo extends JpaRepository<UserRole, Integer> {
    UserRole findByName(String name);
}
