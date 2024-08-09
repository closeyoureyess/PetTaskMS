package com.pettaskmgmntsystem.PetTaskMS.tms.mapper;

import com.pettaskmgmntsystem.PetTaskMS.authorization.mapper.UserMapper;
import com.pettaskmgmntsystem.PetTaskMS.tms.dto.NotesDto;
import com.pettaskmgmntsystem.PetTaskMS.tms.repository.Notes;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class NotesMapper {

    @Autowired
    private UserMapper userMapper;

    public Notes convertDtoToNotes(NotesDto notesDto){
        Notes localNotes = new Notes();
        localNotes.setId(notesDto.getId());
        localNotes.setComments(notesDto.getComments());
        localNotes.setUsers(userMapper.convertDtoToUser(notesDto.getUsersDto()));
        return localNotes;
    }

    public NotesDto convertNotesToDto(Notes notes){
        NotesDto localNotesDto = new NotesDto();
        localNotesDto.setId(notes.getId());
        localNotesDto.setComments(notes.getComments());
        localNotesDto.setUsersDto(userMapper.convertUserToDto(notes.getUsers()));
        return localNotesDto;
    }

}