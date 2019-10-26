package task.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import task.model.Status;

public class SharedTaskDTO {

    @JsonProperty("task_id")
    private Long taskId;

    private String description;

    private Status status;

    @JsonProperty("recipient_id")
    private String recipient;

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public Long getTaskId() {
        return taskId;
    }

    public String getDescription() {
        return description;
    }

    public Status getStatus() {
        return status;
    }

    public String getRecipient() {
        return recipient;
    }
}
