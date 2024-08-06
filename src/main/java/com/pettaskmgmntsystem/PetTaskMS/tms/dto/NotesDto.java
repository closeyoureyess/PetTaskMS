package com.pettaskmgmntsystem.PetTaskMS.tms.dto;

import com.pettaskmgmntsystem.PetTaskMS.authorization.dto.CustomUsersDto;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
public class NotesDto implements Serializable {

    private int id;

    private CustomUsersDto usersDto;

    private String comments;

    public NotesDto(CustomUsersDto usersDto, String comments) {
        this.usersDto = usersDto;
        this.comments = comments;
    }

    public NotesDto(CustomUsersDto usersDto) {
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
