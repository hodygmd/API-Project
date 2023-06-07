package com.example.apiproject.services;

import com.example.apiproject.config.JwtProvider;
import com.example.apiproject.dto.DistanceUnitDto;
import com.example.apiproject.dto.TokenDto;
import com.example.apiproject.dto.UserDto;
import com.example.apiproject.entities.DistanceUnit;
import com.example.apiproject.entities.User;
import com.example.apiproject.repositories.DistanceUnitRepository;
import com.example.apiproject.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Optional;

@Service
public class AuthService {
    /*@Autowired
    private DistanceUnitRepository repository;*/
    @Autowired
    private UserRepository repository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private JwtProvider provider;
    @Autowired
    private ModelMapper mapper;
    /*public UserDto save(UserDto user){
        Optional<User> response=repository.findByUsername(user.getUsername());
        if(response.isPresent()){
            throw new ResponseStatusException(HttpStatus.CONFLICT,String.format("User %s already exist",user.getUsername()));
        }
        User entity=repository.save( new User(user.getUsername(), encoder.encode(user.getPass())));
        return mapper.map(entity,UserDto.class);

    }*/
    /*public TokenDto login(DistanceUnitDto distanceUnit) {
        DistanceUnit distanceUnitEntity=repository.findByUnit(distanceUnit.getUnit()).orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        if(encoder.matches(distanceUnit.getId().toString(),distanceUnitEntity.getId().toString())){
            return new TokenDto(provider.createToken(distanceUnitEntity));
        }else{
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED);
        }
    }*/
    public TokenDto login(Integer id, String pass) {
        try {
            UserDto result = entityToDto(repository.findByIdAndPassword(id,pass));
            return new TokenDto(provider.createToken(result));
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("User %s doesn't exist",id));
        }
    }
    public TokenDto validate(String token){
        provider.validate(token);
        String user=provider.getUsernameFromToken(token);
        repository.findByUsername(user).orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        return new TokenDto(token);
    }
    private UserDto entityToDto(User user) {
        return new UserDto(
                user.getId(),
                user.getUsername(),
                user.getPass()
        );

    }
}
