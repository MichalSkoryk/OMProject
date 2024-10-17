package com.skoryk.superapp.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Collections;
import java.util.Set;

//TODO: Add permissions for different roles, maybe rwd linux?
@Getter
@RequiredArgsConstructor
public enum GroupRole {
    GROUP_OWNER(Collections.emptySet()),
    GROUP_MANAGER(Collections.emptySet()),
    GROUP_USER(Collections.emptySet());

    private final Set<Permission> permissions;

}
