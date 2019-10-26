package task.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import task.model.SharedTask;
import task.model.SharedTaskPK;

import java.util.List;

@Repository
public interface SharedTaskRepository extends JpaRepository<SharedTask, SharedTaskPK> {

    List<SharedTask> findAllByRecipientId(Integer recipientId);


}
