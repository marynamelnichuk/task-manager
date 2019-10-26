package task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.dto.request.UserRegisterRequest;
import task.dto.response.UserRegisterResponse;
import task.exception.EntityNotFoundException;
import task.model.User;
import task.repository.UserRepository;
import task.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserRegisterResponse mapToRegisterResponse(User user) {
        UserRegisterResponse response = new UserRegisterResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setNickname(user.getNickname());
        response.setPassword(user.getPassword());
        return response;
    }

    public User mapToUserFromUserRequest (UserRegisterRequest userRegisterRequest) {
        User user = new User();
        user.setEmail(userRegisterRequest.getEmail());
        user.setNickname(userRegisterRequest.getNickname());
        user.setPassword(userRegisterRequest.getPassword());
        return user;
    }

    @Override
    public UserRegisterResponse register(UserRegisterRequest userRegisterRequest) {
        return mapToRegisterResponse(userRepository.save(mapToUserFromUserRequest(userRegisterRequest)));
    }

    @Override
    public User findUserById(Integer id) throws EntityNotFoundException {
        return userRepository.findById(id).orElseThrow(() ->
                new EntityNotFoundException("User with id:{0} not found", id));
    }

    @Override
    public User findUserByEmail(String email) throws EntityNotFoundException {
        return userRepository.findByEmail(email).orElseThrow(() ->
                new EntityNotFoundException("User with email:{0} not found", email));
    }


}
