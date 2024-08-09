package com.pettaskmgmntsystem.PetTaskMS.tms.services;

import com.pettaskmgmntsystem.PetTaskMS.tms.dto.TasksDto;
import com.pettaskmgmntsystem.PetTaskMS.tms.repository.Tasks;
import com.pettaskmgmntsystem.PetTaskMS.tms.mapper.TaskMapper;
import com.pettaskmgmntsystem.PetTaskMS.tms.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private TasksRepository tasksRepository;

    public TasksDto createTasks(TasksDto tasksDto) {
        Tasks newTasks = taskMapper.convertDtoToTasks(tasksDto);
        Tasks newTasksFromRepository = tasksRepository.save(newTasks);
        return taskMapper.convertTasksToDto(newTasksFromRepository);
    }

    public TasksDto changeTasks(TasksDto tasksDto) {
        Optional<Tasks> optionalTaskDatabase = tasksRepository.findById(tasksDto.getId());
        if (optionalTaskDatabase.isPresent()) {
            Tasks newTasks = taskMapper.compareTaskAndDto(tasksDto, optionalTaskDatabase.get());
            return taskMapper.convertTasksToDto(
                    tasksRepository.save(newTasks)
            );
        }
        return null;
    }
}
