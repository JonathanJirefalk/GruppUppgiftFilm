package com.example.Statistik.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "statistics")
public class Statistics {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Primärnyckel

    private String title;      // Filmens titel
    private String director;    // Filmens regissör
    private int views;         // Antal visningar
    private double rating;      // Betyg

    // Tom konstruktor för JPA
    public Statistics() {}

    public Statistics(String title, String director, int views, double rating) {
        this.title = title;
        this.director = director;
        this.views = views;
        this.rating = rating;
    }

    // Getters och Setters
    public Long getId() { return id; }
    public String getTitle() { return title; }
    public String getDirector() { return director; }
    public int getViews() { return views; }
    public double getRating() { return rating; }

    public void setViews(int views) { this.views = views; }
    public void setRating(double rating) { this.rating = rating; }
}
