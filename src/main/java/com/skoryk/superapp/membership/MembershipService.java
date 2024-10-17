package com.skoryk.superapp.membership;

import com.skoryk.superapp.model.Membership;
import com.skoryk.superapp.repository.MembershipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MembershipService {

    private final MembershipRepository membershipRepository;

    public Optional<Membership> findAllByUserId(UUID userId) {
        return membershipRepository.findByUserId(userId);
    }

    public Optional<Membership> findAllByGroupId(UUID groupId) {
        return membershipRepository.findByGroupId(groupId);
    }

    public Membership createMembership(UUID userId, UUID groupId, MembershipCreateRequest request){
        Membership membership = Membership
                .builder()
                .userId(userId)
                .groupId(groupId)
                .groupRole(request.getRole())
                .build();
        return membershipRepository.save(membership);
    }

    public Membership patchMembership(UUID userId, UUID groupId, MembershipCreateRequest request){
        Optional<Membership> oldMembership = membershipRepository.findByUserIdAndGroupId(userId, groupId);
        oldMembership.get().setGroupRole(request.getRole());
        oldMembership.ifPresent(membershipRepository::save);
        return membershipRepository.save(oldMembership.get());
    }

    public void deleteMembership(UUID userId, UUID groupId){
        membershipRepository.deleteMembershipByUserIdAndGroupId(userId, groupId);
    }
}
