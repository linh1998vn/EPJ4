package com.example.fai_edunext.dto.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
public class AddGroup_ClassroomRequest {
    private List<GroupRequest> groups;
}
