package com.example.Recensioner.Controller;

import com.example.Recensioner.Service.RecensionService;
import com.example.Recensioner.entity.Recension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recensioner")
public class RecensionerController {

    @Autowired
    private RecensionService recensionService;

    @GetMapping
    public List<Recension> hämtaAllaRecensioner() {
        return recensionService.hämtaAllaRecensioner();
    }

    @PostMapping
    public Recension skapaRecension(@RequestBody Recension recension) {
        return recensionService.skapaRecension(recension);
    }
}
