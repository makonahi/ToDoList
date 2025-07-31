package makonahi.ToDoList.service;

import makonahi.ToDoList.model.Task;
import makonahi.ToDoList.repository.TaskRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/tasks")
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;

    public Task createTask(Task task){
        return taskRepository.save(task);
    }

    public Task updateTask(Task redactedTask, Integer task_id){
        Task originalTask = getTaskById(task_id);
        BeanUtils.copyProperties(redactedTask, originalTask, "id", "createdDate"); // Копируем все поля, кроме id и createdDate
        return taskRepository.save(originalTask);
    }

    public void deleteTask(Integer task_id){
        taskRepository.deleteById(task_id);
    }

    public Task getTaskById(Integer task_id){
        System.out.println("Fetching task with ID: " + task_id);
        Optional<Task> task = taskRepository.findById(task_id);
        if (task.isPresent()) {
            System.out.println("Task found: " + task.get());
        } else {
            System.out.println("Task not found with ID: " + task_id);
        }
        return task.orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

}
