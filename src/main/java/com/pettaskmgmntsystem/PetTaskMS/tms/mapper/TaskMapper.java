package com.pettaskmgmntsystem.PetTaskMS.tms.mapper;

/*import com.pettaskmgmntsystem.PetTaskMS.authorization.mapper.UserMapper;*/
import com.pettaskmgmntsystem.PetTaskMS.authorization.mapper.UserMapper;
import com.pettaskmgmntsystem.PetTaskMS.tms.dto.TasksDto;
import com.pettaskmgmntsystem.PetTaskMS.tms.repository.Tasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NotesMapper notesMapper;

    public Tasks convertDtoToTasks(TasksDto tasksDto) {
        Tasks taskLocalObject = new Tasks();
        taskLocalObject.setId(tasksDto.getId());
        taskLocalObject.setTaskExecutor(userMapper.convertDtoToUser(tasksDto.getTaskExecutor()));
        taskLocalObject.setTaskAuthor(userMapper.convertDtoToUser(tasksDto.getTaskAuthor()));
        taskLocalObject.setTaskPriority(tasksDto.getTaskPriority());
        taskLocalObject.setTaskStatus(tasksDto.getTaskStatus());
        taskLocalObject.setDescription(tasksDto.getDescription());
        taskLocalObject.setHeader(tasksDto.getHeader());
        taskLocalObject.setNotes(notesMapper.convertDtoToNotes(tasksDto.getNotesDto()));
        return taskLocalObject;
    }

    public TasksDto convertTasksToDto(Tasks tasks) {
        TasksDto tasksDtoLocalObject = new TasksDto();
        tasksDtoLocalObject.setId(tasks.getId());
        tasksDtoLocalObject.setTaskExecutor(userMapper.convertUserToDto(tasks.getTaskExecutor()));
        tasksDtoLocalObject.setTaskAuthor(userMapper.convertUserToDto(tasks.getTaskAuthor()));
        tasksDtoLocalObject.setTaskPriority(tasks.getTaskPriority());
        tasksDtoLocalObject.setTaskStatus(tasks.getTaskStatus());
        tasksDtoLocalObject.setDescription(tasks.getDescription());
        tasksDtoLocalObject.setHeader(tasks.getHeader());
        tasksDtoLocalObject.setNotesDto(notesMapper.convertNotesToDto(tasks.getNotes()));
        return tasksDtoLocalObject;
    }

    public Tasks compareTaskAndDto(TasksDto tasksDto, Tasks tasks){
        //author
        /*if(tasksDto.getTaskAuthor().getEmail() != null && !tasksDto.getTaskAuthor().getEmail()
                .equals(tasks.getTaskAuthor().getEmail())){

            tasks.getTaskAuthor().setEmail(tasksDto.getTaskAuthor().getEmail());
        }
        if(tasksDto.getTaskAuthor().getSurname() != null && !tasksDto.getTaskAuthor().getSurname()
                .equals(tasks.getTaskAuthor().getSurname())){

            tasks.getTaskAuthor().setSurname(tasksDto.getTaskAuthor().getSurname());
        }
        if(tasksDto.getTaskAuthor().getEmail() != null && !tasksDto.getTaskAuthor().getEmail()
                .equals(tasks.getTaskAuthor().getEmail())){

            tasks.getTaskAuthor().setEmail(tasksDto.getTaskAuthor().getEmail());
        }
        //executor
        if(tasksDto.getTaskExecutor().getName() != null && !tasksDto.getTaskExecutor().getName()
                .equals(tasks.getTaskExecutor().getName())){

            tasks.getTaskExecutor().setName(tasksDto.getTaskExecutor().getName());
        }
        if(tasksDto.getTaskExecutor().getSurname() != null && !tasksDto.getTaskExecutor().getSurname()
                .equals(tasks.getTaskExecutor().getSurname())){

            tasks.getTaskExecutor().setSurname(tasksDto.getTaskExecutor().getSurname());
        }
        if(tasksDto.getTaskExecutor().getEmail() != null && !tasksDto.getTaskExecutor().getEmail()
                .equals(tasks.getTaskExecutor().getEmail())){

            tasks.getTaskExecutor().setEmail(tasksDto.getTaskExecutor().getEmail());
        }
        //desctiption
        if(tasksDto.getDescription() != null && !tasksDto.getDescription()
                .equals(tasks.getDescription())){

            tasks.setDescription(tasksDto.getDescription());
        }
        //priority
        if(tasksDto.getTaskPriority() != null && !tasksDto.getTaskPriority()
                .equals(tasks.getTaskPriority())){

            tasks.setTaskPriority(tasksDto.getTaskPriority());
        }
        //status
        if(tasksDto.getTaskStatus() != null && !tasksDto.getTaskStatus()
                .equals(tasks.getTaskStatus())){

            tasks.setTaskStatus(tasksDto.getTaskStatus());
        }*/
        return tasks;
    }
}
