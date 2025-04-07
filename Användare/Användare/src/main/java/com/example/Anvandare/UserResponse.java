package com.example.Anvandare;

import java.util.List;

public class UserResponse {

    private User user;
    private List<Recension> recensioner;

    public UserResponse(User user, List<Recension> recensioner) {

        this.user = user;
        this.recensioner = recensioner;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Recension> getRecensioner() {
        return recensioner;
    }

    public void setRecensioner(List<Recension> recensioner) {
        this.recensioner = recensioner;
    }

}
