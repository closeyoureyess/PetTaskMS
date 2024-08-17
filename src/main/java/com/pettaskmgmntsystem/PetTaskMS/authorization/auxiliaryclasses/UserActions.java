package com.pettaskmgmntsystem.PetTaskMS.authorization.auxiliaryclasses;

import com.pettaskmgmntsystem.PetTaskMS.authorization.repository.AuthorizationRepository;
import com.pettaskmgmntsystem.PetTaskMS.authorization.repository.CustomUsers;
import com.pettaskmgmntsystem.PetTaskMS.constants.ConstantsClass;
import com.pettaskmgmntsystem.PetTaskMS.exeptions.DescriptionUserExeption;
import com.pettaskmgmntsystem.PetTaskMS.tms.auxiliaryclasses.TasksActions;
import com.pettaskmgmntsystem.PetTaskMS.tms.repository.Tasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserActions {

    @Autowired
    private AuthorizationRepository authorizationRepository;

    @Autowired
    private TasksActions tasksActions;

    public Tasks checkFindUser(CustomUsers customUsers, Tasks newTasks, Integer typeOperations) throws UsernameNotFoundException {
        Optional<CustomUsers> optionalCustomUsers = searchUserEmailOrId(customUsers);

        if (optionalCustomUsers.isPresent() && (typeOperations.equals(ConstantsClass.REGIME_RECORD))) {
            newTasks.setTaskExecutor(optionalCustomUsers.get());
            return newTasks;
        } else if (optionalCustomUsers.isPresent() && typeOperations.equals(ConstantsClass.REGIME_OVERWRITING)) {
            newTasks.setTaskAuthor(optionalCustomUsers.get());
            return newTasks;
        } else {
            throw new UsernameNotFoundException(DescriptionUserExeption.USER_NOT_FOUND.getEnumDescription());
        }
    }

    public Optional<CustomUsers> getCurrentUser() {
        System.out.println("ТЕКУЩИЙ ЮЗЕР " + SecurityContextHolder.getContext().getAuthentication().getName());
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        return authorizationRepository.findByEmail(loggedInUser.getName());
    }

    public String getEmailCurrentUser(){
        return getCurrentUser().get().getEmail();
    }

    public Optional<CustomUsers> searchUserEmailOrId(CustomUsers customUsers) throws UsernameNotFoundException {
        Optional<CustomUsers> optionalCustomUsers = Optional.empty();
        if (customUsers != null) {
            if (customUsers.getEmail() != null) {
                optionalCustomUsers = authorizationRepository.findByEmail(customUsers.getEmail());
            } else if (customUsers.getId() != null) {
                optionalCustomUsers = authorizationRepository.findById(customUsers.getId());
            }
            if (optionalCustomUsers.isEmpty()){
                throw new UsernameNotFoundException(DescriptionUserExeption.USER_NOT_FOUND.getEnumDescription());
            }
        }
        return optionalCustomUsers;
    }
}
