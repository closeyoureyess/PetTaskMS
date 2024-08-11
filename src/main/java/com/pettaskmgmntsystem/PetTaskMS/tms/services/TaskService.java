package com.pettaskmgmntsystem.PetTaskMS.tms.services;

import com.pettaskmgmntsystem.PetTaskMS.authorization.auxiliaryclasses.UserActions;
import com.pettaskmgmntsystem.PetTaskMS.authorization.mapper.UserMapper;
import com.pettaskmgmntsystem.PetTaskMS.authorization.repository.AuthorizationRepository;
import com.pettaskmgmntsystem.PetTaskMS.authorization.repository.CustomUsers;
import com.pettaskmgmntsystem.PetTaskMS.constants.ConstantsClass;
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

    public TasksDto createTasks(TasksDto tasksDto) throws UsernameNotFoundException {
        if(tasksDto.getTaskExecutor() == null){
            log.info("TESSSSSSSSSSSSSSSSTTTTTTTT" + tasksDto.getTaskExecutor().getEmail());
        } else {
            log.info("BAAAAAAABBBBBB" + tasksDto.getTaskExecutor().getEmail());
        }
        Tasks newTasks;
        Optional<CustomUsers> optionalAuthorizedUser = userActions.getCurrentUser();
        tasksDto.setTaskAuthor(userMapper.convertUserToDto(optionalAuthorizedUser.get()));
        newTasks = taskMapper.convertDtoToTasks(tasksDto);
        CustomUsers customUsers = newTasks.getTaskExecutor();
        newTasks = userActions.checkFindUser(customUsers, newTasks, ConstantsClass.REGIME_RECORD);
        newTasks = userActions.checkFindUser(newTasks.getTaskAuthor(), newTasks, ConstantsClass.REGIME_OVERWRITING);
        return taskMapper.convertTasksToDto(tasksRepository.save(newTasks));
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
