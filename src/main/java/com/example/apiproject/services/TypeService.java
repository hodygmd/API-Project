package com.example.apiproject.services;

import com.example.apiproject.config.JwtProvider;
import com.example.apiproject.entities.Type;
import com.example.apiproject.repositories.TypeRepository;
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
public class TypeService {
    @Autowired
    private TypeRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private JwtProvider provider;
    @Autowired
    private ModelMapper mapper;
    public List<Type> getAllByStatus(String token){
        provider.validate(token);
        String user=provider.getUsernameFromToken(token);
        userRepository.findByUsername(user).orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        return repository.findAllByStatus();
    }
    public List<String> getTypesByStatus(){
        return repository.findTypesByStatus();
    }
    public Type createType(Type type, String token){
        provider.validate(token);
        String user=provider.getUsernameFromToken(token);
        userRepository.findByUsername(user).orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        String[] result= repository.findTypesByStatus().toArray(new String[0]);
        for(int i=0;i<result.length;i++){
            if (type.getType().equals(result[i])) {
                throw new ResponseStatusException(HttpStatus.FOUND,String.format("Type %s already exists",result[i]));
            }
        }
        return repository.save(type);
    }
    public Type updateType(Integer idType, Type type, String token){
        provider.validate(token);
        String user=provider.getUsernameFromToken(token);
        userRepository.findByUsername(user).orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        Optional<Type> result=repository.findById(idType);
        if(result.isPresent()){
            return repository.save(type);
        }else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Type %s doesn't exists",idType));
        }
    }
}
