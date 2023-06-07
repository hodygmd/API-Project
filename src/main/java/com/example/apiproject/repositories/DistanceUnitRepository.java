package com.example.apiproject.repositories;

import com.example.apiproject.entities.DistanceUnit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DistanceUnitRepository extends JpaRepository<DistanceUnit,Integer> {
    @Query("SELECT d FROM DistanceUnit d WHERE d.status=1")
    public List<DistanceUnit> findAllByStatus();
    @Query("SELECT unit FROM DistanceUnit WHERE status=1")
    public List<String> findUnitsByStatus();
    public Optional<DistanceUnit> findByUnit(String unit);
    @Query("select du from DistanceUnit du where du.unit = ?1 and du.id = ?2")
    public DistanceUnit findByUnitAndId(String unit,Integer id);
}