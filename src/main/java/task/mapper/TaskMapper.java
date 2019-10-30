package task.mapper;

import org.springframework.stereotype.Component;
import task.dto.TaskDTO;
import task.dto.request.TaskCreateRequest;
import task.dto.response.TaskCreateResponse;
import task.model.Status;
import task.model.Task;
import task.model.User;

@Component
public class TaskMapper  {

    public Task mapToTaskFromTaskCreateRequest(TaskCreateRequest taskCreateRequest, User user) {
        Task task = new Task();
        task.setStatus(Status.ACTIVE);
        task.setDescription(taskCreateRequest.getDescription());
        task.setUser(user);
        return  task;
    }

    public TaskCreateResponse mapToTaskCreateResponse(Task task) {
        TaskCreateResponse taskCreateResponse = new TaskCreateResponse();
        taskCreateResponse.setDescription(task.getDescription());
        taskCreateResponse.setStatus(task.getStatus());
        taskCreateResponse.setTaskId(task.getId());
        taskCreateResponse.setCreator(task.getUser().getId());
        return taskCreateResponse;
    }

    public TaskDTO mapToTaskDTO(Task task) {
        TaskDTO taskDTO = new TaskDTO();
        taskDTO.setTaskId(task.getId());
        taskDTO.setDescription(task.getDescription());
        taskDTO.setStatus(task.getStatus());
        taskDTO.setCreator(task.getUser().getId());
        return taskDTO;
    }


}
