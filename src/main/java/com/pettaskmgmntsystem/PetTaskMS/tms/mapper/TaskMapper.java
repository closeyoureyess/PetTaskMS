package com.pettaskmgmntsystem.PetTaskMS.tms.mapper;

import com.pettaskmgmntsystem.PetTaskMS.authorization.auxiliaryclasses.UserActions;
import com.pettaskmgmntsystem.PetTaskMS.authorization.mapper.UserMapper;
import com.pettaskmgmntsystem.PetTaskMS.authorization.repository.AuthorizationRepository;
import com.pettaskmgmntsystem.PetTaskMS.constants.ConstantsClass;
import com.pettaskmgmntsystem.PetTaskMS.exeptions.DescriptionUserExeption;
import com.pettaskmgmntsystem.PetTaskMS.tms.dto.TasksDto;
import com.pettaskmgmntsystem.PetTaskMS.tms.repository.Tasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

@Component
public class TaskMapper {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NotesMapper notesMapper;
    @Autowired
    AuthorizationRepository authorizationRepository;
    @Autowired
    UserActions userActions;

    public Tasks convertDtoToTasks(TasksDto tasksDto) {
        Tasks taskLocalObject = new Tasks();
        taskLocalObject.setId(tasksDto.getId());
        if (tasksDto.getTaskExecutor() != null) {
            taskLocalObject.setTaskExecutor(userMapper.convertDtoToUser(tasksDto.getTaskExecutor()));
        }
        taskLocalObject.setTaskAuthor(userMapper.convertDtoToUser(tasksDto.getTaskAuthor()));
        if (tasksDto.getTaskPriority() != null) {
            taskLocalObject.setTaskPriority(tasksDto.getTaskPriority());
        }
        if (tasksDto.getTaskStatus() != null) {
            taskLocalObject.setTaskStatus(tasksDto.getTaskStatus());
        }
        if (tasksDto.getDescription() != null) {
            taskLocalObject.setDescription(tasksDto.getDescription());
        }
        if (tasksDto.getHeader() != null) {
            taskLocalObject.setHeader(tasksDto.getHeader());
        }
        if (tasksDto.getNotesDto() != null) {
            taskLocalObject.setNotes(notesMapper.convertDtoToNotes(tasksDto.getNotesDto()));
        }
        return taskLocalObject;
    }

    public TasksDto convertTasksToDto(Tasks tasks) {
        TasksDto tasksDtoLocalObject = new TasksDto();
        tasksDtoLocalObject.setId(tasks.getId());
        if (tasks.getTaskExecutor() != null) {
            tasksDtoLocalObject.setTaskExecutor(userMapper.convertUserToDto(tasks.getTaskExecutor()));
        }
        if (tasks.getTaskAuthor() != null) {
            tasksDtoLocalObject.setTaskAuthor(userMapper.convertUserToDto(tasks.getTaskAuthor()));
        }
        if (tasks.getTaskPriority() != null) {
            tasksDtoLocalObject.setTaskPriority(tasks.getTaskPriority());
        }
        if (tasks.getTaskStatus() != null) {
            tasksDtoLocalObject.setTaskStatus(tasks.getTaskStatus());
        }
        if (tasks.getDescription() != null) {
            tasksDtoLocalObject.setDescription(tasks.getDescription());
        }
        if (tasks.getHeader() != null) {
            tasksDtoLocalObject.setHeader(tasks.getHeader());
        }
        if (tasks.getNotes() != null) {
            tasksDtoLocalObject.setNotesDto(notesMapper.convertNotesToDto(tasks.getNotes()));
        }
        return tasksDtoLocalObject;
    }

    public Tasks compareTaskAndDto(TasksDto tasksDto, Tasks tasks) throws UsernameNotFoundException {
        //author
        tasks = compareTaskAndDtoAuthor(tasksDto, tasks);
        //executor
        tasks = compareTasksAndDtoExecutor(tasksDto, tasks);
        //desctiption
        tasks = compareTasksAndDtoDescription(tasksDto, tasks);
        //priority
        tasks = compareTasksAndDtoPriority(tasksDto, tasks);
        //header
        tasks = compareTasksAndDtoHeader(tasksDto, tasks);
        //status
        tasks = compareTasksAndDtoStatus(tasksDto, tasks);
        //notes
        tasks = compareTasksAndDtoNotes(tasksDto, tasks);
        return tasks;
    }

    private Tasks compareTaskAndDtoAuthor(TasksDto tasksDto, Tasks tasks) throws UsernameNotFoundException {
        if (!tasksDto.getTaskAuthor().getEmail().equals(tasks.getTaskAuthor().getEmail())
                ||
                !tasksDto.getTaskAuthor().getId().equals(tasks.getTaskAuthor().getId())) {

            Tasks newTasks = userActions.checkFindUser(userMapper.convertDtoToUser(tasksDto.getTaskAuthor()), tasks,
                    ConstantsClass.REGIME_OVERWRITING);
            if (newTasks != null) {
                tasks.setTaskAuthor(newTasks.getTaskAuthor());
            }
        } else {
            throw new UsernameNotFoundException(DescriptionUserExeption.USER_NOT_FOUND.getEnumUser());
        }
        return tasks;
    }

    private Tasks compareTasksAndDtoExecutor(TasksDto tasksDto, Tasks tasks) throws UsernameNotFoundException {
        if (!tasksDto.getTaskExecutor().getEmail().equals(tasks.getTaskExecutor().getEmail())
                ||
                !tasksDto.getTaskExecutor().getId().equals(tasks.getTaskExecutor().getId())) {

            Tasks newTasks = userActions.checkFindUser(userMapper.convertDtoToUser(tasksDto.getTaskExecutor()), tasks,
                    ConstantsClass.REGIME_RECORD);
            if (newTasks != null) {
                tasks.setTaskExecutor(newTasks.getTaskExecutor());
            }
        } else {
            throw new UsernameNotFoundException(DescriptionUserExeption.USER_NOT_FOUND.getEnumUser());
        }
        return tasks;
    }

    private Tasks compareTasksAndDtoDescription(TasksDto tasksDto, Tasks tasks) {
        if (tasksDto.getDescription() != null && !tasksDto.getDescription()
                .equals(tasks.getDescription())) {

            tasks.setDescription(tasksDto.getDescription());
        }
        return tasks;
    }

    private Tasks compareTasksAndDtoPriority(TasksDto tasksDto, Tasks tasks) {
        if (tasksDto.getTaskPriority() != null && !tasksDto.getTaskPriority()
                .equals(tasks.getTaskPriority())) {

            tasks.setTaskPriority(tasksDto.getTaskPriority());
        }
        return tasks;
    }

    private Tasks compareTasksAndDtoHeader(TasksDto tasksDto, Tasks tasks) {
        if (tasksDto.getHeader() != null && !tasksDto.getHeader().equals(tasks.getHeader())) {

            tasks.setHeader(tasksDto.getHeader());
        }
        return tasks;
    }

    private Tasks compareTasksAndDtoStatus(TasksDto tasksDto, Tasks tasks) {
        if ((tasks.getTaskExecutor().getEmail().equals(userActions.getCurrentUser().get().getEmail()))
                && (tasksDto.getTaskStatus() != null && !tasksDto.getTaskStatus().equals(tasks.getTaskStatus()))) {

            tasks.setTaskStatus(tasksDto.getTaskStatus());
        }
        return tasks;
    }

    private Tasks compareTasksAndDtoNotes(TasksDto tasksDto, Tasks tasks) {
        if ((tasksDto.getNotesDto() != null) && !tasksDto.getNotesDto().getComments()
                .equals(tasks.getNotes().getComments())) {

            tasks.getNotes().setComments(tasks.getNotes().getComments());
            tasks.getNotes().setUsers(userActions.getCurrentUser().get());
        }
        return tasks;
    }
}
