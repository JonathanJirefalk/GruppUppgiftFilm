package com.example.User;

import java.util.List;

public class UserResponse {

    private User user;
    private List<Review> recensioner;

    public UserResponse(User user, List<Review> recensioner) {

        this.user = user;
        this.recensioner = recensioner;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Review> getRecensioner() {
        return recensioner;
    }

    public void setRecensioner(List<Review> recensioner) {
        this.recensioner = recensioner;
    }

}
