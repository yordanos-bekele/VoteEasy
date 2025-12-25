package com.example.demo.entitiy;

import jakarta.persistence.Entity;
import jakarta.persistence.Index;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

import com.example.demo.enums.MeetingStatus;
import com.example.demo.enums.MeetingTypes;

import jakarta.persistence.*;

@Entity
@Table(
    indexes = {
        @Index(name =  "idx_meeting_status", columnList = "status"),
        @Index(name = "idx_meeting_start_time", columnList = "scheduledStartAt")
    }
)
@Getter
@Setter
public class MeetingEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    @Enumerated(EnumType.STRING)
    private MeetingTypes type;

    @Enumerated(EnumType.STRING)
    private MeetingStatus status = MeetingStatus.DRAFT;

    private LocalDateTime scheduledStartAt;
    private LocalDateTime scheduledEndAt;
    private LocalDateTime actualStartAt;
    private LocalDateTime actualEndAt;
    private boolean deleted = false;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    protected MeetingEntity(){ }

    @PrePersist
    protected void onCreate(){
        LocalDateTime now = LocalDateTime.now();
        this.createdAt = now;
        this.updatedAt = now;
    }

    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = LocalDateTime.now();
    }

}
