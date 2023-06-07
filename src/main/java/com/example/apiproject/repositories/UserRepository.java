package com.example.apiproject.repositories;

import com.example.apiproject.entities.DistanceUnit;
import com.example.apiproject.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    @Query("SELECT u FROM User u WHERE u.id=?1 AND u.pass=?2")
    public User findByIdAndPassword(Integer id,String pass);
    public Optional<User> findByUsername(String username);
}
