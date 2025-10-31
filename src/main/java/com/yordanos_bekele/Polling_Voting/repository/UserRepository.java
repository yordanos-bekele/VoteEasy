package com.yordanos_bekele.Polling_Voting.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.yordanos_bekele.Polling_Voting.entities.Users;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<Users, UUID> {
    Optional<Users> findByPhoneNumber(String phoneNumber);
    Optional<Users> findByUsername(String username);
    Optional<Users> findByEmail(String email);
}
