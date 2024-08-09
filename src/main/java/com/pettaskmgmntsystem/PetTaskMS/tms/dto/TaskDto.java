package com.pettaskmgmntsystem.PetTaskMS.tms.dto;

import com.pettaskmgmntsystem.PetTaskMS.authorization.dto.CustomUsersDto;
/*import com.pettaskmgmntsystem.PetTaskMS.tms.repository.Notes;*/
import com.pettaskmgmntsystem.PetTaskMS.tms.repository.Notes;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TaskDto implements Serializable {

    private int id;

    private CustomUsersDto taskAuthor;
    private CustomUsersDto taskExecutor;

    private NotesDto notesDto;

    private String taskPriority;
    private String taskStatus;
    private String header;
    private String description;

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
