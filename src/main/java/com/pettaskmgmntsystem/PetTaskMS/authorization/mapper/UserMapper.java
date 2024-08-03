package com.pettaskmgmntsystem.PetTaskMS.authorization.mapper;

import com.pettaskmgmntsystem.PetTaskMS.authorization.auxiliaryclasses.Users;
import com.pettaskmgmntsystem.PetTaskMS.authorization.dto.UserDto;

public class UserMapper {

    public Users convertDtoToUser(UserDto userDto) {
        Users usersLocalObject = new Users();
        usersLocalObject.setEmail(userDto.getEmail());
        usersLocalObject.setPasswordKey(userDto.getPasswordKey());
        usersLocalObject.setId(userDto.getId());
        usersLocalObject.setName(userDto.getName());
        usersLocalObject.setSurname(userDto.getSurname());
        return usersLocalObject;
    }

    public UserDto convertUserToDto(Users users) {
        UserDto userDtoLocalObject = new UserDto();
        userDtoLocalObject.setEmail(users.getEmail());
        userDtoLocalObject.setPasswordKey(users.getPasswordKey());
        userDtoLocalObject.setId(users.getId());
        userDtoLocalObject.setName(users.getName());
        userDtoLocalObject.setSurname(users.getSurname());
        return userDtoLocalObject;
    }
}
