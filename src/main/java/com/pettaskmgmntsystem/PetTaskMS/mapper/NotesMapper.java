package com.pettaskmgmntsystem.PetTaskMS.mapper;

import com.pettaskmgmntsystem.PetTaskMS.tms.dto.NotesDto;
import com.pettaskmgmntsystem.PetTaskMS.tms.repository.Notes;

public interface NotesMapper {

    Notes convertDtoToNotes(NotesDto notesDto);
    NotesDto convertNotesToDto(Notes notes);

}
