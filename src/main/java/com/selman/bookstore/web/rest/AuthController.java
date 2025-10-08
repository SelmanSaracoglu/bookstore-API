package com.selman.bookstore.web.rest;


import com.selman.bookstore.dto.LoginRequestDTO;
import com.selman.bookstore.dto.LoginResponseDTO;
import com.selman.bookstore.dto.RegisterRequestDTO;
import com.selman.bookstore.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody RegisterRequestDTO registerRequest){
        authService.registerUser(registerRequest);
        return ResponseEntity.ok("User registered successfully!");
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> loginUser(@Valid @RequestBody LoginRequestDTO loginRequest) {
        String jwt = authService.loginUser(loginRequest);
        return ResponseEntity.ok(new LoginResponseDTO(jwt));
    }
}
