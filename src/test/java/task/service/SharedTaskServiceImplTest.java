package task.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import task.dto.SharedTaskDTO;
import task.dto.request.ShareTaskRequest;
import task.mapper.SharedTaskMapper;
import task.model.SharedTask;
import task.model.Status;
import task.model.Task;
import task.model.User;
import task.repository.SharedTaskRepository;
import task.service.impl.SharedTaskServiceImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class SharedTaskServiceImplTest {

    @Mock
    private UserService userService;

    @Mock
    private SharedTaskMapper sharedTaskMapper;

    @Mock
    private TaskService taskService;

    @Mock
    private SharedTaskRepository sharedTaskRepository;

    @InjectMocks
    private SharedTaskServiceImpl sharedTaskService;

    @Test
    public void shareTask() {
        ShareTaskRequest request = new ShareTaskRequest();
        request.setIdTask(0L);
        request.setEmail("test@gmail.com");

        User user = new User();
        user.setEmail("test@gmail.com");
        user.setPassword("password");
        user.setNickname("nickname");
        user.setId(1);

        Task task = new Task();
        task.setId(0L);
        task.setStatus(Status.ACTIVE);
        task.setDescription("some description");
        task.setUser(user);

        SharedTask sharedTask = new SharedTask();
        sharedTask.setTaskId(request.getIdTask());
        sharedTask.setRecipientId(1);

        SharedTaskDTO sharedTaskDTO = new SharedTaskDTO();
        sharedTaskDTO.setTaskId(request.getIdTask());
        sharedTaskDTO.setDescription(task.getDescription());
        sharedTaskDTO.setRecipient(task.getUser().getEmail());

        when(userService.findUserByEmail(any(String.class))).thenReturn(new User());
        when(taskService.findTaskById(any(Long.class))).thenReturn(task);
        when(sharedTaskRepository.save(any(SharedTask.class))).thenReturn(new SharedTask());
        when(sharedTaskMapper.mapToShareTaskDTO(any(Task.class))).thenReturn(sharedTaskDTO);

        SharedTaskDTO savedSharedTask = sharedTaskService.shareTask(request);
        Assert.assertEquals(request.getIdTask(), savedSharedTask.getTaskId());

    }



}
