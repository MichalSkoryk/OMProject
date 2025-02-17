package com.skoryk.superapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Data
@Table(name = "events")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Getter
    @Setter
    @Column(name = "group_id", unique = true, nullable = false)
    private UUID groupId;
    @Getter
    @Setter
    @Column(name = "start_time", nullable = false)
    private LocalDateTime startTime;
    @Getter
    @Setter
    @Column(name = "end_time", nullable = false)
    private LocalDateTime endTime;
    @Getter
    @Setter
    @Column(name = "name", nullable = false, length = 100)
    private String name;
    @Getter
    @Setter
    @Column(name = "description")
    private String desc;
    @Getter
    @Setter
    @Column(name = "creator", nullable = false)
    private UUID creator;
    @Getter
    @Setter
    @Column(name = "color")
    private String color;
}
