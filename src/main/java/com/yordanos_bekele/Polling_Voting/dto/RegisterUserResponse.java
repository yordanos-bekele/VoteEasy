package com.yordanos_bekele.Polling_Voting.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class RegisterUserResponse {
    private UUID id;
    private String username;
    private String email;
    private String phoneNumber;
    private String role;
}
