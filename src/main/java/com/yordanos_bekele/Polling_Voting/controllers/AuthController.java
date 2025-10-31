package com.yordanos_bekele.Polling_Voting.controllers;

import com.yordanos_bekele.Polling_Voting.dto.AuthRequest;
import com.yordanos_bekele.Polling_Voting.dto.AuthResponse;
import com.yordanos_bekele.Polling_Voting.dto.RegisterUserRequest;
import com.yordanos_bekele.Polling_Voting.mapper.AuthMapper;
import com.yordanos_bekele.Polling_Voting.service.AuthService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.nio.file.Files;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AuthService authService;
    private final AuthMapper authMapper;

    public AuthController(AuthService authService, AuthMapper authMapper){
        this.authService = authService;
        this.authMapper = authMapper;
    }
    @GetMapping
    public ResponseEntity<String> home() throws IOException {
        ClassPathResource htmlFile = new ClassPathResource("static/login.html");
        String htmlContent = Files.readString((htmlFile.getFile().toPath()));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.TEXT_HTML_VALUE)
                .body(htmlContent);
    }

    @PostMapping("/register")
    public ResponseEntity<RegisterUserRequest> register(@RequestBody RegisterUserRequest request){
        RegisterUserRequest newUser = authMapper.toDto(authService.signup(request));
        return ResponseEntity.ok(newUser);
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request){
        return ResponseEntity.ok(authService.login(request));
    }
}
