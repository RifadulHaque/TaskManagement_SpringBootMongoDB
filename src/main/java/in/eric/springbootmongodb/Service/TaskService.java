package in.eric.springbootmongodb.Service;

import in.eric.springbootmongodb.Model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TaskService {

    Page<Task> getAllTasks(Pageable page);

    Task getTaskById(String id);

    void deleteTaskById(String id);

    void deleteTaskByCode(String code);

    Task saveTaskDetails(Task task);

    Task updateTaskDetails(String id, Task task);

    Page<Task> readByTaskStatus(String status, Pageable page);

    Page<Task> readByTaskCode (String code, Pageable page);

    Page<Task> readByTaskCodeContaining(String keyword, Pageable page);


}
