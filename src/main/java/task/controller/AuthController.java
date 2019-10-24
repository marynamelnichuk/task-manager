package task.controller;


import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import task.dto.UserDTO;
import task.dto.request.UserLoginRequest;
import task.dto.request.UserRegisterRequest;
import task.dto.response.UserRegisterResponse;
import task.service.UserService;

import javax.validation.Valid;

@RestController
@RequestMapping("api/auth")
public class AuthController {

    private final UserService userService;

    @Autowired
    AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public ResponseEntity login(@Valid @RequestBody UserLoginRequest userLoginRequest) {
        throw new UnsupportedOperationException();
    }

    @PostMapping("/registration")
    public ResponseEntity<UserRegisterResponse> registration(@Valid @RequestBody UserRegisterRequest userRegisterRequest) {
        System.out.println(userRegisterRequest);
        return ResponseEntity.ok(userService.register(userRegisterRequest));
    }


}
