package com.yordanos_bekele.Polling_Voting.service;

import com.yordanos_bekele.Polling_Voting.commons.Enums;
import com.yordanos_bekele.Polling_Voting.dto.PollRequest;
import com.yordanos_bekele.Polling_Voting.entities.Option;
import com.yordanos_bekele.Polling_Voting.entities.Poll;
import com.yordanos_bekele.Polling_Voting.entities.Users;
import com.yordanos_bekele.Polling_Voting.repository.OptionRepository;
import com.yordanos_bekele.Polling_Voting.repository.PollRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class PollService {
    private final PollRepository pollRepository;
    private final OptionRepository optionRepository;

    public  PollService(PollRepository pollRepository, OptionRepository optionRepository){
        this.pollRepository = pollRepository;
        this.optionRepository = optionRepository;
    }

    @Transactional
    public Poll createPoll(PollRequest request, Users user){
        Poll poll = Poll.builder()
                .title(request.getTitle())
                .description(request.getDescription())
                .createdBy(user)
                .status(Enums.PollStatus.ACTIVE)
                .startTime(request.getStartTime())
                .endTime(request.getEndTime())
                .build();
        Poll savedPoll = pollRepository.save(poll);

        request.getOption().forEach(opt ->{
            Option option = Option.builder()
                    .text(opt)
                    .poll(savedPoll)
                    .build();
            optionRepository.save(option);
        });
    return savedPoll;
    }

    public List<Poll> getAllPolls(){
        return pollRepository.findAll();
    }
}
