package com.abhi.RegistrationBackend.Service;

import com.abhi.RegistrationBackend.Model.User;
import com.abhi.RegistrationBackend.Repo.UserRepo;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepo repo;

    @Transactional
    public ResponseEntity<String> Register(User user) {

        if (repo.findByUsername(user.getUsername()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username already exists!");
        }
        if (repo.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Email already exists!");
        }

        repo.save(user);
        return ResponseEntity.status(HttpStatus.OK).body("User registered successfully!");
    }

    public Optional<User> findByUsername(String username) {
        return repo.findByUsername(username);
    }

    public Optional<User> findByEmail(String email) {
        return repo.findByEmail(email);
    }

    public ResponseEntity<String> Login(String username, String password) {
        Optional<User> user=repo.findByUsername(username);
        if(user.isPresent()) {
            if (user.get().getPassword().equals(password)) {
                return ResponseEntity.status(HttpStatus.OK).body("user successfully loggedin");
            } else {
                return ResponseEntity.status((HttpStatus.UNAUTHORIZED)).body("incorrect password");
            }
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }

    }
}
