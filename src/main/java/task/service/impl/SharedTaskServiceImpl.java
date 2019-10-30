package task.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.dto.SharedTaskDTO;
import task.dto.request.ShareTaskRequest;
import task.exception.EntityNotFoundException;
import task.mapper.SharedTaskMapper;
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
    private final SharedTaskMapper sharedTaskMapper;

    @Autowired
    SharedTaskServiceImpl(SharedTaskRepository sharedTaskRepository, UserService userService, TaskService taskService,
                          SharedTaskMapper sharedTaskMapper) {
        this.sharedTaskRepository = sharedTaskRepository;
        this.userService = userService;
        this.taskService = taskService;
        this.sharedTaskMapper = sharedTaskMapper;
    }

    @Override
    public SharedTaskDTO shareTask(ShareTaskRequest shareTaskRequest) throws EntityNotFoundException {
        User user  = userService.findUserByEmail(shareTaskRequest.getEmail());
        Task task = taskService.findTaskById(shareTaskRequest.getIdTask());
        SharedTask sharedTask = new SharedTask();
        sharedTask.setRecipientId(user.getId());
        sharedTask.setTaskId(task.getId());
        sharedTaskRepository.save(sharedTask);
        //Task task2 = taskService.findTaskById(savedTask.getTaskId());
        return sharedTaskMapper.mapToShareTaskDTO(task);
    }


    @Override
    public List<SharedTaskDTO> findAllSharedTaskByUserId(Integer userId) {
        return sharedTaskRepository.findAllByRecipientId(userId).stream()
                .map(elem ->
                    sharedTaskMapper.mapToShareTaskDTO(taskService.findTaskById(sharedTaskRepository.save(elem).getTaskId())
                    )).collect(Collectors.toList());
    }

}
