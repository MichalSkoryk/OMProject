package com.skoryk.superapp.group;

import com.skoryk.superapp.model.Group;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class GroupResponse {

    List<Group> groups;

}
