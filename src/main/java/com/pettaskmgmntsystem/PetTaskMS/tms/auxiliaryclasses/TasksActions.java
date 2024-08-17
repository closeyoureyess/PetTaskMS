package com.pettaskmgmntsystem.PetTaskMS.tms.auxiliaryclasses;

import com.pettaskmgmntsystem.PetTaskMS.authorization.auxiliaryclasses.UserActions;
import com.pettaskmgmntsystem.PetTaskMS.authorization.dto.CustomUsersDto;
import com.pettaskmgmntsystem.PetTaskMS.authorization.mapper.UserMapper;
import com.pettaskmgmntsystem.PetTaskMS.authorization.repository.AuthorizationRepository;
import com.pettaskmgmntsystem.PetTaskMS.authorization.repository.CustomUsers;
import com.pettaskmgmntsystem.PetTaskMS.constants.ConstantsClass;
import com.pettaskmgmntsystem.PetTaskMS.exeptions.DescriptionUserExeption;
import com.pettaskmgmntsystem.PetTaskMS.exeptions.NotEnoughRulesEntity;
import com.pettaskmgmntsystem.PetTaskMS.tms.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class TasksActions {

    @Autowired
    AuthorizationRepository authorizationRepository;

    @Autowired
    private TasksRepository tasksRepository;
    @Autowired
    private UserMapper userMapper;

    public boolean compareIntWithConstants(Integer objectInt, Integer constantsInt) {
        if (constantsInt.equals(ConstantsClass.REGIME_RECORD) && objectInt.equals(constantsInt)) {
            return true;
        } else if (constantsInt.equals(ConstantsClass.REGIME_OVERWRITING) && objectInt.equals(constantsInt)) {
            return true;
        }
        return false;
    }

    public boolean isPrivilegeTasks(CustomUsersDto customUsersDto) throws NotEnoughRulesEntity {
        UserActions userActions = new UserActions();
        String emailCurrentUser = userActions.getEmailCurrentUser();
        if (customUsersDto.getEmail().equals(emailCurrentUser)) {
            return true;
        } else {
            throw new NotEnoughRulesEntity(DescriptionUserExeption.NOT_ENOUGH_RULES.getEnumDescription());
        }
    }

    public boolean checkExistTasks(Integer idTasks) {
        return tasksRepository.existsById(idTasks);
    }

}
