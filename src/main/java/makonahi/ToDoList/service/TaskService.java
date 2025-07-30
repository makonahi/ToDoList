package makonahi.ToDoList.service;

import jakarta.persistence.EntityNotFoundException;
import makonahi.ToDoList.entity.TaskEntity;
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

    public TaskEntity createTask(TaskEntity taskEntity){
        return taskRepository.save(taskEntity);
    }

    public TaskEntity updateTask(TaskEntity redactedTaskEntity, Integer task_id){
        TaskEntity originalTaskEntity = getTaskById(task_id);
        BeanUtils.copyProperties(redactedTaskEntity, originalTaskEntity, "id", "createdDate"); // Копируем все поля, кроме id и createdDate
        return taskRepository.save(originalTaskEntity);
    }

    public void deleteTask(Integer task_id){
        taskRepository.deleteById(task_id);
    }

    public TaskEntity getTaskById(Integer task_id){
        System.out.println("Fetching task with ID: " + task_id);
        Optional<TaskEntity> task = taskRepository.findById(task_id);
        if (task.isPresent()) {
            System.out.println("Task found: " + task.get());
        } else {
            System.out.println("Task not found with ID: " + task_id);
        }
        return task.orElseThrow(() -> new RuntimeException("Task not found"));
    }



    public List<TaskEntity> getAllTasksByUserID(Integer user_id){
        return taskRepository.findAll();
    }


}
