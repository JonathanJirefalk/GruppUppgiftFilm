package com.example.Filmer;

import java.util.List;

public class MovieResponse {

    private Movie movie;
    private List<Recension> recensioner;

    public MovieResponse(Movie movie, List<Recension> recensioner) {

        this.movie = movie;
        this.recensioner = recensioner;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public List<Recension> getRecensioner() {
        return recensioner;
    }

    public void setRecensioner(List<Recension> recensioner) {
        this.recensioner = recensioner;
    }
}