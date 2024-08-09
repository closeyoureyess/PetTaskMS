package com.pettaskmgmntsystem.PetTaskMS.tms.dto;

import com.pettaskmgmntsystem.PetTaskMS.authorization.dto.CustomUsersDto;
/*import com.pettaskmgmntsystem.PetTaskMS.tms.repository.Notes;*/
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
public class TasksDto implements Serializable {

    private int id;

    private CustomUsersDto taskAuthor;
    private CustomUsersDto taskExecutor;

    private NotesDto notesDto;

    private String taskPriority;
    private String taskStatus;
    private String header;
    private String description;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TasksDto tasksDto = (TasksDto) o;
        return id == tasksDto.id && Objects.equals(taskAuthor, tasksDto.taskAuthor) && Objects.equals(taskExecutor, tasksDto.taskExecutor) && Objects.equals(notesDto, tasksDto.notesDto) && Objects.equals(taskPriority, tasksDto.taskPriority) && Objects.equals(taskStatus, tasksDto.taskStatus) && Objects.equals(header, tasksDto.header) && Objects.equals(description, tasksDto.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, taskAuthor, taskExecutor, notesDto, taskPriority, taskStatus, header, description);
    }
}
