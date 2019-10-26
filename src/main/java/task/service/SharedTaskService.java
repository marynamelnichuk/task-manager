package task.service;

import task.dto.SharedTaskDTO;
import task.dto.request.TaskShareRequest;

import java.util.List;

public interface SharedTaskService {

    SharedTaskDTO shareTask(TaskShareRequest task);

    List<SharedTaskDTO> findAllSharedTaskByUserId(Integer userId);

}
