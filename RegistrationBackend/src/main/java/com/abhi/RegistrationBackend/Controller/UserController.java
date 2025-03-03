package com.abhi.RegistrationBackend.Controller;

import com.abhi.RegistrationBackend.Model.User;
import com.abhi.RegistrationBackend.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
// register-eight-self.vercel.app   or you only use * in origin which mean all
public class UserController {

    @Autowired
    private UserService service;

    @RequestMapping("/")
    public String Greeting() {
        return "Hello, welcome to the registration system!";
    }

    @PostMapping("/register")
    public  ResponseEntity<String> Register(@RequestBody User user) {
        return service.Register(user);
    }

    @PostMapping("/login")
    public ResponseEntity<?> Login( @RequestBody User user)
    {
        return service.Login(user.getUsername(), user.getPassword());
    }
    @GetMapping("/user/{username}")
    public Optional<User> getUserByUsername(@PathVariable String username) {
        return service.findByUsername(username);
    }
}
