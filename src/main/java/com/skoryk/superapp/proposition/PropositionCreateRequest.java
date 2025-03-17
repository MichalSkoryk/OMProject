package com.skoryk.superapp.proposition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.UUID;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PropositionCreateRequest {
    private UUID userId;
    private UUID groupId;
    private UUID activityType;
    private LocalDateTime startDatetime;
    private LocalDateTime endDatetime;
}
