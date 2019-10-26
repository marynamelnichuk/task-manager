package task.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "shared_tasks")
@IdClass(SharedTask.class)
public class SharedTask implements Serializable {

    @Id
    @Column(name = "recipient_id", insertable=false, updatable=false)
    private Integer recipientId;

    @Id
    @Column(name = "task_id", insertable=false, updatable=false)
    private Long taskId;


    /*@ManyToOne
    @JoinColumn(name = "recipient_id")
    private User recipient;


    @ManyToOne
    @JoinColumn(name = "task_id")
    private Task task;
*/


   /* public User getRecipient() {
        return recipient;
    }

    public void setRecipient(User recipient) {
        this.recipient = recipient;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }
*/
    public Integer getRecipientId() {
        return recipientId;
    }

    public void setRecipientId(Integer recipientId) {
        this.recipientId = recipientId;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }
}
