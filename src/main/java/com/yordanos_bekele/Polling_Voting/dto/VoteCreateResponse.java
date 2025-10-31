package com.yordanos_bekele.Polling_Voting.dto;



import java.util.UUID;


public class VoteCreateResponse {
    public UUID voteId;
    public String message;

    public VoteCreateResponse(UUID voteId, String message){
        this.voteId = voteId;
        this.message = message;
    }
}
