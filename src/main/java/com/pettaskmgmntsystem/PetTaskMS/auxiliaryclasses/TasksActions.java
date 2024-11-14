package com.pettaskmgmntsystem.PetTaskMS.auxiliaryclasses;

import com.pettaskmgmntsystem.PetTaskMS.authorization.dto.CustomUsersDto;

public interface TasksActions {

    boolean compareIntWithConstants(Integer objectInt, Integer constantsInt);
    boolean isPrivilegeTasks(CustomUsersDto customUsersDto);
}
