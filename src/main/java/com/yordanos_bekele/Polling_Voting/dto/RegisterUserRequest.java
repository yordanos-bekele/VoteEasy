package com.yordanos_bekele.Polling_Voting.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RegisterUserRequest {

    private String name;

    @NotNull
    private String username;

    private String email;
    private String phoneNumber;

    @NotNull
    private String password;
}
