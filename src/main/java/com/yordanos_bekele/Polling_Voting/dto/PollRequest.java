package com.yordanos_bekele.Polling_Voting.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class PollRequest {
    private String title;
    private String description;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private List<String> option;
}
