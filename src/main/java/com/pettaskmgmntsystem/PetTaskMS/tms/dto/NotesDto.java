package com.pettaskmgmntsystem.PetTaskMS.tms.dto;

import com.pettaskmgmntsystem.PetTaskMS.authorization.dto.CustomUsersDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotesDto implements Serializable {

    private int id;

    private CustomUsersDto usersDto;

    private String comments;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NotesDto notesDto = (NotesDto) o;
        return id == notesDto.id && Objects.equals(usersDto, notesDto.usersDto) && Objects.equals(comments, notesDto.comments);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, usersDto, comments);
    }
}
