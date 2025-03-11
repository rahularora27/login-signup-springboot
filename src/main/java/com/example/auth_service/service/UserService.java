package com.example.auth_service.service;

import com.example.auth_service.models.Users;
import com.example.auth_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/signup")
    public String signup(@RequestBody Users signupRequest) {
        String username = signupRequest.getUsername();
        String password = signupRequest.getPassword();
        if (userRepository.findByUsername(username).isPresent()) {
            System.out.println("User already exists");
        } else {
            Users newUser = new Users(username, password);
            userRepository.save(newUser);
            System.out.println("New user created");
        }
        return "Signup request sent";
    }

    @PostMapping("/login")
    public String login(@RequestBody Users loginRequest) {
        String username = loginRequest.getUsername();
        String password = loginRequest.getPassword();

        if (userRepository.findByUsername(username).isPresent()) {
            if (password.equals(userRepository.findByUsername(username).get().getPassword())) {
                System.out.println("User logged in");
            } else {
                System.out.println("Password does not match");
            }
        } else {
            System.out.println("User not found");
        }

        return "Login request sent";
    }
}
