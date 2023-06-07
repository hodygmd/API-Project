package com.example.apiproject.controllers;

import com.example.apiproject.dto.DistanceUnitDto;
import com.example.apiproject.dto.TokenDto;
import com.example.apiproject.entities.DistanceUnit;
import com.example.apiproject.services.AuthService;
import com.example.apiproject.services.DistanceUnitService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/distance_units")
public class DistanceUnitController {
    @Autowired
    private DistanceUnitService service;
    @Autowired
    private AuthService authService;
    @GetMapping
    public ResponseEntity<List<DistanceUnit>> getDistanceUnits(@RequestParam(value = "apiKey") String apiKey){
        return new ResponseEntity<>(service.getAllByStatus(apiKey), HttpStatus.OK);
    }
    @GetMapping("/units")
    public ResponseEntity<List<String>> getUnitsByStatus(){
        return new ResponseEntity<>(service.getUnitsByStatus(),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<DistanceUnit> createDistanceUnit(@RequestBody DistanceUnit unit, @RequestParam(value = "apiKey") String apiKey){
        return new ResponseEntity<>(service.createDistanceUnit(unit,apiKey),HttpStatus.CREATED);
    }
    @PutMapping("/{idDistanceUnit}")
    public ResponseEntity<DistanceUnit> updateDistanceUnit(@PathVariable("idDistanceUnit") Integer idDistanceUnit,@RequestBody DistanceUnit unit, @RequestParam(value = "apiKey") String apiKey){
        return new ResponseEntity<>(service.updateDistanceUnit(idDistanceUnit,unit,apiKey),HttpStatus.OK);
    }
    @PutMapping("/remove/{idDistanceUnit}")
    public ResponseEntity<DistanceUnit> deleteDistanceUnit(@PathVariable("idDistanceUnit") Integer idDistanceUnit,@RequestBody DistanceUnit unit, @RequestParam(value = "apiKey") String apiKey){
        return new ResponseEntity<>(service.updateDistanceUnit(idDistanceUnit,unit,apiKey),HttpStatus.OK);
    }
}
