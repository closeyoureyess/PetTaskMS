package com.pettaskmgmntsystem.PetTaskMS.authorization;


import com.pettaskmgmntsystem.PetTaskMS.authorization.auxiliaryclasses.CustomUsers;
import com.pettaskmgmntsystem.PetTaskMS.authorization.dto.CustomUsersDto;
import com.pettaskmgmntsystem.PetTaskMS.authorization.mapper.UserMapper;
import com.pettaskmgmntsystem.PetTaskMS.authorization.repository.AuthorizationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private AuthorizationRepository authorizationRepository;

    @Autowired
    private UserMapper userMapper;

    public CustomUsersDto createUser (CustomUsersDto customUsersDto){
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        CustomUsers customUsers = userMapper.convertDtoToUser(customUsersDto);
        customUsers.setPasswordKey(passwordEncoder.encode(customUsers.getPasswordKey()));
        return userMapper.convertUserToDto(
                authorizationRepository.save(customUsers)
        );
    }

}
