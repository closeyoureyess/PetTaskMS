package com.pettaskmgmntsystem.PetTaskMS.tms.mapper;

/*import com.pettaskmgmntsystem.PetTaskMS.authorization.mapper.UserMapper;*/
import com.pettaskmgmntsystem.PetTaskMS.tms.auxiliaryclasses.Tasks;
import com.pettaskmgmntsystem.PetTaskMS.tms.dto.TaskDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

/*    @Autowired
    UserMapper userMapper;*/

    public Tasks convertDtoToTasks(TaskDto taskDto) {
        Tasks taskLocalObject = new Tasks();
        taskLocalObject.setId(taskDto.getId());
        /*taskLocalObject.setTaskExecutor(userMapper.convertDtoToUser(taskDto.getTaskExecutor()));
        taskLocalObject.setTaskAuthor(userMapper.convertDtoToUser(taskDto.getTaskAuthor()));*/
        taskLocalObject.setTaskPriority(taskDto.getTaskPriority());
        taskLocalObject.setTaskStatus(taskDto.getTaskStatus());
        taskLocalObject.setDescription(taskDto.getDescription());
        taskLocalObject.setHeader(taskDto.getHeader());
        /*taskLocalObject.setNotes(taskDto.getNotes());*/
        return taskLocalObject;
    }

    public TaskDto convertTasksToDto(Tasks tasks) {
        TaskDto taskDtoLocalObject = new TaskDto();
        taskDtoLocalObject.setId(tasks.getId());
       /* taskDtoLocalObject.setTaskExecutor(userMapper.convertUserToDto(tasks.getTaskExecutor()));
        taskDtoLocalObject.setTaskAuthor(userMapper.convertUserToDto(tasks.getTaskAuthor()));*/
        taskDtoLocalObject.setTaskPriority(tasks.getTaskPriority());
        taskDtoLocalObject.setTaskStatus(tasks.getTaskStatus());
        taskDtoLocalObject.setDescription(tasks.getDescription());
        taskDtoLocalObject.setHeader(tasks.getHeader());
        /*taskDtoLocalObject.setNotes(tasks.getNotes());*/
        return taskDtoLocalObject;
    }

    public Tasks compareTaskAndDto(TaskDto taskDto, Tasks tasks){
        //author
       /* if(taskDto.getTaskAuthor().getName() != null && !taskDto.getTaskAuthor().getName()
                .equals(tasks.getTaskAuthor().getName())){

            tasks.getTaskAuthor().setName(taskDto.getTaskAuthor().getName());
        }
        if(taskDto.getTaskAuthor().getSurname() != null && !taskDto.getTaskAuthor().getSurname()
                .equals(tasks.getTaskAuthor().getSurname())){

            tasks.getTaskAuthor().setSurname(taskDto.getTaskAuthor().getSurname());
        }
        if(taskDto.getTaskAuthor().getEmail() != null && !taskDto.getTaskAuthor().getEmail()
                .equals(tasks.getTaskAuthor().getEmail())){

            tasks.getTaskAuthor().setEmail(taskDto.getTaskAuthor().getEmail());
        }
        //executor
        if(taskDto.getTaskExecutor().getName() != null && !taskDto.getTaskExecutor().getName()
                .equals(tasks.getTaskExecutor().getName())){

            tasks.getTaskExecutor().setName(taskDto.getTaskExecutor().getName());
        }
        if(taskDto.getTaskExecutor().getSurname() != null && !taskDto.getTaskExecutor().getSurname()
                .equals(tasks.getTaskExecutor().getSurname())){

            tasks.getTaskExecutor().setSurname(taskDto.getTaskExecutor().getSurname());
        }
        if(taskDto.getTaskExecutor().getEmail() != null && !taskDto.getTaskExecutor().getEmail()
                .equals(tasks.getTaskExecutor().getEmail())){

            tasks.getTaskExecutor().setEmail(taskDto.getTaskExecutor().getEmail());
        }*/
        //desctiption
        if(taskDto.getDescription() != null && !taskDto.getDescription()
                .equals(tasks.getDescription())){

            tasks.setDescription(taskDto.getDescription());
        }
        //priority
        if(taskDto.getTaskPriority() != null && !taskDto.getTaskPriority()
                .equals(tasks.getTaskPriority())){

            tasks.setTaskPriority(taskDto.getTaskPriority());
        }
        //status
        if(taskDto.getTaskStatus() != null && !taskDto.getTaskStatus()
                .equals(tasks.getTaskStatus())){

            tasks.setTaskStatus(taskDto.getTaskStatus());
        }
        return tasks;
    }
}
