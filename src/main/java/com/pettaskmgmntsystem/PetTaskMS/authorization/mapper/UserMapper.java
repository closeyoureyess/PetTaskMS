package com.pettaskmgmntsystem.PetTaskMS.authorization.mapper;

import com.pettaskmgmntsystem.PetTaskMS.authorization.auxiliaryclasses.CustomUsers;
import com.pettaskmgmntsystem.PetTaskMS.authorization.dto.CustomUsersDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

    public CustomUsers convertDtoToUser(CustomUsersDto userDto) {
        CustomUsers usersLocalObject = new CustomUsers();
        usersLocalObject.setEmail(userDto.getEmail());
        usersLocalObject.setPasswordKey(userDto.getPasswordKey());
        usersLocalObject.setId(userDto.getId());
        /*usersLocalObject.setName(userDto.getName());
        usersLocalObject.setSurname(userDto.getSurname());*/
        return usersLocalObject;
    }

    public CustomUsersDto convertUserToDto(CustomUsers users) {
        CustomUsersDto userDtoLocalObject = new CustomUsersDto();
        userDtoLocalObject.setEmail(users.getEmail());
        userDtoLocalObject.setPasswordKey(users.getPasswordKey());
        userDtoLocalObject.setId(users.getId());
        /*userDtoLocalObject.setName(users.getName());
        userDtoLocalObject.setSurname(users.getSurname());*/
        return userDtoLocalObject;
    }
}
