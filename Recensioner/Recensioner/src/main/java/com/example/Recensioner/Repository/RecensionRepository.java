package com.example.Recensioner.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Recensioner.entity.Recension;

public interface RecensionRepository extends JpaRepository<Recension, Long> {
    List<Recension> findByUserId(Long userId);
}