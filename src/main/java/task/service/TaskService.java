package task.service;


import task.dto.TaskDTO;
import task.dto.request.TaskCreateRequest;
import task.dto.request.TaskShareRequest;
import task.dto.request.TaskUpdateRequest;
import task.dto.response.TaskCreateResponse;
import task.model.Task;

import java.util.List;

public interface TaskService {

    List<TaskDTO> findAllTasksByUserId(Integer userId);

    TaskCreateResponse createTask(TaskCreateRequest taskCreateRequest);

    void deleteTask(Long id);

    TaskDTO updateTask(TaskUpdateRequest task);

    Task shareTask(TaskShareRequest task);


}
