package com.example.Filmer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/movies")
public class MovieController {


    private MovieRepository movieRepository;

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {
        List<Movie> movies = movieRepository.findAll();
        return new ResponseEntity<>(movies, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        Optional<Movie> movie = movieRepository.findById(id);
        return movie.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {
        if(movie.getTitle() == null || movie.getTitle().trim().isEmpty()
                || movie.getDirector() == null || movie.getDirector().trim().isEmpty()) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Movie savedMovie = movieRepository.save(movie);
        return new ResponseEntity<>(savedMovie, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updatedMovie(@PathVariable Long id, @RequestBody Movie updatedMovie) {
        Optional<Movie> existingMovieOptional = movieRepository.findById(id);
        if (existingMovieOptional.isPresent()) {

            Movie existingMovie = existingMovieOptional.get();

            if (updatedMovie.getTitle() != null && !updatedMovie.getTitle().trim().isEmpty()) {
                existingMovie.setTitle(updatedMovie.getTitle());
            }
            if (updatedMovie.getDirector() != null && !updatedMovie.getDirector().trim().isEmpty()) {
                existingMovie.setDirector(updatedMovie.getDirector());
            }

            Movie updated = movieRepository.save(existingMovie);
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Movie> deleteMovie(@PathVariable Long id) {
        if(movieRepository.existsById(id)) {
            movieRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
