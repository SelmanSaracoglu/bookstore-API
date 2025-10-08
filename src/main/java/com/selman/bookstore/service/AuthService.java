package com.selman.bookstore.service;


import com.selman.bookstore.domain.User;
import com.selman.bookstore.dto.RegisterRequestDTO;
import com.selman.bookstore.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(RegisterRequestDTO registerRequest){
        // Check if username already exists
        if (userRepository.findByUsername(registerRequest.username()).isPresent()) {
            throw new IllegalStateException("Error: Username is already taken!");
        }

        // Check if email already exists
        if (userRepository.findByEmail(registerRequest.email()).isPresent()){
            throw new IllegalStateException("Error: Email is already in use!");
        }

        // Create new user's account
        User user = new User();
        user.setUsername(registerRequest.username());
        user.setEmail(registerRequest.email());
        // Encode password before saving
        user.setPassword(passwordEncoder.encode(registerRequest.password()));

        userRepository.save(user);
    }
}
