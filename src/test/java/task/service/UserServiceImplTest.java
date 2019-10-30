package task.service;


import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import task.dto.request.UserRegistrationRequest;
import task.dto.response.UserRegistrationResponse;
import task.mapper.UserMapper;
import task.model.User;
import task.repository.UserRepository;
import task.service.impl.UserServiceImpl;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void registerUserShouldReturnCreatedUser() {
        UserRegistrationRequest userRegistrationRequest = new UserRegistrationRequest();
        userRegistrationRequest.setEmail("test121@gmail.com");
        userRegistrationRequest.setNickname("test_nickname112");
        userRegistrationRequest.setPassword("testPassword");

        User user = new User();
        user.setEmail(userRegistrationRequest.getEmail());
        user.setNickname(userRegistrationRequest.getNickname());
        user.setPassword(userRegistrationRequest.getPassword());

        UserRegistrationResponse userResponse = new UserRegistrationResponse();
        userResponse.setNickname(user.getNickname());
        userResponse.setEmail(user.getEmail());
        userResponse.setId(user.getId());

        when(userMapper.mapToUserFromUserRequest(any(UserRegistrationRequest.class))).thenReturn(user);
        when(userRepository.existsByEmail(any(String.class))).thenReturn(false);
        when(userRepository.existsByNickname(any(String.class))).thenReturn(false);
        when(userRepository.save(any(User.class))).thenReturn((user));
        when(userMapper.mapToRegisterResponse(any(User.class))).thenReturn(userResponse);

        UserRegistrationResponse savedUser = userService.register(userRegistrationRequest);
        Assert.assertEquals(userRegistrationRequest.getEmail(), savedUser.getEmail());
        Assert.assertEquals(userRegistrationRequest.getNickname(), savedUser.getNickname());

    }

    @Test
    public void findUserByIdShouldReturnUserWithSameId() {
        final int USER_ID =  1;
        User user = new User();
        user.setEmail("test@gmail.com");
        user.setPassword("password");
        user.setNickname("nickname");
        user.setId(USER_ID);
        when(userRepository.findById(USER_ID)).thenReturn(Optional.of(user));
        User foundUser = userService.findUserById(USER_ID);
        Assert.assertEquals(user.getId(), foundUser.getId());
        Assert.assertEquals(user.getEmail(), foundUser.getEmail());
        Assert.assertEquals(user.getNickname(), foundUser.getNickname());
        Assert.assertEquals(user.getPassword(), foundUser.getPassword());
    }

    @Test
    public void findUserByEmailShouldReturnUserWithSameEmail() {
        final String USER_EMAIL =  "test@gmail.com";
        User user = new User();
        user.setEmail(USER_EMAIL);
        user.setPassword("password");
        user.setNickname("nickname");
        user.setId(1);
        when(userRepository.findByEmail(USER_EMAIL)).thenReturn(Optional.of(user));
        User foundUser = userService.findUserByEmail(USER_EMAIL);
        Assert.assertEquals(user.getId(), foundUser.getId());
        Assert.assertEquals(user.getEmail(), foundUser.getEmail());
        Assert.assertEquals(user.getNickname(), foundUser.getNickname());
        Assert.assertEquals(user.getPassword(), foundUser.getPassword());
    }
}
