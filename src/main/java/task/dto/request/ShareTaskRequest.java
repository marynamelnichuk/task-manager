package task.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Email;

public class ShareTaskRequest {

    @JsonProperty("id_task")
    private Long idTask;

    @Email
    private String email;


    public Long getIdTask() {
        return idTask;
    }

    public void setIdTask(Long idTask) {
        this.idTask = idTask;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
