package com.yordanos_bekele.Polling_Voting.repository;

import com.yordanos_bekele.Polling_Voting.entities.Vote;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VoteRepository extends JpaRepository<Vote, UUID> {
    boolean existsByPollIdAndUserId(UUID pollId, UUID userId);
    Long countByOptionId(UUID optionId);
}
