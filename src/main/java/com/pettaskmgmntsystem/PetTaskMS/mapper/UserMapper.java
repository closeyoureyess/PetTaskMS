package com.pettaskmgmntsystem.PetTaskMS.mapper;

import com.pettaskmgmntsystem.PetTaskMS.authorization.dto.CustomUsersDto;
import com.pettaskmgmntsystem.PetTaskMS.authorization.repository.CustomUsers;

public interface UserMapper {

    CustomUsers convertDtoToUser(CustomUsersDto userDto);
    CustomUsersDto convertUserToDto(CustomUsers users);
}
