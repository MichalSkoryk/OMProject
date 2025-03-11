package com.skoryk.superapp.proposition;


import com.skoryk.superapp.model.Proposition;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/propositions")
@RequiredArgsConstructor
public class PropositionController {
    private final PropositionService propositionService;

    @GetMapping("/{propositionId}")
    public ResponseEntity<Object> getProposition(@PathVariable UUID propositionId) {
        return ResponseEntity.ok(propositionService.getPropositionById(propositionId));
    }

    @GetMapping("/group/{groupId}/activityType/{activityTypeId}")
    public ResponseEntity<ArrayList<Proposition>> getActivityTypes(
            @PathVariable UUID groupId,
            @PathVariable UUID activityTypeId)
    {
        return ResponseEntity.ok(propositionService.getPropositionsFromGroupByIdActivity(groupId, activityTypeId));
    }

    @PostMapping("/")
    public ResponseEntity<Object> createProposition(@RequestBody PropositionCreateRequest propositionCreateRequest) {
        if(propositionCreateRequest.getUserId() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You must provide a user id");
        if(propositionCreateRequest.getGroupId() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("You must provide a group id");
        if(propositionCreateRequest.getActivityType() == null)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("ActivityType is required");
        if(propositionCreateRequest.getEndDatetime().isBefore(propositionCreateRequest.getStartDatetime()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The end time is before the start time");
        if(propositionCreateRequest.getEndDatetime().isBefore(LocalDateTime.now()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The end time is in the past");
        if(propositionCreateRequest.getStartDatetime().isBefore(LocalDateTime.now()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The start time is in the past");
        return ResponseEntity.status(HttpStatus.CREATED).body(propositionService.createProposition(propositionCreateRequest));
    }

    @PatchMapping("/{propositionId}")
    public ResponseEntity<Object> patchActivityType(
            @PathVariable UUID propositionId,
            @RequestBody PropositionPatchRequest propositionPatchRequest)
    {
        if(propositionPatchRequest.getEndDatetime().isBefore(propositionPatchRequest.getStartDatetime()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The end time is before the start time");
        if(propositionPatchRequest.getEndDatetime().isBefore(LocalDateTime.now()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The end time is in the past");
        if(propositionPatchRequest.getStartDatetime().isBefore(LocalDateTime.now()))
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The start time is in the past");
        return ResponseEntity.status(HttpStatus.OK).body(propositionService.patchProposition(propositionId, propositionPatchRequest));
    }

    @DeleteMapping("/{propositionId}")
    public ResponseEntity<Object> deleteProposition(@PathVariable UUID propositionId) {
        propositionService.deleteProposition(propositionId);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

}
