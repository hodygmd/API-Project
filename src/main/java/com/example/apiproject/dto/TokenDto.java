package com.example.apiproject.dto;

import lombok.Data;

@Data
public class TokenDto {
    private String token;
    public TokenDto(String token) {
        super();
        this.token = token;
    }
}
