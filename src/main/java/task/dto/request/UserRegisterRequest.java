package task.dto.request;

import javax.validation.constraints.Email;

public class UserRegisterRequest {

    @Email
    private String email;

    private String password;

    private String nickname;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getNickname() {
        return nickname;
    }


    @Override
    public String toString() {
        return "UserRegisterRequest{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", nickname='" + nickname + '\'' +
                '}';
    }
}
