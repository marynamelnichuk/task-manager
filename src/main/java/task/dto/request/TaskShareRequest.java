package task.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

public class TaskShareRequest {

    @JsonProperty("user_id")
    private Integer idUser;

    @JsonProperty("id_task")
    private Long idTask;

    public Integer getIdUser() {
        return idUser;
    }

    public Long getIdTask() {
        return idTask;
    }
}
