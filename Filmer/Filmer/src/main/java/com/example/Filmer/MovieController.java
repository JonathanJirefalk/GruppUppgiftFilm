package com.example.Filmer;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {

    private final MovieService movieService;
    private final WebClient reviewClient;

    public MovieController(MovieService movieService, WebClient.Builder reviewClientBuilder) {
        this.movieService = movieService;
        this.reviewClient = reviewClientBuilder.baseUrl("http://localhost:8082").build();
    }


    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies() {

        return ResponseEntity.ok(movieService.getAllMovies());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable Long id) {
        Movie movie = movieService.getMovieById(id);

        return ResponseEntity.ok(movie);
    }

    @GetMapping("/{id}/reviews")
    public ResponseEntity<MovieResponse> getMovieReviews(@PathVariable Long id) {
        Movie movie = movieService.getMovieById(id);
        Flux<Review> reviewFlux = getReview(id);

        return ResponseEntity.ok(new MovieResponse(movie, reviewFlux.collectList().block()));
    }

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) {

        return ResponseEntity.ok(movieService.newMovie(movie));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id, @RequestBody Movie updatedMovie) {

        return ResponseEntity.ok(movieService.updateMovie(id, updatedMovie));
    }

    @DeleteMapping("/{id}")
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }


    private Flux<Review> getReview(Long id){

        return reviewClient.get().uri("/recensioner/" + id + "/recension").retrieve().bodyToFlux(Review.class);
    }
}
