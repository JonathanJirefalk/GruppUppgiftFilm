package com.example.Recensioner;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public interface RecensionRepository extends JpaRepository<Recension, Long> {
    List<Recension> findByUserId(Long userId);
    List<Recension> findByMovieId(Long movieId);
}