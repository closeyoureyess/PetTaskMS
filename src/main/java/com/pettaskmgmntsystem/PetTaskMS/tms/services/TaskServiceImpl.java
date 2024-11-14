package com.pettaskmgmntsystem.PetTaskMS.tms.services;

import com.pettaskmgmntsystem.PetTaskMS.authorization.dto.CustomUsersDto;
import com.pettaskmgmntsystem.PetTaskMS.auxiliaryclasses.ValidationClass;
import com.pettaskmgmntsystem.PetTaskMS.fabrics.ActionsFabric;
import com.pettaskmgmntsystem.PetTaskMS.fabrics.MappersFabric;
import com.pettaskmgmntsystem.PetTaskMS.authorization.repository.AuthorizationRepository;
import com.pettaskmgmntsystem.PetTaskMS.authorization.repository.CustomUsers;
import com.pettaskmgmntsystem.PetTaskMS.constants.ConstantsClass;
import com.pettaskmgmntsystem.PetTaskMS.exeptions.DescriptionUserExeption;
import com.pettaskmgmntsystem.PetTaskMS.exeptions.ExecutorNotFoundExeption;
import com.pettaskmgmntsystem.PetTaskMS.exeptions.NotEnoughRulesEntity;
import com.pettaskmgmntsystem.PetTaskMS.auxiliaryclasses.ValidationClassImpl;
import com.pettaskmgmntsystem.PetTaskMS.tms.dto.NotesDto;
import com.pettaskmgmntsystem.PetTaskMS.tms.dto.TasksDto;
import com.pettaskmgmntsystem.PetTaskMS.tms.repository.Notes;
import com.pettaskmgmntsystem.PetTaskMS.tms.repository.Tasks;
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
public class TaskServiceImpl implements TaskService {

    @Autowired
    private MappersFabric mappersFabric;
    @Autowired
    private ActionsFabric actionsFabric;
    @Autowired
    private TasksRepository tasksRepository;
    @Autowired
    private AuthorizationRepository authorizationRepository;

    @Override
    public TasksDto createTasks(TasksDto tasksDto) throws UsernameNotFoundException, ExecutorNotFoundExeption {
        Optional<CustomUsers> optionalAuthorizedUser = actionsFabric.createUserActions().getCurrentUser();
        tasksDto.setTaskAuthor(mappersFabric.createUserMapper().convertUserToDto(optionalAuthorizedUser.get()));
        Tasks newTasks = mappersFabric.createTaskMapper().convertDtoToTasks(tasksDto);
        newTasks = actionsFabric.createUserActions().checkFindUser(newTasks.getTaskExecutor(), newTasks, ConstantsClass.REGIME_RECORD);
        newTasks = actionsFabric.createUserActions().checkFindUser(newTasks.getTaskAuthor(), newTasks, ConstantsClass.REGIME_OVERWRITING);
        tasksRepository.save(newTasks); // save to PostgreSQL
        hidePasswordTasksUsers(newTasks);
        return mappersFabric.createTaskMapper().convertTasksToDto(newTasks);
    }

    @Override
    public TasksDto changeTasks(TasksDto tasksDto) throws ExecutorNotFoundExeption, NotEnoughRulesEntity {
        Optional<Tasks> optionalTaskDatabase = Optional.empty();
        if (tasksDto.getId() != null) {
            optionalTaskDatabase = tasksRepository.findById(mappersFabric.createTaskMapper().convertDtoToTasks(tasksDto).getId());
        }
        if (optionalTaskDatabase.isPresent()) {
            TasksDto newTasksDto = mappersFabric.createTaskMapper().convertTasksToDto(optionalTaskDatabase.get());

            boolean resultCheckPrivilege = checkPrivilegeTasks(newTasksDto, tasksDto);
            if (!resultCheckPrivilege) {
                return null;
            }

            if (tasksDto.getTaskAuthor() == null) {
                tasksDto.setTaskAuthor(newTasksDto.getTaskAuthor());
            }
            Tasks newTasks = mappersFabric.createTaskMapper().compareTaskAndDto(tasksDto, optionalTaskDatabase.get());
            newTasks = tasksRepository.save(newTasks);
            return mappersFabric.createTaskMapper().convertTasksToDto(newTasks);
        }
        return null;
    }

    @Override
    public Optional<List<TasksDto>> getTasksOfAuthorOrExecutor(String authorOrExecutor, Integer offset, Integer limit, Integer flag) {
        if (flag.equals(ConstantsClass.REGIME_RECORD)) {
            return Optional.of(receiveAllTasksAuthorOrExecutorDataBase(authorOrExecutor, offset, limit, ConstantsClass.REGIME_RECORD));
        } else if (flag.equals(ConstantsClass.REGIME_OVERWRITING)) {
            return Optional.of(receiveAllTasksAuthorOrExecutorDataBase(authorOrExecutor, offset, limit, ConstantsClass.REGIME_OVERWRITING));
        }
        return Optional.empty();
    }

    @Override
    public boolean deleteTasks(Integer idTasks) {
        boolean resultDeleteTasks = tasksRepository.existsById(idTasks);
        if (resultDeleteTasks) {
            tasksRepository.deleteById(idTasks);
            return true;
        }
        return false;
    }

    private List<TasksDto> receiveAllTasksAuthorOrExecutorDataBase(String authorOrExecutor, Integer offset, Integer limit, Integer flag) {
        ValidationClass validationClass = new ValidationClassImpl();
        List<Tasks> listAllTasks = new LinkedList<>();

        Optional<ValidationClassImpl> resultValid = validationClass.validEmailOrId(authorOrExecutor);
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
        return mappersFabric.createTaskMapper().transferListTasksToDto(listAllTasks);
    }

    private Optional<Page<Tasks>> methodFindAllTasksAuthorOrExecutor(Pageable pageble, Integer userId, Integer flag) {
        if (flag.equals(ConstantsClass.REGIME_RECORD)) {
            return Optional.of(tasksRepository.findAllByTaskAuthorId(userId, pageble));
        } else if (flag.equals(ConstantsClass.REGIME_OVERWRITING)) {
            return Optional.of(tasksRepository.findAllByTaskExecutorId(userId, pageble));
        }
        return Optional.empty();
    }

    private boolean checkPrivilegeTasks(TasksDto tasksDtoFromDB, TasksDto tasksDto) throws NotEnoughRulesEntity {
        CustomUsersDto userDtoAuthorTaskDB = tasksDtoFromDB.getTaskAuthor();
        CustomUsersDto userCurrentDtoExecutorTaskDB = tasksDto.getTaskExecutor();
        boolean availabilityRules = actionsFabric.createTasksActions().isPrivilegeTasks(userDtoAuthorTaskDB);
        if (availabilityRules) {
            return true;
        } else {
            if (actionsFabric.createTasksActions().isPrivilegeTasks(userCurrentDtoExecutorTaskDB)) {
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
