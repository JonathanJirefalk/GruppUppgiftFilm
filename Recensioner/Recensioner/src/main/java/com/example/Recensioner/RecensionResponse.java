package com.example.Recensioner;

import com.example.Recensioner.entity.Recension;

public class RecensionResponse {

    private Recension recension;
    private User user;
    private Movie movie;

    public RecensionResponse(Recension recension, User user, Movie movie) {
        this.recension = recension;
        this.user = user;
        this.movie = movie;
    }

    public Recension getRecension() {
        return recension;
    }

    public void setRecension(Recension recension) {
        this.recension = recension;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }
}
