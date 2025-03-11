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
public class PropositionPatchRequest {
    private LocalDateTime startDatetime;
    private LocalDateTime endDatetime;
    private UUID activityType;
}
