package com.example.Filmer;

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class MovieResponse {

    private Movie movie;
    private Statistics statistics;
    private List<Recension> recensioner;

    public MovieResponse(Movie movie, List<Recension> recensioner) {

        this.movie = movie;
        this.recensioner = recensioner;
    }

    public MovieResponse(Movie movie, Statistics statistics) {
        this.movie = movie;
        this.statistics = statistics;
    }

    public MovieResponse(Movie movie, Statistics statistics, List<Recension> recensioner) {
        this.movie = movie;
        this.statistics = statistics;
        this.recensioner = recensioner;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public Statistics getStatistics() {
        return statistics;
    }

    public void setStatistics(Statistics statistics) {
        this.statistics = statistics;
    }

    public List<Recension> getRecensioner() {
        return recensioner;
    }

    public void setRecensioner(List<Recension> recensioner) {
        this.recensioner = recensioner;
    }
}