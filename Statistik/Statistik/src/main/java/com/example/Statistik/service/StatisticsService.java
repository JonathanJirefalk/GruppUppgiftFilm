package com.example.Statistik.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Statistik.entity.Statistics;
import com.example.Statistik.repository.StatisticsRepository;

@Service
public class StatisticsService {
    @Autowired
    private StatisticsRepository statisticsRepository;

    public void addStatistics(Statistics statistics) {
        statisticsRepository.save(statistics);
    }

    public List<Statistics> getAllStatistics() {
        return statisticsRepository.findAll();
    }

    public Statistics getStatisticsByTitle(String title) {
        return statisticsRepository.findByTitle(title);
    }
}
