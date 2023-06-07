package com.example.apiproject.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "star")
public class Star {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "name")
    private String name;
    @Column(name = "mass")
    private Double mass;
    @ManyToOne
    @JoinColumn(name = "id_distance_unit")
    private DistanceUnit id_distance_unit;
    @Column(name = "distance")
    private String  distance;
    @ManyToOne
    @JoinColumn(name = "id_type")
    private Type id_type;
    @Column(name = "status")
    private Byte status;
}
