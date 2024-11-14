package com.pettaskmgmntsystem.PetTaskMS.fabrics;

import com.pettaskmgmntsystem.PetTaskMS.mapper.NotesMapper;
import com.pettaskmgmntsystem.PetTaskMS.mapper.TaskMapper;
import com.pettaskmgmntsystem.PetTaskMS.mapper.UserMapper;

public interface MappersFabric {

    NotesMapper createNotesMapper();
    TaskMapper createTaskMapper();
    UserMapper createUserMapper();
}
