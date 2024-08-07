package com.pettaskmgmntsystem.PetTaskMS.authorization.service;

import com.pettaskmgmntsystem.PetTaskMS.constants.ConstantsClass;
import com.pettaskmgmntsystem.PetTaskMS.exeptions.DescriptionUserExeption;
import com.pettaskmgmntsystem.PetTaskMS.authorization.auxiliaryclasses.CustomUsers;
import com.pettaskmgmntsystem.PetTaskMS.authorization.repository.AuthorizationRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class MyUserDetailService implements UserDetailsService {

    @Autowired
    private AuthorizationRepository authorizationRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<CustomUsers> userFromDB = authorizationRepository.findByEmail(email);
        if(userFromDB.isPresent()) {
            CustomUsers newCustomUsers = userFromDB.get();
            return User.builder()
                    .username(newCustomUsers.getEmail())
                    .password(newCustomUsers.getPasswordKey())
                    .roles(getRoles(newCustomUsers))
                    .build();
        } else {
            throw new UsernameNotFoundException(DescriptionUserExeption.USER_NOT_FOUND.getEnumUser() + " " + userFromDB.get().getEmail());
        }

    }

    private String getRoles(CustomUsers customUsers) {
        if (customUsers.getRole() == null) {
            return ConstantsClass.USERROLE;
        } else {
            return customUsers.getRole();
        }
    }
}
