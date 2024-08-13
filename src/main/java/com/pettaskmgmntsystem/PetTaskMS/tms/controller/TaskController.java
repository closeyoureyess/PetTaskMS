package com.pettaskmgmntsystem.PetTaskMS.tms.controller;

import com.pettaskmgmntsystem.PetTaskMS.tms.dto.TasksDto;
import com.pettaskmgmntsystem.PetTaskMS.tms.services.TaskService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
@Validated
@Slf4j
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/task/create")
    public ResponseEntity<TasksDto> createTask(@RequestBody TasksDto tasksDto) throws UsernameNotFoundException {
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

    @GetMapping("/task/test")
    public String getTaskAuthor(/*@PathVariable("author") String author*/) {
        /*log.info("Получение задачи по автору, метод GET " + author);
        return ResponseEntity.ok(null);*/
        return "TEST";
    }

    @GetMapping("/task/gen-info/{executor}")
    public ResponseEntity<TasksDto> getTaskExecutor(@PathVariable("executor") String executor) {
        log.info("Получение задачи по исполнителю, метод GET " + executor);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/task/update-tasks")
    public ResponseEntity<TasksDto> editTasks(@RequestBody TasksDto tasksDto) {
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/task/delete/{id}")
    ResponseEntity<List<TasksDto>> deleteCase(@PathVariable("id") Integer id) {
        log.info("Удаление задачи по id, метод DELETE" + id);
        return ResponseEntity.ok(null);
    }
}
