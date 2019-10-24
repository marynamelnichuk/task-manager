package task.controller;

import com.mongodb.DBPortPool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task.dto.TaskDTO;
import task.dto.request.TaskCreateRequest;
import task.dto.request.TaskUpdateRequest;
import task.dto.response.TaskCreateResponse;
import task.model.Task;
import task.service.TaskService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/tasks")
public class TaskController {

    private final TaskService taskService;

    @Autowired
    TaskController(TaskService taskService) {
        this.taskService = taskService;
    }


    @PostMapping
    public ResponseEntity<TaskCreateResponse> createTask(@Valid @RequestBody TaskCreateRequest taskCreateRequest) {
        return ResponseEntity.ok(taskService.createTask(taskCreateRequest));
    }

    @GetMapping("/users/{userId}")
    public ResponseEntity<List<TaskDTO>> findAllTask(@PathVariable(name = "userId")Integer userId) {
        return ResponseEntity.ok(taskService.findAllTasksByUserId(userId));
    }

    @PutMapping
    public ResponseEntity<TaskDTO> updateTask(@Valid @RequestBody TaskUpdateRequest taskUpdateRequest) {
        return ResponseEntity.ok(taskService.updateTask(taskUpdateRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteTask(@PathVariable(name = "id") Long id) {
        taskService.deleteTask(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
