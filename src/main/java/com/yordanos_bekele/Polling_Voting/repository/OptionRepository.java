package com.yordanos_bekele.Polling_Voting.repository;

import com.yordanos_bekele.Polling_Voting.entities.Option;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OptionRepository extends JpaRepository<Option, UUID> {
}
