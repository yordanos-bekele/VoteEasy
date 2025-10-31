package com.yordanos_bekele.Polling_Voting.controllers;

import com.yordanos_bekele.Polling_Voting.commons.DefaultValues;
import com.yordanos_bekele.Polling_Voting.dto.VoteCreateResponse;
import com.yordanos_bekele.Polling_Voting.dto.VoteRequest;
import com.yordanos_bekele.Polling_Voting.service.VoteService;
import com.yordanos_bekele.Polling_Voting.entities.Users;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/votes")
public class VoteController {
    private final VoteService voteService;

    public VoteController(VoteService voteService){
        this.voteService = voteService;
    }

    @PostMapping
    public ResponseEntity<VoteCreateResponse> castVote(@RequestBody VoteRequest request){
        var mockUserId = new DefaultValues();
        Users dummyUser = Users.builder().id(mockUserId.mockUUID).username("user").build();
        var voteId = voteService.castVote(request, dummyUser);
        var response = new VoteCreateResponse(voteId,"Vote cast successfully");
        return ResponseEntity.ok(response);
    }
}
