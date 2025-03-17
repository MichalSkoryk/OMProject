package com.skoryk.superapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Entity
@Data
@Table(name = "propositions")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Proposition {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "user_id")
    private UUID userId;

    @Column(name = "group_id")
    private UUID groupId;

    @Setter
    @Column(name = "activity_type")
    private UUID activityType;

    @Setter
    @Column(name = "start_datetime")
    private LocalDateTime startDatetime;

    @Setter
    @Column(name = "end_datetime")
    private LocalDateTime endDatetime;
}
