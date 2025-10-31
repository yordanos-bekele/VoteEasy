package com.yordanos_bekele.Polling_Voting.service;

import com.yordanos_bekele.Polling_Voting.commons.Enums;
import com.yordanos_bekele.Polling_Voting.dto.VoteRequest;
import com.yordanos_bekele.Polling_Voting.entities.Option;
import com.yordanos_bekele.Polling_Voting.entities.Poll;
import com.yordanos_bekele.Polling_Voting.entities.Users;
import com.yordanos_bekele.Polling_Voting.entities.Vote;
import com.yordanos_bekele.Polling_Voting.repository.OptionRepository;
import com.yordanos_bekele.Polling_Voting.repository.PollRepository;
import com.yordanos_bekele.Polling_Voting.repository.VoteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class VoteService {
    private final VoteRepository voteRepository;
    private final PollRepository pollRepository;
    private final OptionRepository optionRepository;

    public VoteService(VoteRepository voteRepository, PollRepository pollRepository, OptionRepository optionRepository){
        this.voteRepository = voteRepository;
        this.pollRepository = pollRepository;
        this.optionRepository = optionRepository;
    }
    @Transactional
    public UUID castVote(VoteRequest request, Users user){
        if (voteRepository.existsByPollIdAndUserId(request.getPollId(),user.getId()))
            throw new IllegalStateException("User already voted");

        Poll poll = pollRepository.findById(request.getPollId())
                .orElseThrow(()-> new IllegalArgumentException("Poll not found"));
        if (poll.getStatus() != Enums.PollStatus.ACTIVE)
            throw new IllegalStateException("Poll not active");
        Option option = optionRepository.findById(request.getOptionId())
                .orElseThrow(()-> new IllegalArgumentException("Option not found"));
        Vote vote = Vote.builder()
                .poll(poll)
                .option(option)
                .user(user)
                .votedAt(LocalDateTime.now())
                .build();
        Vote savedVote = voteRepository.save(vote);
        return savedVote.getId();
    }
}
