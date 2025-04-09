package com.example.Statistik;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface StatisticsRepository extends JpaRepository<Statistics, Long> {

    Statistics findByMovieId(Long movieId);
}
