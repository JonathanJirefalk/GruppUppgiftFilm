package com.example.Statistik.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Statistik.entity.Statistics;

public interface StatisticsRepository extends JpaRepository<Statistics, Long> {
    Statistics findByTitle(String title);
}
