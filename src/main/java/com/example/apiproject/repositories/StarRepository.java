package com.example.apiproject.repositories;

import com.example.apiproject.entities.Star;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StarRepository extends JpaRepository<Star,Integer> {
    @Query("SELECT star FROM Star star WHERE star.status=1")
    public List<Star> findAllByStatus();
    @Query("SELECT s.name FROM Star s WHERE s.status=1")
    public List<String> findStarsByStatus();
}
