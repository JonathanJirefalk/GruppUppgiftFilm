package com.example.Statistik.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Statistik.entity.Statistics;
import com.example.Statistik.service.StatisticsService;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {
    @Autowired
    private StatisticsService statisticsService;

    @PostMapping
    public void addStatistics(@RequestBody Statistics statistics) {
        statisticsService.addStatistics(statistics);
    }

    @GetMapping
    public List<Statistics> getAllStatistics() {
        return statisticsService.getAllStatistics();
    }

    @GetMapping("/{title}")
    public Statistics getStatisticsByTitle(@PathVariable String title) {
        return statisticsService.getStatisticsByTitle(title);
    }
}
