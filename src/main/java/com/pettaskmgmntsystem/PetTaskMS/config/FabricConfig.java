package com.pettaskmgmntsystem.PetTaskMS.config;

import com.pettaskmgmntsystem.PetTaskMS.auxiliaryclasses.TasksActions;
import com.pettaskmgmntsystem.PetTaskMS.auxiliaryclasses.TasksActionsImpl;
import com.pettaskmgmntsystem.PetTaskMS.auxiliaryclasses.UserActions;
import com.pettaskmgmntsystem.PetTaskMS.fabrics.ActionsFabric;
import com.pettaskmgmntsystem.PetTaskMS.fabrics.MappersFabric;
import com.pettaskmgmntsystem.PetTaskMS.mapper.NotesMapper;
import com.pettaskmgmntsystem.PetTaskMS.mapper.TaskMapper;
import com.pettaskmgmntsystem.PetTaskMS.mapper.UserMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FabricConfig {

    @Bean
    public MappersFabric mappersFabric(NotesMapper notesMapper, TaskMapper taskMapper, UserMapper userMapper) {
        return new MappersFabric() {
            @Override
            public NotesMapper createNotesMapper() {
                return notesMapper;
            }

            @Override
            public TaskMapper createTaskMapper() {
                return taskMapper;
            }

            @Override
            public UserMapper createUserMapper() {
                return userMapper;
            }
        };
    }

    @Bean
    public ActionsFabric actionsFabric(UserActions userActions, TasksActions tasksActions) {
        return new ActionsFabric() {
            @Override
            public TasksActions createTasksActions() {
                return tasksActions;
            }

            @Override
            public UserActions createUserActions() {
                return userActions;
            }
        };
    }
}
