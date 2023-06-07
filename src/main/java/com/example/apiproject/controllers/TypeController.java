package com.example.apiproject.controllers;

import com.example.apiproject.entities.Type;
import com.example.apiproject.services.TypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/star-types")
public class TypeController {
    @Autowired
    private TypeService service;
    @GetMapping
    public ResponseEntity<List<Type>> getAllByStatus(@RequestParam(value = "apiKey") String apiKey){
        return new ResponseEntity<>(service.getAllByStatus(apiKey), HttpStatus.OK);
    }
    @GetMapping("/types")
    public ResponseEntity<List<String>> getTypesByStatus(){
        return new ResponseEntity<>(service.getTypesByStatus(),HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<Type> createType(@RequestBody Type type, @RequestParam(value = "apiKey") String apiKey){
        return new ResponseEntity<>(service.createType(type,apiKey),HttpStatus.CREATED);
    }
    @PutMapping("/{idType}")
    public ResponseEntity<Type> updateType(@PathVariable("idType") Integer idType, @RequestBody Type type, @RequestParam(value = "apiKey") String apiKey){
        return new ResponseEntity<>(service.updateType(idType,type,apiKey),HttpStatus.OK);
    }
    @PutMapping("/remove/{idType}")
    public ResponseEntity<Type> deleteType(@PathVariable("idType") Integer idType, @RequestBody Type type, @RequestParam(value = "apiKey") String apiKey){
        return new ResponseEntity<>(service.updateType(idType,type,apiKey),HttpStatus.OK);
    }
}
