package task.service;

import task.dto.SharedTaskDTO;
import task.dto.request.ShareTaskRequest;

import java.util.List;

public interface SharedTaskService {

    SharedTaskDTO shareTask(ShareTaskRequest task);

    List<SharedTaskDTO> findAllSharedTaskByUserId(Integer userId);

}
