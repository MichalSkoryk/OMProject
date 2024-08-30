package com.skoryk.superapp.group;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupCreateRequest {
    private String groupName;
    private String code;
}
