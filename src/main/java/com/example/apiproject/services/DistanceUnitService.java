package com.example.apiproject.services;

import com.example.apiproject.config.JwtProvider;
import com.example.apiproject.dto.DistanceUnitDto;
import com.example.apiproject.dto.TokenDto;
import com.example.apiproject.entities.DistanceUnit;
import com.example.apiproject.repositories.DistanceUnitRepository;
import com.example.apiproject.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class DistanceUnitService {
    @Autowired
    private DistanceUnitRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private JwtProvider provider;
    @Autowired
    private ModelMapper mapper;
    public List<DistanceUnit> getAllByStatus(String token){
        provider.validate(token);
        String user=provider.getUsernameFromToken(token);
        userRepository.findByUsername(user).orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        return repository.findAllByStatus();
    }
    public List<String> getUnitsByStatus(){
        return repository.findUnitsByStatus();
    }
    public DistanceUnit createDistanceUnit(DistanceUnit unit,String token){
        provider.validate(token);
        String user=provider.getUsernameFromToken(token);
        userRepository.findByUsername(user).orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        String[] result= repository.findUnitsByStatus().toArray(new String[0]);
        for(int i=0;i<result.length;i++){
            if (unit.getUnit().equals(result[i])) {
                throw new ResponseStatusException(HttpStatus.FOUND,String.format("Unit %s already exists",result[i]));
            }
        }
        return repository.save(unit);
    }
    public DistanceUnit updateDistanceUnit(Integer idDistanceUnit,DistanceUnit unit, String token){
        provider.validate(token);
        String user=provider.getUsernameFromToken(token);
        userRepository.findByUsername(user).orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        Optional<DistanceUnit> result=repository.findById(idDistanceUnit);
        if(result.isPresent()){
            return repository.save(unit);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Unit %s doesn't exists",idDistanceUnit));
        }

    }
}
