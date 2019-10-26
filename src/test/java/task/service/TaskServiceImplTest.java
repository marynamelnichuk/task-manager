package task.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import task.dto.request.TaskCreateRequest;
import task.dto.response.TaskCreateResponse;
import task.model.Task;
import task.model.User;
import task.repository.TaskRepository;
import task.service.impl.TaskServiceImpl;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TaskServiceImplTest {

    @InjectMocks
    TaskServiceImpl taskService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private UserService userService;


    @Test
    public void createTask() {
        TaskCreateRequest taskToCreate = new TaskCreateRequest();
        taskToCreate.setUserId(2);
        taskToCreate.setDescription("test task description");
        User user = new User();
        when(taskRepository.save(any(Task.class))).thenReturn(new Task());
        when(userService.findUserById(any(Integer.class))).thenReturn(user);
        TaskCreateResponse createdTask = taskService.createTask(taskToCreate);
        assertThat(createdTask.getDescription()).isSameAs(taskToCreate.getDescription());

    }

    @Test
    public void findAllTasksByUserId() {


    }

    @Test
    public void deleteTask() {
    }

    @Test
    public void updateTask() {
    }

    @Test
    public void findTaskById() {
    }
}
