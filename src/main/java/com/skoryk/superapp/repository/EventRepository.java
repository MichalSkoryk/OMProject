package com.skoryk.superapp.repository;

import com.skoryk.superapp.model.Event;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface EventRepository extends JpaRepository<Event, UUID> {
    @Override
    Optional<Event> findById(UUID id);
}
