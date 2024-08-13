package com.pettaskmgmntsystem.PetTaskMS.authorization.mapper;

import com.pettaskmgmntsystem.PetTaskMS.authorization.repository.CustomUsers;
import com.pettaskmgmntsystem.PetTaskMS.authorization.dto.CustomUsersDto;
import jakarta.validation.constraints.NotNull;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserMapper {

    public CustomUsers convertDtoToUser(CustomUsersDto userDto) {
        CustomUsers usersLocalObject = new CustomUsers();
        if (userDto.getEmail() != null) {
            usersLocalObject.setEmail(userDto.getEmail());
        }
        if (userDto.getPasswordKey() != null) {
            usersLocalObject.setPasswordKey(userDto.getPasswordKey());
        }
        if (userDto.getId() != null) {
            usersLocalObject.setId(userDto.getId());
        }
        if (userDto.getRole() != null) {
            usersLocalObject.setRole(userDto.getRole());
        }
        return usersLocalObject;
    }

    public CustomUsersDto convertUserToDto(CustomUsers users) {
        CustomUsersDto userDtoLocalObject = new CustomUsersDto();
        userDtoLocalObject.setEmail(users.getEmail());
        userDtoLocalObject.setPasswordKey(users.getPasswordKey());
        userDtoLocalObject.setId(users.getId());
        userDtoLocalObject.setRole(users.getRole());
        return userDtoLocalObject;
    }

}
