package com.pettaskmgmntsystem.PetTaskMS.tms.services;

import com.pettaskmgmntsystem.PetTaskMS.tms.repository.Tasks;
import com.pettaskmgmntsystem.PetTaskMS.tms.dto.TaskDto;
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

    public TaskDto createTasks(TaskDto taskDto) {
        Tasks newTasks = taskMapper.convertDtoToTasks(taskDto);
        Tasks newTasksFromRepository = tasksRepository.save(newTasks);
        return taskMapper.convertTasksToDto(newTasksFromRepository);
    }

    public TaskDto changeTasks(TaskDto taskDto) {
        Optional<Tasks> optionalTaskDatabase = tasksRepository.findById(taskDto.getId());
        if (optionalTaskDatabase.isPresent()) {
            Tasks newTasks = taskMapper.compareTaskAndDto(taskDto, optionalTaskDatabase.get());
            return taskMapper.convertTasksToDto(
                    tasksRepository.save(newTasks)
            );
        }
        return null;
    }
}
