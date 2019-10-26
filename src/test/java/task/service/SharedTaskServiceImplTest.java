package task.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import task.repository.SharedTaskRepository;

@RunWith(SpringRunner.class)
public class SharedTaskServiceImplTest {

    @MockBean
    private SharedTaskRepository sharedTaskRepository;

    @MockBean
    private UserService userService;

    @MockBean
    private TaskService taskService;

    @Test
    public void shareTask() {

    }

    @Test
    public void findAllSharedTaskByUserId() {

    }



}
