package com.example.Recensioner;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

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
}