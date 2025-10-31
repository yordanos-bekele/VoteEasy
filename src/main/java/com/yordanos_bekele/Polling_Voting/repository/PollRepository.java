package com.yordanos_bekele.Polling_Voting.repository;

import com.yordanos_bekele.Polling_Voting.entities.Poll;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface PollRepository extends JpaRepository<Poll, UUID> {
}
