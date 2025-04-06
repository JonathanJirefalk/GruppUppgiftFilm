package com.example.Filmer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class MovieService {

    @Autowired
    private MovieRepository movieRepository;

    final WebClient webClient;
    @Autowired
    private MovieController movieController;

    @Autowired
    public MovieService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8082").build();
    }

    public List<Movie> findRecensionByMovieID(Long movieID) {

        return movieRepository.findByMovieId(movieID);
    }

    public Mono<Map<String, Object>> getRecensionByMovie(Long movieId){
        return Mono.justOrEmpty(movieRepository.findByMovieId(movieId))
                .flatMap(movies -> webClient.get()
                        .uri("/recension/" + movieId)
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                        .map(recension -> {
                            Map<String, Object> response = Map.of(
                                    "recension", recension,
                                    "movies", movies
                            );
                            return response;
                        }));
    }


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
