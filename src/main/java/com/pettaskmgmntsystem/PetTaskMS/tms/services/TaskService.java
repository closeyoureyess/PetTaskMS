package com.pettaskmgmntsystem.PetTaskMS.tms.services;

import com.pettaskmgmntsystem.PetTaskMS.authorization.auxiliaryclasses.UserActions;
import com.pettaskmgmntsystem.PetTaskMS.authorization.dto.CustomUsersDto;
import com.pettaskmgmntsystem.PetTaskMS.authorization.mapper.UserMapper;
import com.pettaskmgmntsystem.PetTaskMS.authorization.repository.AuthorizationRepository;
import com.pettaskmgmntsystem.PetTaskMS.authorization.repository.CustomUsers;
import com.pettaskmgmntsystem.PetTaskMS.constants.ConstantsClass;
import com.pettaskmgmntsystem.PetTaskMS.exeptions.ExecutorNotFoundExeption;
import com.pettaskmgmntsystem.PetTaskMS.tms.dto.TasksDto;
import com.pettaskmgmntsystem.PetTaskMS.tms.repository.Tasks;
import com.pettaskmgmntsystem.PetTaskMS.tms.mapper.TaskMapper;
import com.pettaskmgmntsystem.PetTaskMS.tms.repository.TasksRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class TaskService {

    @Autowired
    UserMapper userMapper;
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private TasksRepository tasksRepository;
    @Autowired
    private AuthorizationRepository authorizationRepository;
    @Autowired
    private UserActions userActions;

    public TasksDto createTasks(TasksDto tasksDto) throws UsernameNotFoundException, ExecutorNotFoundExeption {
        Optional<CustomUsers> optionalAuthorizedUser = userActions.getCurrentUser();
        tasksDto.setTaskAuthor(userMapper.convertUserToDto(optionalAuthorizedUser.get()));
        Tasks newTasks = taskMapper.convertDtoToTasks(tasksDto);
        newTasks = userActions.checkFindUser(newTasks.getTaskExecutor(), newTasks, ConstantsClass.REGIME_RECORD);
        newTasks = userActions.checkFindUser(newTasks.getTaskAuthor(), newTasks, ConstantsClass.REGIME_OVERWRITING);
        tasksRepository.save(newTasks); // save to PostgreSQL
        hidePassword(newTasks);
        return taskMapper.convertTasksToDto(newTasks);
    }

    public TasksDto changeTasks(TasksDto tasksDto) throws ExecutorNotFoundExeption {
        Optional<Tasks> optionalTaskDatabase = Optional.empty();
        Tasks newTasks = taskMapper.convertDtoToTasks(tasksDto);
        if (tasksDto.getId() != null) {
            optionalTaskDatabase = tasksRepository.findById(newTasks.getId());
        }
        if (optionalTaskDatabase.isPresent()) {
            if (tasksDto.getTaskAuthor() == null){
                tasksDto.setTaskAuthor(userMapper.convertUserToDto(optionalTaskDatabase.get().getTaskAuthor()));
            }
            newTasks = taskMapper.compareTaskAndDto(tasksDto, optionalTaskDatabase.get());
            newTasks = tasksRepository.save(newTasks);
        }
        return taskMapper.convertTasksToDto(newTasks);
    }

    public boolean deleteTasks(CustomUsersDto customUsersDto){
       boolean resultDeleteTasks = tasksRepository.existsById(customUsersDto.getId());
       if (resultDeleteTasks){
           tasksRepository.deleteById(customUsersDto.getId());
           return true;
       }
       return false;
    }

    private void hidePassword(Tasks newTasks, TasksDto... tasksDto) {
        if (newTasks.getTaskAuthor() != null) {
            newTasks.getTaskAuthor().setPasswordKey(ConstantsClass.HIDE);
        }
        if (newTasks.getTaskExecutor() != null) {
            newTasks.getTaskExecutor().setPasswordKey(ConstantsClass.HIDE);
        }
        if (newTasks.getNotes() != null && newTasks.getNotes().getComments() != null) {
            newTasks.getNotes().getUsers().setPasswordKey(ConstantsClass.HIDE);
        }
    }
}
