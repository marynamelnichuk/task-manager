package task.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import task.dto.TaskDTO;
import task.dto.request.TaskCreateRequest;
import task.dto.request.TaskUpdateRequest;
import task.dto.response.TaskCreateResponse;
import task.mapper.TaskMapper;
import task.model.Status;
import task.model.Task;
import task.model.User;
import task.repository.TaskRepository;
import task.service.impl.TaskServiceImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceImplTest {

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserService userService;

    @Mock
    private TaskMapper taskMapper;

    @InjectMocks
    private TaskServiceImpl taskService;

    @Test
    public void createTaskShouldReturnCreatedTask() {
        User user = new User();
        user.setId(1);
        user.setEmail("test@gmail.com");
        user.setPassword("password");
        user.setNickname("nickname");

        TaskCreateRequest taskToCreate = new TaskCreateRequest();
        taskToCreate.setUserId(1);
        taskToCreate.setDescription("test task description");

        Task task = new Task();
        task.setId(0L);
        task.setDescription(taskToCreate.getDescription());
        task.setStatus(Status.ACTIVE);
        task.setUser(user);

        TaskCreateResponse taskCreateResponse = new TaskCreateResponse();
        taskCreateResponse.setTaskId(task.getId());
        taskCreateResponse.setStatus(task.getStatus());
        taskCreateResponse.setDescription(task.getDescription());

        when(userService.findUserById(taskToCreate.getUserId())).thenReturn(user);
        when(taskMapper.mapToTaskFromTaskCreateRequest(taskToCreate, user)).thenReturn(task);
        when(taskRepository.save(any(Task.class))).thenReturn(task);
        when(taskMapper.mapToTaskCreateResponse(any(Task.class))).thenReturn(taskCreateResponse);

        TaskCreateResponse createdTask = taskService.createTask(taskToCreate);
        Assert.assertEquals(taskToCreate.getDescription(), createdTask.getDescription());

    }

    @Test
    public void findAllTasksByUserIdShouldReturnAllTasksTHatHasUser() {
        User user = new User();
        user.setId(1);
        user.setEmail("test@gmail.com");
        user.setPassword("password");
        user.setNickname("nickname");

        when(taskRepository.findByUserId(user.getId())).thenReturn(new ArrayList<>());

        List<TaskDTO> tasks = taskService.findAllTasksByUserId(user.getId());
        for(TaskDTO taskDTO : tasks) {
            Assert.assertEquals(taskDTO.getCreator(), user.getId());
        }

    }

    @Test
    public void deleteTaskByIdShouldDeleteTaskNothingReturn() {
        taskService.deleteTask(0L);
        verify(taskRepository, times(1)).deleteById(0L);
    }

    @Test
    public void updateTaskShouldReturnUpdatedTask(){
        TaskUpdateRequest taskUpdateRequest = new TaskUpdateRequest();
        taskUpdateRequest.setTaskId(1L);
        taskUpdateRequest.setStatus(Status.DONE);
        taskUpdateRequest.setDescription("updated description");

        Task task = new Task();
        task.setId(1L);
        task.setStatus(taskUpdateRequest.getStatus());
        task.setDescription(taskUpdateRequest.getDescription());

        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTaskId(task.getId());
        taskDTO.setStatus(task.getStatus());
        taskDTO.setDescription(task.getDescription());

        when(taskRepository.findById(taskUpdateRequest.getTaskId())).thenReturn(Optional.of(new Task()));
        when(taskRepository.save(any(Task.class))).thenReturn(task);
        when(taskMapper.mapToTaskDTO(task)).thenReturn(taskDTO);

        TaskDTO updatedTask = taskService.updateTask(taskUpdateRequest);

        Assert.assertEquals(taskUpdateRequest.getDescription(), updatedTask.getDescription());
        Assert.assertEquals(taskUpdateRequest.getStatus(), updatedTask.getStatus());
        Assert.assertEquals(taskUpdateRequest.getTaskId(), updatedTask.getTaskId());

    }

    @Test
    public void findTaskByIdShouldReturnTaskWithSameId() {
        final long TASK_ID =  1L;
        User user = new User();
        user.setEmail("test@gmail.com");
        user.setPassword("password");
        user.setNickname("nickname");
        user.setId(1);

        Task task = new Task();
        task.setId(TASK_ID);
        task.setDescription("test description");
        task.setStatus(Status.ACTIVE);
        task.setUser(user);

        when(taskRepository.findById(TASK_ID)).thenReturn(Optional.of(task));
        Task foundTask = taskService.findTaskById(TASK_ID);
        Assert.assertEquals(task.getId(), foundTask.getId());
        Assert.assertEquals(task.getDescription(), foundTask.getDescription());
        Assert.assertEquals(task.getUser(), foundTask.getUser());
        Assert.assertEquals(task.getStatus(), foundTask.getStatus());

    }
}
