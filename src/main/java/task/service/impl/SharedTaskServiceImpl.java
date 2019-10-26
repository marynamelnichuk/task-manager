package task.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.dto.SharedTaskDTO;
import task.dto.request.TaskShareRequest;
import task.exception.EntityNotFoundException;
import task.model.SharedTask;
import task.model.Task;
import task.model.User;
import task.repository.SharedTaskRepository;
import task.service.SharedTaskService;
import task.service.TaskService;
import task.service.UserService;
import java.util.List;
import java.util.stream.Collectors;


@Service
public class SharedTaskServiceImpl implements SharedTaskService {

    private final SharedTaskRepository sharedTaskRepository;
    private final UserService userService;
    private final TaskService taskService;

    @Autowired
    SharedTaskServiceImpl(SharedTaskRepository sharedTaskRepository, UserService userService, TaskService taskService) {
        this.sharedTaskRepository = sharedTaskRepository;
        this.userService = userService;
        this.taskService = taskService;
    }

    @Override
    public SharedTaskDTO shareTask(TaskShareRequest taskShareRequest) throws EntityNotFoundException {
        User user  = userService.findUserByEmail(taskShareRequest.getEmail());
        Task task = taskService.findTaskById(taskShareRequest.getIdTask());
        SharedTask sharedTask = new SharedTask();
        sharedTask.setRecipientId(user.getId());
        sharedTask.setTaskId(task.getId());
        sharedTaskRepository.save(sharedTask);
        return mapToTaskDTO(sharedTaskRepository.save(sharedTask));
    }

    private SharedTaskDTO mapToTaskDTO(SharedTask sharedTask) {
        SharedTaskDTO taskDTO = new SharedTaskDTO();
        Task task = taskService.findTaskById(sharedTask.getTaskId());
        taskDTO.setRecipient(task.getUser().getNickname());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setStatus(task.getStatus());
        taskDTO.setTaskId(task.getId());
        return taskDTO;
    }

    @Override
    public List<SharedTaskDTO> findAllSharedTaskByUserId(Integer userId) {
        return sharedTaskRepository.findAllByRecipientId(userId).stream()
                .map(this::mapToTaskDTO).collect(Collectors.toList());
    }

}
