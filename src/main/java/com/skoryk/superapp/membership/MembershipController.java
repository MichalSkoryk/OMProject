package com.skoryk.superapp.membership;

import com.skoryk.superapp.model.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1/memberships")
@RequiredArgsConstructor
public class MembershipController {
    private final MembershipService membershipService;


    @GetMapping("/user/{userId}")
    public ResponseEntity<Object> getAllMembershipsOfUser(@PathVariable UUID userId){
        ArrayList<Membership> userMemberships= membershipService.findAllByUserId(userId);
        if(userMemberships.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(userMemberships);
    }

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

    @GetMapping("/group/{groupId}")
    public ResponseEntity<Object> getAllGroupUsers(
            @PathVariable UUID groupId
    ){
        ArrayList<Membership> groupMemberships = membershipService.findAllByGroupId(groupId);
        if(groupMemberships.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(groupMemberships);
    }
}
