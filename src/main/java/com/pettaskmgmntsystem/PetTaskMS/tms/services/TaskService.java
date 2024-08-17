package com.pettaskmgmntsystem.PetTaskMS.tms.services;

import com.pettaskmgmntsystem.PetTaskMS.authorization.auxiliaryclasses.UserActions;
import com.pettaskmgmntsystem.PetTaskMS.authorization.dto.CustomUsersDto;
import com.pettaskmgmntsystem.PetTaskMS.authorization.mapper.UserMapper;
import com.pettaskmgmntsystem.PetTaskMS.authorization.repository.AuthorizationRepository;
import com.pettaskmgmntsystem.PetTaskMS.authorization.repository.CustomUsers;
import com.pettaskmgmntsystem.PetTaskMS.constants.ConstantsClass;
import com.pettaskmgmntsystem.PetTaskMS.exeptions.DescriptionUserExeption;
import com.pettaskmgmntsystem.PetTaskMS.exeptions.ExecutorNotFoundExeption;
import com.pettaskmgmntsystem.PetTaskMS.exeptions.NotEnoughRulesEntity;
import com.pettaskmgmntsystem.PetTaskMS.tms.auxiliaryclasses.TasksActions;
import com.pettaskmgmntsystem.PetTaskMS.tms.dto.NotesDto;
import com.pettaskmgmntsystem.PetTaskMS.tms.dto.TasksDto;
import com.pettaskmgmntsystem.PetTaskMS.tms.repository.Notes;
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
    private UserMapper userMapper;
    @Autowired
    private TaskMapper taskMapper;
    @Autowired
    private TasksRepository tasksRepository;
    @Autowired
    private AuthorizationRepository authorizationRepository;
    @Autowired
    private UserActions userActions;
    @Autowired
    private TasksActions tasksActions;

    public TasksDto createTasks(TasksDto tasksDto) throws UsernameNotFoundException, ExecutorNotFoundExeption {
        Optional<CustomUsers> optionalAuthorizedUser = userActions.getCurrentUser();
        tasksDto.setTaskAuthor(userMapper.convertUserToDto(optionalAuthorizedUser.get()));
        Tasks newTasks = taskMapper.convertDtoToTasks(tasksDto);
        newTasks = userActions.checkFindUser(newTasks.getTaskExecutor(), newTasks, ConstantsClass.REGIME_RECORD);
        newTasks = userActions.checkFindUser(newTasks.getTaskAuthor(), newTasks, ConstantsClass.REGIME_OVERWRITING);
        tasksRepository.save(newTasks); // save to PostgreSQL
        hidePasswordTasksUsers(newTasks);
        return taskMapper.convertTasksToDto(newTasks);
    }

    public TasksDto changeTasks(TasksDto tasksDto) throws ExecutorNotFoundExeption, NotEnoughRulesEntity {
        Optional<Tasks> optionalTaskDatabase = Optional.empty();
        if (tasksDto.getId() != null) {
            optionalTaskDatabase = tasksRepository.findById(taskMapper.convertDtoToTasks(tasksDto).getId());
        }
        if (optionalTaskDatabase.isPresent()) {
            TasksDto newTasksDto = taskMapper.convertTasksToDto(optionalTaskDatabase.get());

            boolean resultCheckPrivilege = checkPrivilegeTasks(newTasksDto, tasksDto);
            if (!resultCheckPrivilege){
                return null;
            }

            if (tasksDto.getTaskAuthor() == null) {
                tasksDto.setTaskAuthor(newTasksDto.getTaskAuthor());
            }
            Tasks newTasks = taskMapper.compareTaskAndDto(tasksDto, optionalTaskDatabase.get());
            newTasks = tasksRepository.save(newTasks);
            return taskMapper.convertTasksToDto(newTasks);
        }
        return null;
    }

    /*public TasksDto getTasks(String) {
        tasksRepository.findById()
    }
*/
    public boolean deleteTasks(Integer idTasks) {
        boolean resultDeleteTasks = tasksActions.checkExistTasks(idTasks);
        if (resultDeleteTasks) {
            tasksRepository.deleteById(idTasks);
            return true;
        }
        return false;
    }

    private boolean checkPrivilegeTasks(TasksDto tasksDtoFromDB, TasksDto tasksDto) throws NotEnoughRulesEntity {
        CustomUsersDto userDtoAuthorTaskDB = tasksDtoFromDB.getTaskAuthor();
        CustomUsersDto userCurrentDtoExecutorTaskDB = tasksDto.getTaskExecutor();
        boolean availabilityRules = tasksActions.isPrivilegeTasks(userDtoAuthorTaskDB);
        if (availabilityRules) {
            return true;
        } else {
            if (tasksActions.isPrivilegeTasks(userCurrentDtoExecutorTaskDB)) {
                return isFieldsTasksDtoIsNullOrNot(tasksDto);
            } else {
                return false;
            }
        }
    }

    private boolean isFieldsTasksDtoIsNullOrNot(TasksDto tasksDto) throws NotEnoughRulesEntity {
        if ((tasksDto.getNotesDto() == null
                && tasksDto.getTaskPriority() == null && tasksDto.getTaskAuthor() == null
                && tasksDto.getTaskExecutor() == null && tasksDto.getDescription() == null && tasksDto.getHeader() == null)
                ||
                (tasksDto.getId() != null && tasksDto.getTaskStatus() != null)
        ) {
            return true;
        } else {
            throw new NotEnoughRulesEntity(DescriptionUserExeption.NOT_ENOUGH_RULES_EXECUTOR.getEnumDescription());
        }
    }

    private void hidePasswordTasksUsers(Tasks newTasks, TasksDto... tasksDto) {
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

    private void hidePasswordNotesUsers(Notes newNotes, NotesDto... notesDto) {
        if (newNotes.getUsers() != null) {
            newNotes.getUsers().setPasswordKey(ConstantsClass.HIDE);
        }
    }

}
