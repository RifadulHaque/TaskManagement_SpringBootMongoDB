package in.eric.springbootmongodb.Service;

import in.eric.springbootmongodb.Model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {

    Page<Task> getAllTasks(Pageable page);

    Task getTaskById(String id);

    void deleteTaskById(String id);

    Task saveTaskDetails(Task task);

    Task updateTaskDetails(String id, Task task);

    List<Task> readByTaskStatus(String status, Pageable page);

    List<Task> readByTaskCode (String code, Pageable page);

    List<Task> readByTaskCodeContaining(String keyword, Pageable page);

    List<Task> readByTaskTitleContaining(String keyword, Pageable page);

}
