package com.yordanos_bekele.Polling_Voting.service;

import com.yordanos_bekele.Polling_Voting.dto.AuthRequest;
import com.yordanos_bekele.Polling_Voting.dto.AuthResponse;
import com.yordanos_bekele.Polling_Voting.dto.RegisterUserRequest;
import com.yordanos_bekele.Polling_Voting.entities.Users;
import com.yordanos_bekele.Polling_Voting.exceptions.UserExistsException;
import com.yordanos_bekele.Polling_Voting.exceptions.UserNotFoundException;
import com.yordanos_bekele.Polling_Voting.repository.UserRepository;
import com.yordanos_bekele.Polling_Voting.util.JwtUtil;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;
    private final AuthenticationManager authenticationManager;

    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder, JwtUtil jwtUtil, AuthenticationManager authenticationManager){
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    public Users signup(@Valid @RequestBody RegisterUserRequest request){

        validateUserForSignUP(request);

        request.setPassword(passwordEncoder.encode(request.getPassword()));
        Users newUser = Users.builder()
                .username(request.getUsername())
                .name(request.getName())
                .email(request.getEmail()!= null? request.getEmail() : null)
                .phoneNumber(request.getPhoneNumber()!= null ? request.getPhoneNumber() : null)
                .password(request.getPassword())
                .build();
        return userRepository.save(newUser);
    }
    public AuthResponse login (AuthRequest request){
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(),request.getPassword())
        );
        Users user = userRepository.findByUsername(request.getUsername()).orElseThrow(()-> new RuntimeException("User does not exits"));
        String token = jwtUtil.generateToken(user.getUsername(),user.getRole().name());
        return new AuthResponse(token);
    }

    public void validateUserForSignUP(RegisterUserRequest request){
        if (usernameExits(request.getUsername())) {
            throw new UserExistsException("User already exits with this user name");
        }else if (request.getEmail()!=null && emailExits(request.getEmail())){
            throw new UserExistsException("User exits with this email");
        }else if (request.getPhoneNumber()!=null && phoneExits(request.getPhoneNumber())){
            throw new UserExistsException("User exits with this Phone number");
        }
    }

    public boolean usernameExits(String username){
         Users exitingUser = userRepository.findByUsername(username).orElse(null);
         return exitingUser != null;
    }
    public boolean emailExits(String email){
        Users exitingEmail = userRepository.findByEmail(email).orElse(null);
        return exitingEmail != null;
    }
    public boolean phoneExits(String phoneNumber){
        Users exitingPhone = userRepository.findByPhoneNumber(phoneNumber).orElse(null);
        return exitingPhone!=null;
    }

}
