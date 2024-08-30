package com.skoryk.superapp.model;

import jakarta.persistence.*;
import lombok.*;


import java.util.UUID;

@Entity
@Data
@Table(name = "groups")
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Group {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;
    @Getter
    @Column(name = "group_name")
    private String groupName;
    @Getter
    @Column(length = 8)
    private String code;

}
