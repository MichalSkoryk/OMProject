package com.skoryk.superapp.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Data
@Table(name = "activity_types")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ActivityType {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Getter
    @Setter
    @Column(name = "group_id")
    private UUID groupId;

    @Getter
    @Setter
    @Column(name = "activity_name", length = 50)
    private String activityName;

    @Getter
    @Setter
    @Column(name = "state")
    private boolean state;
}
