package com.pettaskmgmntsystem.PetTaskMS.fabrics;

import com.pettaskmgmntsystem.PetTaskMS.auxiliaryclasses.TasksActions;
import com.pettaskmgmntsystem.PetTaskMS.auxiliaryclasses.UserActions;

public interface ActionsFabric {

    TasksActions createTasksActions();
    UserActions createUserActions();
}
