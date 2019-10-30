package task.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.constraints.Size;

public class TaskCreateRequest {

    @JsonProperty("user_id")
    private Integer userId;

    @Size(max = 200)
    private String description;

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
