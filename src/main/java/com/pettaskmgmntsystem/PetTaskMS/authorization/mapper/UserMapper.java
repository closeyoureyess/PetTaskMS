package com.pettaskmgmntsystem.PetTaskMS.authorization.mapper;

import com.pettaskmgmntsystem.PetTaskMS.authorization.repository.CustomUsers;
import com.pettaskmgmntsystem.PetTaskMS.authorization.dto.CustomUsersDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public CustomUsers convertDtoToUser(CustomUsersDto userDto) {
        CustomUsers usersLocalObject = new CustomUsers();
        usersLocalObject.setEmail(userDto.getEmail());
        usersLocalObject.setPasswordKey(userDto.getPasswordKey());
        usersLocalObject.setId(userDto.getId());
        usersLocalObject.setRole(userDto.getRole());
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
