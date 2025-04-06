package com.example.Recensioner.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Recensioner.Service.RecensionService;
import com.example.Recensioner.entity.Recension;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/recensioner")
public class RecensionController {

    @Autowired
    private RecensionService recensionService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Recension>> getRecensionerByUserId(@PathVariable Long userId) {
        List<Recension> recensioner = recensionService.findRecensionByUserId(userId);
        if (recensioner.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(recensioner, HttpStatus.OK);
    }

    /**
     * Hämta en specifik recension och användarinformation kopplad till den.
     */
    @GetMapping("/{recensionId}/with-user")
    public Mono<ResponseEntity<Map<String, Object>>> getRecensionWithUser(@PathVariable Long recensionId) {
        return recensionService.getRecensionWithUser(recensionId)
                .map(response -> new ResponseEntity<>(response, HttpStatus.OK))
                .defaultIfEmpty(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Skapa en recension och verifiera användaren.
     */
    @PostMapping
    public Mono<ResponseEntity<Recension>> skapaRecension(@RequestBody Recension recension) {
        return recensionService.createRecension(recension, recension.getUserId())
                .map(savedRecension -> new ResponseEntity<>(savedRecension, HttpStatus.CREATED))
                .onErrorResume(error -> Mono.just(new ResponseEntity<>(HttpStatus.BAD_REQUEST)));
    }
}