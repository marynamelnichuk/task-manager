package task.dto.request;

import javax.validation.constraints.Email;

public class UserLoginRequest {

    @Email
    private String email;

    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }
}
