package com.example.apiproject.repositories;

import com.example.apiproject.entities.Star;
import com.example.apiproject.entities.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TypeRepository extends JpaRepository<Type,Integer> {
    @Query("SELECT type FROM Type type WHERE type.status=1")
    public List<Type> findAllByStatus();
    @Query("SELECT t.type FROM Type t WHERE t.status=1")
    public List<String> findTypesByStatus();
}
