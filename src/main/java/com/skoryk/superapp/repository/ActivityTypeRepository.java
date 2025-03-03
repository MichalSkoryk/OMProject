package com.skoryk.superapp.repository;


import com.skoryk.superapp.model.ActivityType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public interface ActivityTypeRepository extends JpaRepository<ActivityType, UUID> {

    ArrayList<ActivityType> findByGroupId(UUID groupId);

    Optional<ActivityType> findById(UUID id);

}
