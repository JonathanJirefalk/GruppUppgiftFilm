package com.example.Recensioner.Service;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.Recensioner.Repository.RecensionRepository;
import com.example.Recensioner.entity.Recension;

import reactor.core.publisher.Mono;

@Service
public class RecensionService {

    @Autowired
    private RecensionRepository recensionRepository;

    private final WebClient webClient;

    @Autowired
    public RecensionService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8083").build();
    }

    public List<Recension> findRecensionByUserId(Long userId) {
        return recensionRepository.findByUserId(userId);
    }

    /**
     * Hämta en specifik recension och tillhörande användarinformation.
     */
    public Mono<Map<String, Object>> getRecensionWithUser(Long recensionId) {
        return Mono.justOrEmpty(recensionRepository.findById(recensionId))
                .flatMap(recension -> webClient.get()
                        .uri("/users/" + recension.getUserId()) // Hämta användardata
                        .retrieve()
                        .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                        .map(user -> {
                            Map<String, Object> response = Map.of(
                                    "recension", recension,
                                    "user", user
                            );
                            return response;
                        }));
    }

    /**
     * Skapa en ny recension och verifiera att användaren finns.
     */
    public Mono<Recension> createRecension(Recension recension, Long userId) {
        return webClient.get()
                .uri("/users/" + userId)
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<Map<String, Object>>() {})
                .flatMap(user -> {
                    if (user == null) {
                        return Mono.error(new RuntimeException("Användare med ID " + userId + " hittades inte."));
                    }
                    recension.setUserId(userId);
                    return Mono.just(recensionRepository.save(recension));
                });
    }
}