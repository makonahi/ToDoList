package makonahi.ToDoList.controller;

import io.swagger.v3.oas.annotations.Operation;
import makonahi.ToDoList.entity.TaskEntity;
import makonahi.ToDoList.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Autowired
    TaskService taskService;

    @Operation(summary = "Creates new TASK entry with new ID.")
    @PostMapping
    public TaskEntity createTask(@RequestBody TaskEntity taskEntity){
        return taskService.createTask(taskEntity);
    }

    @Operation(summary = "Returns a JSON TASK entry by ID, f.e. /tasks/1.")
    @GetMapping("/{id}")
    @ResponseBody
    public TaskEntity getTaskById(@PathVariable Integer id) {
        System.out.println("Request received for task ID: " + id);  // Логирование для отладки
        TaskEntity task = taskService.getTaskById(id);
        System.out.println("Task fetched: " + task);  // Логирование результата
        return task;
    }

    @Operation(summary = "Essentially replaces old TASK with new TASK fields, but same ID.")
    @PutMapping("/{id}")
    public TaskEntity updateTask(@PathVariable Integer id, @RequestBody TaskEntity redactedTaskEntity) {
        return taskService.updateTask(redactedTaskEntity, id);
    }

    @Operation(summary = "Deletes TASK by this specific ID.")
    @DeleteMapping("/{id}")
    public void deleteTask(@PathVariable Integer id){
        taskService.deleteTask(id);
    }

}
