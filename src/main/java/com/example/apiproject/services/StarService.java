package com.example.apiproject.services;

import com.example.apiproject.config.JwtProvider;
import com.example.apiproject.entities.Star;
import com.example.apiproject.repositories.StarRepository;
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
public class StarService {
    @Autowired
    private StarRepository repository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private JwtProvider provider;
    @Autowired
    private ModelMapper mapper;
    public List<Star> getAllByStatus(String token){
        provider.validate(token);
        String user=provider.getUsernameFromToken(token);
        userRepository.findByUsername(user).orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        return repository.findAllByStatus();
    }
    public List<String> getStarsByStatus(){
        return repository.findStarsByStatus();
    }
    public Star createStar(Star star, String token){
        provider.validate(token);
        String user=provider.getUsernameFromToken(token);
        userRepository.findByUsername(user).orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        //Convertion List<String> to array:String[]
        String[] result= repository.findStarsByStatus().toArray(new String[0]);
        for(int i=0;i<result.length;i++){
            if (star.getName().equals(result[i])) {
                throw new ResponseStatusException(HttpStatus.FOUND,String.format("Star %s already exists",result[i]));
            }
        }
        return repository.save(star);
    }
    public Star updateStar(Integer idStar,Star star, String token){
        provider.validate(token);
        String user=provider.getUsernameFromToken(token);
        userRepository.findByUsername(user).orElseThrow(()->new ResponseStatusException(HttpStatus.UNAUTHORIZED));
        Optional<Star> result=repository.findById(idStar);
        if(result.isPresent()){
            return repository.save(star);
        }else{
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,String.format("Star %s doesn't exists",idStar));
        }
    }

}
