package com.skoryk.superapp.group;

import com.skoryk.superapp.auth.AuthenticationService;
import com.skoryk.superapp.model.Group;
import com.skoryk.superapp.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/groups")
@RequiredArgsConstructor
public class GroupController {

    private final GroupRepository groupRepository;
    private final GroupService groupService;

    /**
     * TODO: Check only for user that sent
     * @return all groups where user is
     */
    @GetMapping()
    public ResponseEntity<Object> getAllGroups(){
        return ResponseEntity.ok(groupRepository.findAll());
    }

    @PostMapping("/create")
    public ResponseEntity<Object> createGroup(@RequestBody GroupCreateRequest request){
        return ResponseEntity.ok(groupService.createGroup(request));
    }

}
