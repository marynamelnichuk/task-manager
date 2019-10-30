package task.service;


import task.dto.UserDTO;
import task.dto.request.UserLoginRequest;
import task.dto.request.UserRegistrationRequest;
import task.dto.response.UserRegistrationResponse;
import task.model.User;

public interface UserService {

    UserRegistrationResponse register(UserRegistrationRequest userRegistrationRequest);

    User findUserById(Integer id);

    User findUserByEmail(String email);

    UserDTO findByNicknameAndPassword(UserLoginRequest userLoginRequest);

}
