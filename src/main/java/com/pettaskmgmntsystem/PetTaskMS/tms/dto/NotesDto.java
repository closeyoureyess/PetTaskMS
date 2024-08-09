package com.pettaskmgmntsystem.PetTaskMS.tms.dto;

import com.pettaskmgmntsystem.PetTaskMS.authorization.dto.CustomUsersDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class NotesDto implements Serializable {

    private int id;

    private CustomUsersDto usersDto;

    private String comments;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
