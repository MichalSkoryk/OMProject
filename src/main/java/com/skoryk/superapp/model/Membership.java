package com.skoryk.superapp.model;


import com.skoryk.superapp.membership.MembershipPk;
import com.sun.istack.NotNull;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "memberships")
@IdClass(MembershipPk.class)
public class Membership {
    @Id
    @Column(name = "user_id")
    private UUID userId;
    @Id
    @Column(name = "group_id")
    private UUID groupId;
    @Setter
    @NotNull
    @Column(name = "role")
    private GroupRole groupRole;
}
