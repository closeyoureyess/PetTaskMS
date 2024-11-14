package com.pettaskmgmntsystem.PetTaskMS.mapper;

import com.pettaskmgmntsystem.PetTaskMS.exeptions.ExecutorNotFoundExeption;
import com.pettaskmgmntsystem.PetTaskMS.tms.dto.TasksDto;
import com.pettaskmgmntsystem.PetTaskMS.tms.repository.Tasks;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

public interface TaskMapper {

    Tasks convertDtoToTasks(TasksDto tasksDto, Integer... method) throws ExecutorNotFoundExeption;
    TasksDto convertTasksToDto(Tasks tasks);
    List<TasksDto> transferListTasksToDto(List<Tasks> tasksList);
    Tasks compareTaskAndDto(TasksDto tasksDto, Tasks tasks) throws UsernameNotFoundException;

}
