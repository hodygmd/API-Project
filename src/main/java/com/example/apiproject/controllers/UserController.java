package com.example.apiproject.controllers;

import com.example.apiproject.dto.TokenDto;
import com.example.apiproject.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/auth")
public class UserController {
    @Autowired
    private AuthService service;
    @GetMapping("/login")
    public ResponseEntity<TokenDto> login(@RequestParam(value = "id") Integer id, @RequestParam(value = "pass") String pass){
        TokenDto token=service.login(id,pass);
        return ResponseEntity.ok(token);
    }
    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validate(@RequestParam String token){
        TokenDto tokenDto=service.validate(token);
        return ResponseEntity.ok(tokenDto);
    }
}
