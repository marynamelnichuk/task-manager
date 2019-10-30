package task.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import task.dto.UserDTO;
import task.dto.request.UserLoginRequest;
import task.dto.request.UserRegistrationRequest;
import task.dto.response.UserRegistrationResponse;
import task.exception.AuthenticationException;
import task.exception.EmailAlreadyExistException;
import task.exception.NicknameAlreadyExistException;
import task.service.UserService;

import javax.validation.Valid;


@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity<UserDTO> login(@Valid @RequestBody UserLoginRequest userLoginRequest) throws AuthenticationException {
        return ResponseEntity.ok(userService.findByNicknameAndPassword(userLoginRequest));
    }

    @PostMapping("/registration")
    public ResponseEntity<UserRegistrationResponse> registration(@Valid @RequestBody UserRegistrationRequest userRegistrationRequest)
            throws EmailAlreadyExistException, NicknameAlreadyExistException {
        return ResponseEntity.ok(userService.register(userRegistrationRequest));
    }



}
