package task.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import task.model.Status;

public class TaskUpdateRequest {

    @JsonProperty("task_id")
    private Long taskId;

    private String description;

    private Status status;

    public Long getTaskId() {
        return taskId;
    }

    public String getDescription() {
        return description;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

}
