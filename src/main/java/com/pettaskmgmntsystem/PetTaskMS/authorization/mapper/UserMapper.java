/*
package com.pettaskmgmntsystem.PetTaskMS.authorization.mapper;

import com.pettaskmgmntsystem.PetTaskMS.authorization.auxiliaryclasses.CustomUsers;
import com.pettaskmgmntsystem.PetTaskMS.authorization.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public CustomUsers convertDtoToUser(UserDto userDto) {
        CustomUsers usersLocalObject = new CustomUsers();
        usersLocalObject.setEmail(userDto.getEmail());
        usersLocalObject.setPasswordKey(userDto.getPasswordKey());
        usersLocalObject.setId(userDto.getId());
        usersLocalObject.setName(userDto.getName());
        usersLocalObject.setSurname(userDto.getSurname());
        return usersLocalObject;
    }

    public UserDto convertUserToDto(CustomUsers users) {
        UserDto userDtoLocalObject = new UserDto();
        userDtoLocalObject.setEmail(users.getEmail());
        userDtoLocalObject.setPasswordKey(users.getPasswordKey());
        userDtoLocalObject.setId(users.getId());
        userDtoLocalObject.setName(users.getName());
        userDtoLocalObject.setSurname(users.getSurname());
        return userDtoLocalObject;
    }
}
*/
