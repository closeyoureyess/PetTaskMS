package com.pettaskmgmntsystem.PetTaskMS.tms.mapper;

import com.pettaskmgmntsystem.PetTaskMS.authorization.auxiliaryclasses.UserActions;
import com.pettaskmgmntsystem.PetTaskMS.authorization.mapper.UserMapper;
import com.pettaskmgmntsystem.PetTaskMS.authorization.repository.AuthorizationRepository;
import com.pettaskmgmntsystem.PetTaskMS.authorization.repository.CustomUsers;
import com.pettaskmgmntsystem.PetTaskMS.constants.ConstantsClass;
import com.pettaskmgmntsystem.PetTaskMS.exeptions.DescriptionUserExeption;
import com.pettaskmgmntsystem.PetTaskMS.exeptions.ExecutorNotFoundExeption;
import com.pettaskmgmntsystem.PetTaskMS.tms.auxiliaryclasses.TasksActions;
import com.pettaskmgmntsystem.PetTaskMS.tms.dto.TasksDto;
import com.pettaskmgmntsystem.PetTaskMS.tms.repository.Tasks;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Slf4j
public class TaskMapper {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private NotesMapper notesMapper;
    @Autowired
    private AuthorizationRepository authorizationRepository;
    @Autowired
    private UserActions userActions;
    @Autowired
    private TasksActions tasksActions;

    public Tasks convertDtoToTasks(TasksDto tasksDto, Integer... method) throws ExecutorNotFoundExeption {
        Tasks taskLocalObject = new Tasks();
        if (tasksDto != null) {
            taskLocalObject.setId(tasksDto.getId());
            if ((tasksDto.getTaskExecutor() == null && method != null)
                    || tasksDto.getTaskExecutor() != null) {

                taskLocalObject.setTaskExecutor(userMapper.convertDtoToUser(tasksDto.getTaskExecutor()));
            } else if (tasksDto.getTaskExecutor() == null && method == null) {
                throw new ExecutorNotFoundExeption(DescriptionUserExeption.EXECUTOR_NOT_SPECIFIED.getEnumDescription());
            }
            taskLocalObject.setTaskAuthor(userMapper.convertDtoToUser(tasksDto.getTaskAuthor()));
            taskLocalObject.setTaskPriority(tasksDto.getTaskPriority());
            taskLocalObject.setTaskStatus(tasksDto.getTaskStatus());
            taskLocalObject.setDescription(tasksDto.getDescription());
            taskLocalObject.setHeader(tasksDto.getHeader());
            if (tasksDto.getNotesDto() != null) {
                taskLocalObject.setNotes(notesMapper.convertDtoToNotes(tasksDto.getNotesDto()));
            }
        }
        return taskLocalObject;
    }

    public TasksDto convertTasksToDto(Tasks tasks) {
        TasksDto tasksDtoLocalObject = new TasksDto();
        if (tasks != null) {
            tasksDtoLocalObject.setId(tasks.getId());
            tasksDtoLocalObject.setTaskExecutor(userMapper.convertUserToDto(tasks.getTaskExecutor()));
            tasksDtoLocalObject.setTaskAuthor(userMapper.convertUserToDto(tasks.getTaskAuthor()));
            tasksDtoLocalObject.setTaskPriority(tasks.getTaskPriority());
            tasksDtoLocalObject.setTaskStatus(tasks.getTaskStatus());
            tasksDtoLocalObject.setDescription(tasks.getDescription());
            tasksDtoLocalObject.setHeader(tasks.getHeader());
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

    private Tasks compareTaskAndDtoAuthor(TasksDto tasksDto, Tasks tasks) {
        Tasks newTasks;
        if ((tasksDto.getTaskAuthor() != null && tasks.getTaskAuthor() != null) // Есть автор
                && (!tasksDto.getTaskAuthor().getEmail().equals(tasks.getTaskAuthor().getEmail()) // Емейл не совпадает
                ||
                !tasksDto.getTaskAuthor().getId().equals(tasks.getTaskAuthor().getId()))) { // ID не совпадает

            newTasks = userActions.checkFindUser(userMapper.convertDtoToUser(tasksDto.getTaskAuthor()), tasks,
                    ConstantsClass.REGIME_OVERWRITING);
            if (newTasks != null) {
                tasks.setTaskAuthor(newTasks.getTaskAuthor());
            }
        } else if ((tasksDto.getTaskAuthor() != null && tasks.getTaskAuthor() == null)
                && (tasksDto.getTaskAuthor().getId() != null || tasksDto.getTaskAuthor().getEmail() != null)) {

            newTasks = userActions.checkFindUser(userMapper.convertDtoToUser(tasksDto.getTaskAuthor()), tasks,
                    ConstantsClass.REGIME_OVERWRITING);
            if (newTasks != null) {
                tasks.setTaskAuthor(newTasks.getTaskAuthor());
            }
        }
        return tasks;
    }

    private Tasks compareTasksAndDtoExecutor(TasksDto tasksDto, Tasks tasks) {
        if (
                (
                        ((tasksDto.getTaskExecutor() != null && tasks.getTaskExecutor() != null))
                        &&
                        (!tasksDto.getTaskExecutor().getEmail().equals(tasks.getTaskExecutor().getEmail())
                                ||
                                !tasksDto.getTaskExecutor().getId().equals(tasks.getTaskExecutor().getId()))
                )

                        || (tasksDto.getTaskExecutor() != null && tasks.getTaskExecutor() == null)
        ) {

            Tasks newTasks = userActions.checkFindUser(userMapper.convertDtoToUser(tasksDto.getTaskExecutor()), tasks,
                    ConstantsClass.REGIME_RECORD);
            if (newTasks != null) {
                tasks.setTaskExecutor(newTasks.getTaskExecutor());
            }
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
        if (/*tasksDto.getTaskPriority() != null && */!tasksDto.getTaskPriority()
                .equals(tasks.getTaskPriority())) {

            tasks.setTaskPriority(tasksDto.getTaskPriority());
        }
        return tasks;
    }

    private Tasks compareTasksAndDtoHeader(TasksDto tasksDto, Tasks tasks) {
        if (/*tasksDto.getHeader() != null && */!tasksDto.getHeader().equals(tasks.getHeader())) {

            tasks.setHeader(tasksDto.getHeader());
        }
        return tasks;
    }

    private Tasks compareTasksAndDtoStatus(TasksDto tasksDto, Tasks tasks) {
        if ((tasks.getTaskExecutor().getEmail().equals(userActions.getEmailCurrentUser()))
                && (tasksDto.getTaskStatus() != null && !tasksDto.getTaskStatus().equals(tasks.getTaskStatus()))) {

            tasks.setTaskStatus(tasksDto.getTaskStatus());
        }
        return tasks;
    }

    private Tasks compareTasksAndDtoNotes(TasksDto tasksDto, Tasks tasks) {
        Optional<CustomUsers> optionalCurrentUser = userActions.getCurrentUser();
        if ((tasksDto.getNotesDto() != null && tasks.getNotes() != null) && optionalCurrentUser.isPresent()) { // Если комментарий уже есть и попытка его отредактировать
            String emailCurrentUser = optionalCurrentUser.get().getEmail();
            if(emailCurrentUser.equals(tasks.getNotes().getUsers().getEmail())){ // проверка, что автор комментария пытается его изменить
                tasks.getNotes().setComments(tasksDto.getNotesDto().getComments());
                tasks.getNotes().setUsers(userActions.getCurrentUser().get());
            }
        }
        return tasks;
    }
}
