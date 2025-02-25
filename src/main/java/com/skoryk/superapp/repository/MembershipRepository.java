package com.skoryk.superapp.repository;


import com.skoryk.superapp.model.Membership;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.UUID;

public interface MembershipRepository extends JpaRepository<Membership, UUID> {

    ArrayList<Membership> findByUserId(UUID userId);

    ArrayList<Membership> findByGroupId(UUID groupId);

    Membership findByUserIdAndGroupId(UUID userId, UUID groupId);

    @Transactional
    void deleteMembershipByUserIdAndGroupId(UUID userId, UUID groupId);
}
