package task.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import task.dto.UserDTO;
import task.dto.request.UserLoginRequest;
import task.dto.request.UserRegistrationRequest;
import task.dto.response.UserRegistrationResponse;
import task.exception.AuthenticationException;
import task.exception.EmailAlreadyExistException;
import task.exception.EntityNotFoundException;
import task.exception.NicknameAlreadyExistException;
import task.mapper.UserMapper;
import task.model.User;
import task.repository.UserRepository;
import task.service.UserService;


@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Autowired
    UserServiceImpl(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public UserRegistrationResponse register(UserRegistrationRequest userRegistrationRequest) throws EmailAlreadyExistException, NicknameAlreadyExistException{
        if (userRepository.existsByEmail(userRegistrationRequest.getEmail())) {
            throw new EmailAlreadyExistException("This email already exists");
        }
        if (userRepository.existsByNickname(userRegistrationRequest.getNickname())) {
            throw new NicknameAlreadyExistException("This nickname already exists");
        }
        return userMapper.mapToRegisterResponse(
                userRepository.save(
                        userMapper.mapToUserFromUserRequest(userRegistrationRequest)));
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

    @Override
    public UserDTO findByNicknameAndPassword(UserLoginRequest userLoginRequest) {
        User user =  userRepository.findByNicknameAndPassword(userLoginRequest.getNickname(), userLoginRequest.getPassword())
                .orElseThrow(() -> new AuthenticationException("User with nickname:{0} not found", userLoginRequest.getNickname()));
        return userMapper.mapToUserDTO(user);
    }


}
