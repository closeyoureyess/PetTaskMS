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
import com.pettaskmgmntsystem.PetTaskMS.tms.auxiliaryclasses.ValidationClass;
import com.pettaskmgmntsystem.PetTaskMS.tms.dto.NotesDto;
import com.pettaskmgmntsystem.PetTaskMS.tms.dto.TasksDto;
import com.pettaskmgmntsystem.PetTaskMS.tms.repository.Notes;
import com.pettaskmgmntsystem.PetTaskMS.tms.repository.Tasks;
import com.pettaskmgmntsystem.PetTaskMS.tms.mapper.TaskMapper;
import com.pettaskmgmntsystem.PetTaskMS.tms.repository.TasksRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
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
            if (!resultCheckPrivilege) {
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

    public Optional<List<TasksDto>> getTasksOfAuthorOrExecutor(String authorOrExecutor, Integer offset, Integer limit, Integer flag) {
        if (flag.equals(ConstantsClass.REGIME_RECORD)) {
            return Optional.of(receiveAllTasksAuthorOrExecutorDataBase(authorOrExecutor, offset, limit, ConstantsClass.REGIME_RECORD));
        } else if (flag.equals(ConstantsClass.REGIME_OVERWRITING)) {
            return Optional.of(receiveAllTasksAuthorOrExecutorDataBase(authorOrExecutor, offset, limit, ConstantsClass.REGIME_OVERWRITING));
        }
        return Optional.empty();
    }


    public boolean deleteTasks(Integer idTasks) {
        boolean resultDeleteTasks = tasksRepository.existsById(idTasks);
        if (resultDeleteTasks) {
            tasksRepository.deleteById(idTasks);
            return true;
        }
        return false;
    }

    private List<TasksDto> receiveAllTasksAuthorOrExecutorDataBase(String authorOrExecutor, Integer offset, Integer limit, Integer flag) {
        ValidationClass validationClass = new ValidationClass();
        List<Tasks> listAllTasks = new LinkedList<>();

        Optional<ValidationClass> resultValid = validationClass.validEmailOrId(authorOrExecutor);
        if (resultValid.isPresent()) {

            Pageable pageble = PageRequest.of(offset, limit);
            String userEmail = resultValid.get().getValidationString();
            Integer userId = resultValid.get().getValidationInteger();

            if (userEmail != null) {
                Optional<CustomUsers> optionalCustomUsers = authorizationRepository.findByEmail(userEmail);
                if (optionalCustomUsers.isPresent()) {
                    Optional<Page<Tasks>> pageWithTasks = methodFindAllTasksAuthorOrExecutor(pageble, optionalCustomUsers.get().getId(), flag);
                    if (pageWithTasks.isPresent()) {
                        listAllTasks = pageWithTasks.get().stream().toList();
                    }
                }
            } else if (userId != null) {
                Optional<Page<Tasks>> pageWithTasks = methodFindAllTasksAuthorOrExecutor(pageble, userId, flag);
                if (pageWithTasks.isPresent()) {
                    listAllTasks = pageWithTasks.get().stream().toList();
                }
            }

        }
        return taskMapper.transferListTasksToDto(listAllTasks);
    }

    private Optional<Page<Tasks>> methodFindAllTasksAuthorOrExecutor(Pageable pageble, Integer userId,
                                                                     Integer flag) {
        if (flag.equals(ConstantsClass.REGIME_RECORD)) {
            return Optional.of(tasksRepository.findAllByTasksAuthorId(userId, pageble));
        } else if (flag.equals(ConstantsClass.REGIME_OVERWRITING)) {
            return Optional.of(tasksRepository.findAllByTasksExecutorId(userId, pageble));
        }
        return Optional.empty();
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
