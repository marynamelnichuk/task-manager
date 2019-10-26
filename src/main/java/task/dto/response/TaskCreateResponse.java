package task.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import task.model.Status;

public class TaskCreateResponse {

    @JsonProperty("task_id")
    private Long taskId;

    private String description;

    private Status  status;

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getDescription() {
        return description;
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

    @Override
    public String toString() {
        return "TaskCreateResponse{" +
                "taskId=" + taskId +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
