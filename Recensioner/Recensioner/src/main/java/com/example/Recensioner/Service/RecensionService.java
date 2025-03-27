package com.example.Recensioner.Service;

import com.example.Recensioner.Repository.RecensionRepository;
import com.example.Recensioner.entity.Recension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecensionService {

    @Autowired
    private RecensionRepository recensionRepository;

    public List<Recension> hämtaAllaRecensioner() {
        return recensionRepository.findAll();
    }

    public Recension skapaRecension(Recension recension) {
        return recensionRepository.save(recension);
    }
}
