package task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.dto.TaskDTO;
import task.dto.request.TaskCreateRequest;
import task.dto.request.TaskUpdateRequest;
import task.dto.response.TaskCreateResponse;
import task.exception.EntityNotFoundException;
import task.mapper.TaskMapper;
import task.model.Task;
import task.model.User;
import task.repository.TaskRepository;
import task.service.TaskService;
import task.service.UserService;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskRepository taskRepository;
    private final UserService userService;
    private final TaskMapper taskMapper;

    @Autowired
    TaskServiceImpl(TaskRepository taskRepository, UserService userService,
                    TaskMapper taskMapper) {
        this.taskRepository = taskRepository;
        this.userService = userService;
        this.taskMapper = taskMapper;
    }

    @Override
    public List<TaskDTO> findAllTasksByUserId(Integer userId) {
        return taskRepository.findByUserId(userId).stream()
                .map(taskMapper::mapToTaskDTO)
                .collect(Collectors.toList());
    }

    @Override
    public TaskCreateResponse createTask(TaskCreateRequest taskCreateRequest) throws EntityNotFoundException {
        User user = userService.findUserById(taskCreateRequest.getUserId());
        Task task = taskRepository.save(taskMapper.mapToTaskFromTaskCreateRequest(taskCreateRequest, user));
        return taskMapper.mapToTaskCreateResponse(task);
    }

    @Override
    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }


    @Override
    public TaskDTO updateTask(TaskUpdateRequest taskUpdateRequest) {
        Task task  = findTaskById(taskUpdateRequest.getTaskId());
        task.setDescription(taskUpdateRequest.getDescription());
        task.setStatus(taskUpdateRequest.getStatus());
        Task savedTask = taskRepository.save(task);
        return taskMapper.mapToTaskDTO(savedTask);
    }

    @Override
    public Task findTaskById(Long id) {
        return taskRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("Task with id:{0} not found", id));
    }


}
