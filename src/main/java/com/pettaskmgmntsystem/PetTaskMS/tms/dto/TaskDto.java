package com.pettaskmgmntsystem.PetTaskMS.tms.dto;

import com.pettaskmgmntsystem.PetTaskMS.authorization.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
public class TaskDto implements Serializable {

    private UserDto taskAuthor;
    private UserDto taskExecutor;
    private String taskPriority;
    private String taskStatus;
}
