package com.example.Anvandare.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.Anvandare.entity.User;
import com.example.Anvandare.repository.UserRepository;

import reactor.core.publisher.Mono;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private final WebClient webClient;

    @Autowired
    public UserService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl("http://localhost:8082").build(); // RecensionService URL
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User updatedUser) {
        Optional<User> existingUserOptional = userRepository.findById(id);
        if (existingUserOptional.isPresent()) {
            User existingUser = existingUserOptional.get();
            existingUser.setUsername(updatedUser.getUsername());
            existingUser.setEmail(updatedUser.getEmail());
            return userRepository.save(existingUser);
        } else {
            return null;
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    /**
     * Hämtar recensioner för en användare från RecensionService via WebClient.
     */
    public List<Map<String, Object>> getReviewsForUser(Long userId) {
        try {
            return webClient.get()
                    .uri("/api/recensioner/user/" + userId)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, response -> {
                        System.err.println("Klientfel: " + response.statusCode());
                        return Mono.error(new RuntimeException("Klientfel vid hämtning av recensioner."));
                    })
                    .onStatus(HttpStatusCode::is5xxServerError, response -> {
                        System.err.println("Serverfel: " + response.statusCode());
                        return Mono.error(new RuntimeException("Serverfel vid hämtning av recensioner."));
                    })
                    .bodyToFlux(Map.class)
                    .map(map -> (Map<String, Object>) map) // Typkonvertering säkerställd
                    .collectList()
                    .block();
        } catch (Exception e) {
            System.err.println("Fel vid hämtning av recensioner: " + e.getMessage());
            return List.of(); // Returnera tom lista vid fel
        }
    }

    /**
     * Hämtar en specifik recension från RecensionService via WebClient.
     */
    public Map<String, Object> getReviewInfoFromReviewService(Long reviewId) {
        try {
            return webClient.get()
                    .uri("/api/recensioner/" + reviewId)
                    .retrieve()
                    .onStatus(HttpStatusCode::is4xxClientError, response -> {
                        System.err.println("Klientfel: " + response.statusCode());
                        return Mono.error(new RuntimeException("Klientfel vid hämtning av recension."));
                    })
                    .onStatus(HttpStatusCode::is5xxServerError, response -> {
                        System.err.println("Serverfel: " + response.statusCode());
                        return Mono.error(new RuntimeException("Serverfel vid hämtning av recension."));
                    })
                    .bodyToMono(Map.class)
                    .block();
        } catch (Exception e) {
            System.err.println("Fel vid hämtning av recension: " + e.getMessage());
            return null; // Returnera null vid fel
        }
    }
}