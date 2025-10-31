package com.yordanos_bekele.Polling_Voting.mapper;

import com.yordanos_bekele.Polling_Voting.dto.AuthResponse;
import com.yordanos_bekele.Polling_Voting.dto.RegisterUserRequest;
import com.yordanos_bekele.Polling_Voting.entities.Users;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface AuthMapper {

    RegisterUserRequest toDto(Users user);
}
