package com.skoryk.superapp.group;

import com.skoryk.superapp.model.Group;
import com.skoryk.superapp.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final AuthenticationManager authenticationManager;

    public Group createGroup(GroupCreateRequest request){
        Group group = Group
                .builder()
                .groupName(request.getGroupName())
                .code(request.getCode())
                .build();
        return groupRepository.save(group);
    }

}
