package com.skoryk.superapp.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.skoryk.superapp.model.Permission.*;

@Getter
@RequiredArgsConstructor
public enum Role {
    USER(Collections.emptySet()),
    ADMIN(Set.of(
            ADMIN_READ,
            ADMIN_UPDATE,
            ADMIN_DELETE,
            ADMIN_CREATE
    ));

    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getSimpleGrantedAuthority() {
        return getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
                .collect(Collectors.toList());
    }
}
