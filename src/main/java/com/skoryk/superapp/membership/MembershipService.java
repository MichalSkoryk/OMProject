package com.skoryk.superapp.membership;

import com.skoryk.superapp.model.Membership;
import com.skoryk.superapp.repository.MembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MembershipService {

    private final MembershipRepository membershipRepository;

    public ArrayList<Membership> findAllByUserId(UUID userId) {
        return membershipRepository.findByUserId(userId);
    }

    public ArrayList<Membership> findAllByGroupId(UUID groupId) {
        return membershipRepository.findByGroupId(groupId);
    }

    // Would like to add control
    public Membership createMembership(UUID userId, UUID groupId, MembershipCreateRequest request){
        Membership membership = Membership
                .builder()
                .groupRole(request.getRole())
                .groupId(groupId)
                .userId(userId)
                .build();
        return membershipRepository.save(membership);
    }

    public Membership patchMembership(UUID userId, UUID groupId, MembershipCreateRequest request){
        Membership oldMembership = membershipRepository.findByUserIdAndGroupId(userId, groupId);
        if (oldMembership == null)
            return null;
        oldMembership.setGroupRole(request.getRole());
        return membershipRepository.save(oldMembership);
    }

    public void deleteMembership(UUID userId, UUID groupId){
        membershipRepository.deleteMembershipByUserIdAndGroupId(userId, groupId);
    }

    public boolean isUserInGroup(UUID userId, UUID groupId){
        return membershipRepository.findByUserIdAndGroupId(userId, groupId) != null;
    }
}
