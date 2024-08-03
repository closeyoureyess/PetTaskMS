package com.pettaskmgmntsystem.PetTaskMS.authorization.mapper;

import com.pettaskmgmntsystem.PetTaskMS.authorization.auxiliaryclasses.User;
import com.pettaskmgmntsystem.PetTaskMS.authorization.dto.UserDto;

public class UserMapper {

    public User convertDtoToUser(UserDto userDto) {
        User userLocalObject = new User();
        userLocalObject.setEmail(userDto.getEmail());
        userLocalObject.setPasswordKey(userDto.getPasswordKey());
        userLocalObject.setId(userDto.getId());
        userLocalObject.setName(userDto.getName());
        userLocalObject.setSurname(userDto.getSurname());
        return userLocalObject;
    }

    public UserDto convertDtoToUser(User user) {
        UserDto userDtoLocalObject = new UserDto();
        userDtoLocalObject.setEmail(user.getEmail());
        userDtoLocalObject.setPasswordKey(user.getPasswordKey());
        userDtoLocalObject.setId(user.getId());
        userDtoLocalObject.setName(user.getName());
        userDtoLocalObject.setSurname(user.getSurname());
        return userDtoLocalObject;
    }
}
