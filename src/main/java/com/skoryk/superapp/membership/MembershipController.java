package com.skoryk.superapp.membership;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/memberships")
@RequiredArgsConstructor
public class MembershipController {
    private final MembershipService membershipService;

    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getAllMembershipsOfUser(@PathVariable UUID userId){
        return ResponseEntity.ok(membershipService.findAllByUserId(userId));
    }

    //If already in the database should return error already created
    /**
     * Called when user joins the group
     */
    @PostMapping("/user/{userId}/group/{groupId}")
    public ResponseEntity<Object> joinUserToTheGroup(
            @PathVariable UUID userId,
            @PathVariable UUID groupId,
            @RequestBody MembershipCreateRequest request
    ){
        if(membershipService.isUserInGroup(userId, groupId))
            return ResponseEntity.status(409).body("{\"errorMsg\":\" User with Id '" + userId + "' is already member of the group '" + groupId + "'.\"}");
        return ResponseEntity.status(201).body(membershipService.createMembership(userId, groupId, request));
    }

    @PatchMapping("/user/{userId}/group/{groupId}")
    public ResponseEntity<Object> updateUserRoleInTheGroup(
            @PathVariable UUID userId,
            @PathVariable UUID groupId,
            @RequestBody MembershipCreateRequest request
    ){
        return ResponseEntity.ok(membershipService.patchMembership(userId, groupId, request));
    }

    //Not working probably search error
    @DeleteMapping("/user/{userId}/group/{groupId}")
    public ResponseEntity<Object> deleteUserFromTheGroup(
        @PathVariable UUID userId,
        @PathVariable UUID groupId
    ){
        membershipService.deleteMembership(userId, groupId);
        return ResponseEntity.status(204).build();
    }

    //Not working why?
    @GetMapping("/group/{groupId}")
    public ResponseEntity<Object> getAllGroupUsers(
            @PathVariable UUID groupId
    ){
        return ResponseEntity.ok(membershipService.findAllByGroupId(groupId));
    }
}
