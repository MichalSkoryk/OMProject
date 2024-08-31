package com.skoryk.superapp.group;

import com.skoryk.superapp.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/user/{userId}/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupRepository groupRepository;
    private final GroupService groupService;

    @GetMapping()
    public ResponseEntity<Object> getAllGroups(@PathVariable UUID userId){
        return ResponseEntity.ok(groupService.findAll(userId));
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createGroup(@PathVariable UUID userId, @RequestBody GroupCreateRequest request){
        return ResponseEntity.status(201).body(groupService.createGroup(request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteGroup(@PathVariable UUID userId, @PathVariable UUID id){
        groupService.deleteGroup(id);
        return ResponseEntity.status(204).build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Object> patchGroup(
            @PathVariable UUID userId,
            @PathVariable UUID id,
            @RequestBody GroupCreateRequest request
    ){
        return ResponseEntity.ok(groupService.patchGroup(id, request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Object> getGroup(@PathVariable UUID userId, @PathVariable UUID id){
        return ResponseEntity.ok(groupService.getGroup(id));
    }
}
