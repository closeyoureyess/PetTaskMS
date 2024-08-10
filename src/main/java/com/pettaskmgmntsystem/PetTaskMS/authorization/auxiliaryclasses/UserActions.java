package com.pettaskmgmntsystem.PetTaskMS.authorization.auxiliaryclasses;

import com.pettaskmgmntsystem.PetTaskMS.authorization.repository.AuthorizationRepository;
import com.pettaskmgmntsystem.PetTaskMS.authorization.repository.CustomUsers;
import com.pettaskmgmntsystem.PetTaskMS.constants.ConstantsClass;
import com.pettaskmgmntsystem.PetTaskMS.exeptions.DescriptionUserExeption;
import com.pettaskmgmntsystem.PetTaskMS.tms.repository.Tasks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserActions {

    @Autowired
    private AuthorizationRepository authorizationRepository;

    public Tasks checkFindUser(CustomUsers customUsers, Tasks newTasks, Integer typeOperations) throws UsernameNotFoundException {
        Optional<CustomUsers> optionalCustomUsers = searchUserEmailOrId(customUsers);

        if (optionalCustomUsers.isPresent() && (typeOperations.equals(ConstantsClass.REGIME_RECORD))) {
            newTasks.setTaskExecutor(optionalCustomUsers.get());
            return newTasks;
        } else if (optionalCustomUsers.isPresent() && typeOperations.equals(ConstantsClass.REGIME_OVERWRITING)) {
            newTasks.setTaskAuthor(optionalCustomUsers.get());
            return newTasks;
        } else {
            throw new UsernameNotFoundException(DescriptionUserExeption.USER_NOT_FOUND.getEnumUser());
        }
    }

    public Optional<CustomUsers> getCurrentUser() {
        var userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return authorizationRepository.findByEmail(userDetails.getUsername());
    }

    private Optional<CustomUsers> searchUserEmailOrId(CustomUsers customUsers){
        Optional<CustomUsers> optionalCustomUsers = Optional.empty();
        if (customUsers.getEmail() != null) {
            optionalCustomUsers = authorizationRepository.findByEmail(customUsers.getEmail());
        } else if (customUsers.getId() != null) {
            optionalCustomUsers = authorizationRepository.findById(customUsers.getId());
        }
        return optionalCustomUsers;
    }
}
