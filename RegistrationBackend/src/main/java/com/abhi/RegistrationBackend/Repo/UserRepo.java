package com.abhi.RegistrationBackend.Repo;

import com.abhi.RegistrationBackend.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Integer> {
     Optional<User> findByUsername(String username);
     Optional<User> findByEmail(String email);
}
