package com.pettaskmgmntsystem.PetTaskMS.tms.dto;

import com.pettaskmgmntsystem.PetTaskMS.authorization.dto.UserDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotesDto {

    private int id;

    private UserDto usersDto;

    private String comments;

    public NotesDto(UserDto usersDto, String comments) {
        this.usersDto = usersDto;
        this.comments = comments;
    }

    public NotesDto(UserDto usersDto) {
        this.usersDto = usersDto;
    }

    public NotesDto(String comments) {
        this.comments = comments;
    }

    public NotesDto() {
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
