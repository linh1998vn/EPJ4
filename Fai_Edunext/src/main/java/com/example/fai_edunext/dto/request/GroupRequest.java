package com.example.fai_edunext.dto.request;

import com.example.fai_edunext.entity.Group;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class GroupRequest {
    private Long groupId;
    private String name;
    private String status;

    public GroupRequest(Group group){
        this.name = group.getGroupName();
    }
}
