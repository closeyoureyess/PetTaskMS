package com.pettaskmgmntsystem.PetTaskMS.tms.auxiliaryclasses;

import com.pettaskmgmntsystem.PetTaskMS.authorization.auxiliaryclasses.UserActions;
import com.pettaskmgmntsystem.PetTaskMS.authorization.repository.CustomUsers;
import com.pettaskmgmntsystem.PetTaskMS.constants.ConstantsClass;
import com.pettaskmgmntsystem.PetTaskMS.exeptions.DescriptionUserExeption;
import com.pettaskmgmntsystem.PetTaskMS.exeptions.NotEnoughRulesEntity;
import com.pettaskmgmntsystem.PetTaskMS.tms.repository.Tasks;
import com.pettaskmgmntsystem.PetTaskMS.tms.repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class TasksActions {

    @Autowired
    private TasksRepository tasksRepository;

    public boolean compareIntWithConstants(Integer objectInt, Integer constantsInt) {
        if (constantsInt.equals(ConstantsClass.REGIME_RECORD) && objectInt.equals(constantsInt)) {
            return true;
        } else if (constantsInt.equals(ConstantsClass.REGIME_OVERWRITING) && objectInt.equals(constantsInt)) {
            return true;
        }
        return false;
    }

    public boolean isPrivilegeTasks(CustomUsers customUsers, Integer... flagAuthorOrExecutor) throws NotEnoughRulesEntity {
        UserActions userActions = new UserActions();
        String emailCurrentUser = userActions.getEmailCurrentUser();
        if (customUsers.getEmail().equals(emailCurrentUser)) {
            return true;
        } else {
            throw new NotEnoughRulesEntity(DescriptionUserExeption.NOT_ENOUGH_RULES.getEnumDescription());
        }
    }

    public boolean checkExistTasks(Integer idTasks) {
        return tasksRepository.existsById(idTasks);
    }

}
