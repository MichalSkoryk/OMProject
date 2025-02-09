package com.skoryk.superapp.repository;


import com.skoryk.superapp.model.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.LinkedList;
import java.util.Optional;
import java.util.UUID;

public interface MembershipRepository extends JpaRepository<Membership, UUID> {

    Optional<Membership> findByUserId(UUID userId);

    LinkedList<Membership> findByGroupId(UUID groupId);

    Optional<Membership> findByUserIdAndGroupId(UUID userId, UUID groupId);

    void deleteMembershipByUserIdAndGroupId(UUID userId, UUID groupId);
}
