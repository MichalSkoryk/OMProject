package com.skoryk.superapp.repository;


import com.skoryk.superapp.model.Proposition;
import org.springframework.data.repository.CrudRepository;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

public interface PropositionRepository extends CrudRepository<Proposition, UUID> {

    Optional<Proposition> findById(UUID id);
    //TODO: Create Endpoint for this feature
    ArrayList<Proposition> findByUserIdAndGroupIdAndActivityType(UUID userId, UUID groupId, UUID activityType);
    ArrayList<Proposition> findByGroupIdAndActivityType(UUID groupId, UUID activityTypeId);
}
