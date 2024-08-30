package com.skoryk.superapp.repository;

import com.skoryk.superapp.model.Group;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface GroupRepository extends JpaRepository<Group, UUID> {
    @Override
    Optional<Group> findById(UUID id);
}
