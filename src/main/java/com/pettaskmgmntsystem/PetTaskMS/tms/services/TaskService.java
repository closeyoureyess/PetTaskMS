package com.pettaskmgmntsystem.PetTaskMS.tms.services;

import com.pettaskmgmntsystem.PetTaskMS.exeptions.MainException;
import com.pettaskmgmntsystem.PetTaskMS.tms.dto.TasksDto;

import java.util.List;
import java.util.Optional;

public interface TaskService {

    TasksDto createTasks(TasksDto tasksDto) throws MainException;
    TasksDto changeTasks(TasksDto tasksDto) throws MainException;
    Optional<List<TasksDto>> getTasksOfAuthorOrExecutor(String authorOrExecutor, Integer offset, Integer limit, Integer flag);
    boolean deleteTasks(Integer idTasks);

}
