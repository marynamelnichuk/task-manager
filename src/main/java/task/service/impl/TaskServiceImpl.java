package task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.dto.TaskDTO;
import task.dto.request.TaskCreateRequest;
import task.dto.request.TaskShareRequest;
import task.dto.request.TaskUpdateRequest;
import task.dto.response.TaskCreateResponse;
import task.exception.EntityNotFoundException;
import task.model.Status;
import task.model.Task;
import task.repository.TaskRepository;
import task.service.TaskService;
import task.service.UserService;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;

    @Autowired
    TaskServiceImpl(TaskRepository taskRepository, UserService userService) {
        this.taskRepository = taskRepository;
        this.userService = userService;
    }

    @Override
    public List<TaskDTO> findAllTasksByUserId(Integer userId) {
        throw new UnsupportedOperationException();
    }

    private Task mapToTaskFromTaskCreateRequest(TaskCreateRequest taskCreateRequest) {
        Task task = new Task();
        task.setStatus(Status.ACTIVE);
        task.setDescription(taskCreateRequest.getDescription());
        task.setUser(userService.findUserById(taskCreateRequest.getUserId()));
        return  task;
    }

    private TaskCreateResponse mapToTaskCreateResponse(Task task) {
        TaskCreateResponse taskCreateResponse = new TaskCreateResponse();
        taskCreateResponse.setTaskId(task.getId());
        taskCreateResponse.setDescription(task.getDescription());
        taskCreateResponse.setStatus(task.getStatus());
        return taskCreateResponse;
    }

    @Override
    public TaskCreateResponse createTask(TaskCreateRequest taskCreateRequest) throws EntityNotFoundException {
        Task savedTask = taskRepository.save(mapToTaskFromTaskCreateRequest(taskCreateRequest));
        return mapToTaskCreateResponse(savedTask);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }

    private Task findById(Long id) {
        return taskRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Task with id:{0} not found", id));
    }

    private TaskDTO mapToTaskDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTaskId(task.getId());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setStatus(task.getStatus());
        taskDTO.setRecipientId(1);
        return taskDTO;
    }


    @Override
    public TaskDTO updateTask(TaskUpdateRequest taskUpdateRequest) {
        Task task  = findById(taskUpdateRequest.getTaskId());
        task.setDescription(taskUpdateRequest.getDescription());
        task.setStatus(taskUpdateRequest.getStatus());
        Task savedTask = taskRepository.save(task);
        return mapToTaskDTO(savedTask);
    }

    @Override
    public Task shareTask(TaskShareRequest task) {
        throw  new UnsupportedOperationException();
    }

}