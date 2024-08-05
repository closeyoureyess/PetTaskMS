package com.pettaskmgmntsystem.PetTaskMS.tms.controller;

import com.pettaskmgmntsystem.PetTaskMS.tms.dto.TaskDto;
import com.pettaskmgmntsystem.PetTaskMS.tms.services.TaskService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("tasksapi/v2")
@Slf4j
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/auth")
    public ResponseEntity<TaskDto> authorization(@RequestBody TaskDto taskDto){
        return ResponseEntity.ok(taskDto);
    }

    @PostMapping("/task/create")
    public ResponseEntity<TaskDto> createTask(@RequestBody TaskDto taskDto){
        log.info("Создание задачи, POST ");
        return ResponseEntity.ok(taskDto);
    }

    @GetMapping("/task/gen-info/{author}")
    public ResponseEntity<TaskDto> getTaskAuthor(@PathVariable("author") String author) {
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
    public ResponseEntity<TaskDto> getTaskExecutor(@PathVariable("executor") String executor) {
        log.info("Получение задачи по исполнителю, метод GET " + executor);
        return ResponseEntity.ok(null);
    }

    @PutMapping("/task/update-tasks")
    public ResponseEntity<TaskDto> editTasks(@RequestBody TaskDto taskDto){
        return ResponseEntity.ok(null);
    }

    @DeleteMapping("/task/delete/{id}")
    ResponseEntity<List<TaskDto>> deleteCase(@PathVariable("id") Integer id) {
        log.info("Удаление задачи по id, метод DELETE" + id);
        return ResponseEntity.ok(null);
    }
}
