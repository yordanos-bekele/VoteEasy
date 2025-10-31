package com.yordanos_bekele.Polling_Voting.entities;

import com.yordanos_bekele.Polling_Voting.commons.Enums.Role;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import java.util.UUID;

@Entity(name = "users")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Users {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(updatable = false)
    private UUID id;

    @Column(name="full_name")
    private String name;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(unique = true)
    private String email;

    @Column(name = "phone_number", unique = true)
    private String phoneNumber;

    private String password;

    @Column(name = "is_email_verified",columnDefinition = "BOOLEAN default FALSE")
    private boolean isEmailVerified;

    @Column(name = "is_phone_verified", columnDefinition = "BOOLEAN default FALSE")
    private boolean isPhoneVerified;

    @Enumerated(EnumType.STRING)
    @Column(columnDefinition = "VARCHAR(100) default 'USER'")
    private Role role;
}
