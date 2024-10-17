package com.skoryk.superapp.membership;

import jakarta.persistence.Column;
import jakarta.persistence.IdClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Objects;
import java.util.UUID;

@Data
@Getter
@NoArgsConstructor
public class MembershipPk  implements java.io.Serializable {
    @Column(nullable = false, name = "user_id")
    private UUID userId;
    @Column(nullable = false, name = "group_id")
    private UUID groupId;

    @Override
    public boolean equals(Object obj) {
        if(this == obj) return true;
        if (!(obj instanceof MembershipPk)) return false;
        return userId.equals(((MembershipPk)obj).userId) && groupId.equals(((MembershipPk)obj).groupId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userId, groupId);
    }

}
