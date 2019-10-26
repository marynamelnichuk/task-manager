package task.service;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import task.dto.request.UserRegisterRequest;
import task.dto.response.UserRegisterResponse;
import task.model.User;
import task.repository.UserRepository;
import task.service.impl.UserServiceImpl;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserServiceImpl userService;

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService2;

    @Test
    public void register() {
        UserRegisterRequest userRegisterRequest = new UserRegisterRequest();
        userRegisterRequest.setEmail("test@gmail.com");
        userRegisterRequest.setNickname("test_nickname");
        userRegisterRequest.setPassword("testPassword");

        User user = new User();
        user.setEmail(userRegisterRequest.getEmail());
        user.setNickname(userRegisterRequest.getNickname());
        user.setPassword(userRegisterRequest.getPassword());

        when(userRepository.save(user)).thenReturn(user);
        UserRegisterResponse savedUser = userService.register(userRegisterRequest);
        assertThat(userRegisterRequest.getEmail()).isSameAs(savedUser);
    }

    @Test
    public void findUserById() {

    }

    @Test
    public void findUserByEmail() {
    }
}
