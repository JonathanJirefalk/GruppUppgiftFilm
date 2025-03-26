package com.example.Anvandare.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Anvandare.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    // Du kan lägga till anpassade metoder här om det behövs
}