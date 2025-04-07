package com.example.Recensioner.Service;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

import com.example.Recensioner.Repository.RecensionRepository;
import com.example.Recensioner.entity.Recension;

@Service
public class RecensionService {

    private final RecensionRepository recensionRepository;

    public RecensionService(RecensionRepository recensionRepository) {
        this.recensionRepository = recensionRepository;
    }

    public Recension newRecension(Recension recension) {
        return recensionRepository.save(recension);
    }

    public List<Recension> getAllRecensions() {
        return recensionRepository.findAll();
    }

    public Recension getRecensionById(Long id) {
        return recensionRepository.findById(id).orElse(null);
    }

    public List<Recension> getRecensionByMovieId(Long movieId) {
        return recensionRepository.findByMovieId(movieId);
    }

    public List<Recension> getRecensionByUserId(Long userId) {
        return recensionRepository.findByUserId(userId);
    }

    public void deleteRecension(Long id){
        recensionRepository.deleteById(id);
    }

    public Recension updateRecension(Long id, Recension updatedRecension) {
        Recension targetRecension = getRecensionById(id);
        if(targetRecension != null){

            targetRecension = updatedRecension;
            return newRecension(targetRecension);

        }else{

            throw new EntityNotFoundException("Recension not found");
        }
    }

    /**
     * Hämta en specifik recension och tillhörande användarinformation.
     */
    /*public Mono<Map<String, Object>> getRecensionWithUser(Long recensionId) {
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
    }*/

    /**
     * Skapa en ny recension och verifiera att användaren finns.
     */
    /*public Mono<Recension> createRecension(Recension recension, Long userId) {
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
    }*/
}