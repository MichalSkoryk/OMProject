package com.skoryk.superapp.event;

import com.skoryk.superapp.event.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/v1/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping("/event/{eventId}")
    public ResponseEntity<Object> getAllMembershipsOfUser(@PathVariable UUID eventId){
        return ResponseEntity.ok(eventService.findAllById(eventId));
    }

}
