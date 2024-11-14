package com.pettaskmgmntsystem.PetTaskMS.tms.controller;

import com.pettaskmgmntsystem.PetTaskMS.constants.ConstantsClass;
import com.pettaskmgmntsystem.PetTaskMS.exeptions.MainException;
import com.pettaskmgmntsystem.PetTaskMS.tms.dto.TasksDto;
import com.pettaskmgmntsystem.PetTaskMS.tms.services.TaskService;
import jakarta.validation.constraints.Min;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping
@Slf4j
public class TaskController {

    @Autowired
    private TaskService taskService;

    @PostMapping("/task/create")
    public ResponseEntity<TasksDto> createTask(@RequestBody TasksDto tasksDto) throws MainException {
        log.info("Создание задачи, POST " + tasksDto.getHeader());
        TasksDto localTasksDto = taskService.createTasks(tasksDto);
        if (localTasksDto != null) {
            return ResponseEntity.ok(localTasksDto);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/task/gen-info/author/{author}")
    public ResponseEntity<List<TasksDto>> getTaskAuthor(
            @PathVariable("author") String author,
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
            @RequestParam(value = "limit", defaultValue = "10") @Min(1) Integer limit
    ) {
        log.info("Получение задачи по автору, метод GET " + author);
        Optional<List<TasksDto>> optionalAuthorsTasksDtoList = taskService.getTasksOfAuthorOrExecutor(author, offset, limit,
                ConstantsClass.REGIME_RECORD);
        if (optionalAuthorsTasksDtoList.isPresent()) {
            return ResponseEntity.ok(optionalAuthorsTasksDtoList.get());
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/task/gen-info/executor/{executorEmail}")
    public ResponseEntity<List<TasksDto>> getTaskExecutor(
            @PathVariable("executorEmail") String executorEmail,
            @RequestParam(value = "offset", defaultValue = "0") @Min(0) Integer offset,
            @RequestParam(value = "limit", defaultValue = "10") @Min(1) Integer limit
    ) {
        log.info("Получение задачи по исполнителю, метод GET " + executorEmail);
        Optional<List<TasksDto>> optionalExecutorTasksDtoList = taskService.getTasksOfAuthorOrExecutor(executorEmail, offset, limit,
                ConstantsClass.REGIME_OVERWRITING);
        if (optionalExecutorTasksDtoList.isPresent()) {
            return ResponseEntity.ok(optionalExecutorTasksDtoList.get());
        }
        return ResponseEntity.badRequest().build();
    }

    @PutMapping("/task/update-tasks")
    public ResponseEntity<TasksDto> editTasks(@RequestBody TasksDto tasksDto) throws MainException {
        TasksDto newTasksDto = taskService.changeTasks(tasksDto);
        if (newTasksDto != null) {
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
