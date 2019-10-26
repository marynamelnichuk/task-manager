package task.service;


import task.dto.request.UserRegisterRequest;
import task.dto.response.UserRegisterResponse;
import task.model.User;

public interface UserService {

    UserRegisterResponse register(UserRegisterRequest userRegisterRequest);

    User findUserById(Integer id);

    User findUserByEmail(String email);

}
