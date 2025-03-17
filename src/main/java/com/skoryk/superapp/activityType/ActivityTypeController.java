package com.skoryk.superapp.activityType;

import com.skoryk.superapp.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/activityType")
@RequiredArgsConstructor
public class ActivityTypeController {
    private final ActivityTypeService activityService;
    private final GroupRepository groupRepository;

    @GetMapping("/{activityTypeId}")
    public ResponseEntity<Object> getActivityType(@PathVariable UUID activityTypeId) {
        return ResponseEntity.ok(activityService.findById(activityTypeId));
    }

    @GetMapping("/group/{groupId}")
    public ResponseEntity<Object> getActivityTypesInGroup(@PathVariable UUID groupId) {
        return ResponseEntity.ok(activityService.findAllByGroupId(groupId));
    }


    @PostMapping
    public ResponseEntity<Object> createActivityType(
            @RequestBody ActivityTypeCreateRequest activityTypeCreateRequest
    ) {
        if(activityTypeCreateRequest.getActivityName() == null) {
            return ResponseEntity.badRequest().body("ActivityType is required");
        }
        if(activityTypeCreateRequest.getActivityName().length() > 50) {
            return ResponseEntity.badRequest().body("ActivityType is too long");
        }

        UUID groupId = activityTypeCreateRequest.getGroupId();

        if(!groupRepository.existsById(groupId)) {
            return ResponseEntity.badRequest().body("Group with id " + groupId + " does not exist");
        }
        return ResponseEntity.ok(activityService.save(activityTypeCreateRequest));
    }

    @PostMapping("/{oldActivityId}")
    public ResponseEntity<Object> patchActivityType(
            @RequestBody ActivityTypePatchRequest activityTypePatchRequest,
            @PathVariable UUID oldActivityId
    ) {
        if(activityTypePatchRequest.getActivityName() == null) {
            return ResponseEntity.badRequest().body("ActivityType is required");
        }
        if(activityTypePatchRequest.getActivityName().length() > 50) {
            return ResponseEntity.badRequest().body("ActivityType is too long");
        }

        UUID groupId = activityTypePatchRequest.getGroupId();

        if(!groupRepository.existsById(groupId)) {
            return ResponseEntity.badRequest().body("Group with id " + groupId + " does not exist");
        }

        return ResponseEntity.ok(activityService.patch(activityTypePatchRequest, oldActivityId));

    }

}
