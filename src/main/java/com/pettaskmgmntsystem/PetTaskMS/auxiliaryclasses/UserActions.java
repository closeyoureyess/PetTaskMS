package com.pettaskmgmntsystem.PetTaskMS.auxiliaryclasses;

import com.pettaskmgmntsystem.PetTaskMS.authorization.repository.CustomUsers;
import com.pettaskmgmntsystem.PetTaskMS.tms.repository.Tasks;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

public interface UserActions {

    Tasks checkFindUser(CustomUsers customUsers, Tasks newTasks, Integer typeOperations) throws UsernameNotFoundException;
    Optional<CustomUsers> getCurrentUser();
    String getEmailCurrentUser();
    Optional<CustomUsers> searchUserEmailOrId(CustomUsers customUsers) throws UsernameNotFoundException;
}
