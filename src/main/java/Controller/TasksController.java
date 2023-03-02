package Controller;

import Entity.Task;
import Repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TasksController {

    @Autowired
    private TasksRepository tasksRepository;

    @GetMapping("/tasks")
    public ResponseEntity<?> getAllTasks() {
        return null;
    }

}
