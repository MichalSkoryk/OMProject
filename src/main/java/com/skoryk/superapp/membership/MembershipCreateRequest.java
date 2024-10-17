package com.skoryk.superapp.membership;

import com.skoryk.superapp.model.GroupRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MembershipCreateRequest {
    private GroupRole role;
}
