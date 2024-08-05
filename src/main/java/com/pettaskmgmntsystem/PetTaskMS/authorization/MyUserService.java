package com.pettaskmgmntsystem.PetTaskMS.authorization;

import com.pettaskmgmntsystem.PetTaskMS.authorization.auxiliaryclasses.CustomUsers;
import com.pettaskmgmntsystem.PetTaskMS.authorization.repository.AuthorizationRepository;
import com.pettaskmgmntsystem.PetTaskMS.customexeprions.EntityNotFoundExeption;
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
public class MyUserService implements UserDetailsService {

    @Autowired
    private AuthorizationRepository authorizationRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Optional<CustomUsers> userFromDB = authorizationRepository.findByEmail(email);
        if(userFromDB.isPresent()){
            CustomUsers newCustomUsers = userFromDB.get();
            return User.builder()
                    .username(newCustomUsers.getName())
                    .password(newCustomUsers.getPasswordKey())
                    .roles(getRoles(newCustomUsers))
                    .build();
        }
        log.error("Такого пользователя не существует");
        return null;
    }

    private String getRoles(CustomUsers customUsers) throws EntityNotFoundExeption {
        String resultRole = customUsers.getRole();
        if(!(resultRole == null)){
            return resultRole;
        } else {
            throw new EntityNotFoundExeption("У юзера отсутствуе роль");
        }
    }
}
