package com.example.apiproject.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "distance_unit")
public class DistanceUnit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "unit")
    private String unit;
    @Column(name = "status")
    private Byte status;
}
