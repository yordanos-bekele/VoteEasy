package com.yordanos_bekele.Polling_Voting.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class VoteRequest {
    private UUID pollId;
    private UUID optionId;
}
