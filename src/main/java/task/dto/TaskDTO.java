package task.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import task.model.Status;

public class TaskDTO {

    @JsonProperty("task_id")
    private Long taskId;

    private String description;

    private Status status;

    @JsonProperty("recipient_id")
    private Integer recipientId;

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setRecipientId(Integer recipientId) {
        this.recipientId = recipientId;
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

    public Integer getRecipientId() {
        return recipientId;
    }
}
