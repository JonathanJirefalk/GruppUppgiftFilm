package com.example.Recensioner.Controller;

import java.util.List;
import java.util.Map;

import com.example.Recensioner.Movie;
import com.example.Recensioner.RecensionResponse;
import com.example.Recensioner.Repository.RecensionRepository;
import com.example.Recensioner.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.history.Revision;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.Recensioner.Service.RecensionService;
import com.example.Recensioner.entity.Recension;

import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/recensioner")
public class RecensionController {

    private final RecensionService recensionService;
    private final WebClient movieClient;
    private final WebClient userClient;

    public RecensionController(RecensionService recensionService, WebClient.Builder movieClient, WebClient.Builder userClient) {
        this.recensionService = recensionService;
        this.movieClient = movieClient.baseUrl("http://localhost:8081").build();
        this.userClient = userClient.baseUrl("http://localhost:8083").build();
    }

    //Read______________________________________________________________________________________________________________

    @GetMapping
    public ResponseEntity<List<Recension>> getAllRecensions(){

        return ResponseEntity.ok(recensionService.getAllRecensions());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RecensionResponse> getRecensionById(@PathVariable Long id){

        Recension recension = recensionService.getRecensionById(id);
        Mono<User> userMono = getUser(recension.getUserId());
        Mono<Movie> movieMono = getMovie(recension.getMovieId());

        return ResponseEntity.ok(new RecensionResponse(recension, userMono.block(), movieMono.block()));
    }

    @GetMapping("/{id}/user")
    public List<Recension> getRecensionByUserId(@PathVariable Long id) {

        return recensionService.getRecensionByUserId(id);
    }

    @GetMapping("/{id}/recension")
    public List<Recension> getRecensionByMovieId(@PathVariable Long id) {

        return recensionService.getRecensionByMovieId(id);
    }

    //Create____________________________________________________________________________________________________________

    @PostMapping
    public ResponseEntity<Recension> createRecension(@RequestBody Recension recension) {

        return ResponseEntity.ok(recensionService.newRecension(recension));
    }


    //Edit______________________________________________________________________________________________________________

    @PutMapping("/{id}")
    public ResponseEntity<Recension> updateRecension(@PathVariable Long id, @RequestBody Recension newRecension) {

        return ResponseEntity.ok(recensionService.updateRecension(id, newRecension));
    }


    //Delete____________________________________________________________________________________________________________

    @DeleteMapping("/{id}")
    public void deleteRecension(@PathVariable Long id) {

        recensionService.deleteRecension(id);
    }


    private Mono<User> getUser(Long userId){

        return userClient.get().uri("/users/" + userId).retrieve().bodyToMono(User.class);
    }

    private Mono<Movie> getMovie(Long movieId){

        return movieClient.get().uri("/movie/" + movieId).retrieve().bodyToMono(Movie.class);
    }

    /**
     * Hämta en specifik recension och användarinformation kopplad till den.
     */
    /*@GetMapping("/{recensionId}/with-user")
    public Mono<ResponseEntity<Map<String, Object>>> getRecensionWithUser(@PathVariable Long recensionId) {
        return recensionService.getRecensionWithUser(recensionId)
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Skapa en recension och verifiera användaren.
     */
    /*@PostMapping
    public Mono<ResponseEntity<Recension>> skapaRecension(@RequestBody Recension recension) {
        return recensionService.createRecension(recension, recension.getUserId())
                .map(savedRecension -> new ResponseEntity<>(savedRecension, HttpStatus.CREATED))
                .onErrorResume(error -> Mono.just(new ResponseEntity<>(HttpStatus.BAD_REQUEST)));
    }*/
}