package com.yordanos_bekele.Polling_Voting.controllers;

import com.yordanos_bekele.Polling_Voting.commons.DefaultValues;
import com.yordanos_bekele.Polling_Voting.dto.PollRequest;
import com.yordanos_bekele.Polling_Voting.entities.Poll;
import com.yordanos_bekele.Polling_Voting.entities.Users;
import com.yordanos_bekele.Polling_Voting.commons.DefaultValues.*;
import com.yordanos_bekele.Polling_Voting.service.PollService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/polls")
public class PollController {

    private final PollService pollService;
    public PollController(PollService pollService){
        this.pollService = pollService;
    }

    @PostMapping
    public ResponseEntity<Poll> createPoll(@RequestBody PollRequest request){
        // Replace with actual logged-in user (via SecurityContext)
        var userId = new DefaultValues();
        Users dummyUser = Users.builder().id(userId.mockUUID).username("admin").build();
        Poll newPoll = pollService.createPoll(request, dummyUser);

        return ResponseEntity.ok(newPoll);
    }
    @GetMapping
    public ResponseEntity<List<Poll>> getAllPolls(){
        List<Poll> allPolls = pollService.getAllPolls();
        return ResponseEntity.ok(allPolls);
    }
}
