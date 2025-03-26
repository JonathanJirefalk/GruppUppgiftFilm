package com.example.Filmer;

import java.util.List;
import java.util.Optional;

public class MovieService {

    private MovieRepository movieRepository;

    public List<Movie> getAllMovies() {

        return movieRepository.findAll();
    }

    public Optional<Movie> getMovieById(Long id) {

        return movieRepository.findById(id);
    }

    public Movie createMovie(Movie movie){
        return movieRepository.save(movie);
    }

    public void deleteMovie(Movie movie){
        movieRepository.delete(movie);
    }

    public Optional<Movie> updatedMovie(Long id, Movie updatedMovie){
        Optional<Movie> existingMovieOptional = movieRepository.findById(id);
        if(existingMovieOptional.isPresent()){
            Movie existingMovie = existingMovieOptional.get();
            existingMovie.setTitle(updatedMovie.getTitle());
            existingMovie.setDirector(updatedMovie.getDirector());

            return Optional.of(movieRepository.save(existingMovie));
        }
        return Optional.empty();
    }
}
