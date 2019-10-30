package task.mapper;

import org.springframework.stereotype.Component;
import task.dto.UserDTO;
import task.dto.request.UserRegistrationRequest;
import task.dto.response.UserRegistrationResponse;
import task.model.User;


@Component
public class UserMapper {

    public UserRegistrationResponse mapToRegisterResponse(User user) {
        UserRegistrationResponse response = new UserRegistrationResponse();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setNickname(user.getNickname());
        return response;
    }

    public User mapToUserFromUserRequest (UserRegistrationRequest userRegistrationRequest) {
        User user = new User();
        user.setEmail(userRegistrationRequest.getEmail());
        user.setNickname(userRegistrationRequest.getNickname());
        user.setPassword(userRegistrationRequest.getPassword());
        return user;
    }

    public UserDTO mapToUserDTO(User user) {
        UserDTO userDTO = new UserDTO();
        userDTO.setId(user.getId());
        userDTO.setEmail(user.getEmail());
        userDTO.setNickname(user.getNickname());
        return userDTO;
    }

}
