package task.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task.dto.SharedTaskDTO;
import task.dto.request.TaskShareRequest;
import task.service.SharedTaskService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/shared_tasks")
public class SharedTaskController {

    private final SharedTaskService sharedTaskService;

    @Autowired
    SharedTaskController(SharedTaskService sharedTaskService) {
        this.sharedTaskService = sharedTaskService;
    }

    @PostMapping
    public ResponseEntity<SharedTaskDTO> shareTask(@Valid  @RequestBody  TaskShareRequest taskShareRequest) {
        return ResponseEntity.ok(sharedTaskService.shareTask(taskShareRequest));
}

    @GetMapping
    public ResponseEntity<List<SharedTaskDTO>> findSharedTaskByUserId(@PathVariable Integer userId) {
        return ResponseEntity.ok(sharedTaskService.findAllSharedTaskByUserId(userId));
    }


}
