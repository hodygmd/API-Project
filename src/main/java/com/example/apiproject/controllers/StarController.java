package com.example.apiproject.controllers;

import com.example.apiproject.entities.Star;
import com.example.apiproject.services.StarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/stars")
public class StarController {
    @Autowired
    private StarService service;
    @GetMapping
    public ResponseEntity<List<Star>> getStars(@RequestParam(value = "apiKey") String apiKey){
        return new ResponseEntity<>(service.getAllByStatus(apiKey), HttpStatus.OK);
    }
    @GetMapping("/names")
    public ResponseEntity<List<String>> getStarsByStatus() {
        return new ResponseEntity<>(service.getStarsByStatus(),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Star> createStar(@RequestBody Star star,@RequestParam(value = "apiKey") String apiKey){
        return new ResponseEntity<>(service.createStar(star,apiKey),HttpStatus.CREATED);
    }
    @PutMapping("/{idStar}")
    public ResponseEntity<Star> updateStar(@PathVariable("idStar") Integer idStar,@RequestBody Star star,@RequestParam(value = "apiKey") String apiKey){
        return new ResponseEntity<>(service.updateStar(idStar,star,apiKey),HttpStatus.OK);
    }
    @PutMapping("/remove/{idStar}")
    public ResponseEntity<Star> deleteStar(@PathVariable("idStar") Integer idStar,@RequestBody Star star,@RequestParam(value = "apiKey") String apiKey){
        return new ResponseEntity<>(service.updateStar(idStar,star,apiKey),HttpStatus.OK);
    }
}
