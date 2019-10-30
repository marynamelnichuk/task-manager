package task.mapper;

import org.springframework.stereotype.Component;
import task.dto.SharedTaskDTO;
import task.model.Task;

@Component
public class SharedTaskMapper {


    public SharedTaskDTO mapToShareTaskDTO(Task task) {
        SharedTaskDTO taskDTO = new SharedTaskDTO();
        taskDTO.setRecipient(task.getUser().getNickname());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setStatus(task.getStatus());
        taskDTO.setTaskId(task.getId());
        return taskDTO;
    }

}
