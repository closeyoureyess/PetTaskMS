package com.pettaskmgmntsystem.PetTaskMS.tms.controller;

import com.pettaskmgmntsystem.PetTaskMS.constants.ConstantsClass;
import com.pettaskmgmntsystem.PetTaskMS.exeptions.ExecutorNotFoundExeption;
import com.pettaskmgmntsystem.PetTaskMS.tms.dto.TasksDto;
import com.pettaskmgmntsystem.PetTaskMS.tms.services.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@Slf4j
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/task/create")
    public ResponseEntity<TasksDto> createTask(@RequestBody TasksDto tasksDto) throws UsernameNotFoundException, ExecutorNotFoundExeption {
        log.info("Создание задачи, POST " + tasksDto.getHeader());
        TasksDto localTasksDto = taskService.createTasks(tasksDto);
        if (localTasksDto != null) {
            return ResponseEntity.ok(localTasksDto);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/task/gen-info/{author}")
    public ResponseEntity<TasksDto> getTaskAuthor(@PathVariable("author") String author) {
        log.info("Получение задачи по автору, метод GET " + author);
        return ResponseEntity.ok(null);
    }

    @GetMapping("/task/gen-info/{executor}")
    public ResponseEntity<TasksDto> getTaskExecutor(@PathVariable("executor") String executor) {
        log.info("Получение задачи по исполнителю, метод GET " + executor);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/task/update-tasks")
    public ResponseEntity<TasksDto> editTasks(@RequestBody TasksDto tasksDto) throws ExecutorNotFoundExeption {
        TasksDto newTasksDto = taskService.changeTasks(tasksDto);
        if (newTasksDto != null){
        return ResponseEntity.ok(newTasksDto);
        }
        return ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/task/delete/{id}")
    public ResponseEntity<String> deleteCase(@PathVariable("id") Integer idTasks) {
        log.info("Удаление задачи по id, метод DELETE" + idTasks);
        boolean resultDeleteTasks = taskService.deleteTasks(idTasks);
        if (resultDeleteTasks) {
            return ResponseEntity.ok(ConstantsClass.IS_DELETE);
        }
        return ResponseEntity.notFound().build();
    }
}
