package com.skoryk.superapp.event;

import com.skoryk.superapp.model.Event;
import com.skoryk.superapp.repository.EventRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public Optional<Event> findAllById(UUID eventId) {
        return eventRepository.findById(eventId);
    }
}
