package com.skoryk.superapp.group;

import com.skoryk.superapp.model.Group;
import com.skoryk.superapp.repository.GroupRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

//TODO: Make groups methods dependant on userid

@Service
@RequiredArgsConstructor
public class GroupService {
    private final GroupRepository groupRepository;
    private final AuthenticationManager authenticationManager;

    public List<Group> findAll(UUID userId) {
        return groupRepository.findAll();
    }


    public Group createGroup( GroupCreateRequest request){
        Group group = Group
                .builder()
                .groupName(request.getGroupName())
                .code(request.getCode())
                .build();
        return groupRepository.save(group);
    }

    public void deleteGroup(UUID id){
        groupRepository.deleteById(id);
    }

    public Optional<Group> getGroup(UUID id) {
        return groupRepository.findById(id);
    }

    public Group patchGroup(UUID id, GroupCreateRequest request) {
        Optional<Group> oldGroup = groupRepository.findById(id);
        if(request.getGroupName()!=null)
            oldGroup.get().setGroupName(request.getGroupName());
        if(request.getCode()!=null)
            oldGroup.get().setCode(request.getCode());
        oldGroup.ifPresent(groupRepository::save);
        return groupRepository.save(oldGroup.get());
    }
}
