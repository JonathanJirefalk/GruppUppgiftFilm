package com.example.Filmer;

import jakarta.persistence.*;

@Entity
@Table(name = "movies")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long recensionId;
    private String title;
    private String director;

    public Movie(){
    }

    /*public Movie(String title, String director, Long recensionId) {
        this.title = title;
        this.director = director;
        this.id = recensionId;
    }*/

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /*public Long getRecensionId(){
        return recensionId;
    }

    public void setRecensionId(Long recensionId){
        this.recensionId = recensionId;
    }*/

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }
}
