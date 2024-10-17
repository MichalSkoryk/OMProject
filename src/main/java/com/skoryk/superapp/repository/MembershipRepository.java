package com.skoryk.superapp.repository;


import com.skoryk.superapp.model.Membership;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


import java.util.Optional;
import java.util.UUID;

public interface MembershipRepository extends JpaRepository<Membership, Long> {

    Optional<Membership> findByUserId(UUID userId);

    Optional<Membership> findByGroupId(UUID groupId);

    Optional<Membership> findByUserIdAndGroupId(UUID userId, UUID groupId);

    void deleteMembershipByUserIdAndGroupId(UUID userId, UUID groupId);
}
