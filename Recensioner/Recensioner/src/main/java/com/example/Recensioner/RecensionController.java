package com.example.Recensioner;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.List;

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
    public ResponseEntity<Recension> getRecensionById(@PathVariable Long id){

        return ResponseEntity.ok(recensionService.getRecensionById(id));
    }


    @GetMapping("/{id}/user")
    public List<Recension> getRecensionsByUserId(@PathVariable Long id) {

        return recensionService.getRecensionByUserId(id);
    }

    @GetMapping("/{id}/recension")
    public List<Recension> getRecensionsByMovieId(@PathVariable Long id) {

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




    //Other Functions___________________________________________________________________________________________________

    private Mono<User> getUser(Long id){

        return userClient.get().uri("/users/" + id).retrieve().bodyToMono(User.class);
    }

    private Mono<Movie> getMovie(Long id){

        return movieClient.get().uri("/movies/" + id).retrieve().bodyToMono(Movie.class);
    }
}