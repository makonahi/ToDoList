package makonahi.ToDoList.controller;

import io.swagger.v3.oas.annotations.Operation;
import makonahi.ToDoList.model.Task;
import makonahi.ToDoList.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    TaskService taskService;

    @Operation(summary = "Creates new TASK entry with new ID.")
    @PostMapping("/tasks")
    public Task createTask(@RequestBody Task task){
        return taskService.createTask(task);
    }

    @Operation(summary = "Returns a JSON TASK entry by ID, f.e. /tasks/1.")
    @GetMapping("/tasks/{id}")
    @ResponseBody
    public ResponseEntity<Task> getTaskById(@PathVariable Integer id) {
        Task task = taskService.getTaskById(id);
        return new ResponseEntity<>(task, HttpStatus.OK);
    }

    @Operation(summary = "Returns all TASK entries from DB.")
    @GetMapping("/tasks")
    @ResponseBody
    public ResponseEntity<List<Task>> getTasks() {
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    @Operation(summary = "Replaces old TASK with new TASK fields, but same ID.")
    @PutMapping("/tasks/{id}")
    public Task updateTask(@PathVariable Integer id, @RequestBody Task redactedTask) {
        return taskService.updateTask(redactedTask, id);
    }

    @Operation(summary = "Deletes TASK by this specific ID.")
    @DeleteMapping("/tasks/{id}")
    public void deleteTask(@PathVariable Integer id){
        taskService.deleteTask(id);
    }

}
