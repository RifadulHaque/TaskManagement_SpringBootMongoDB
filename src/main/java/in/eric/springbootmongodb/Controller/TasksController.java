package in.eric.springbootmongodb.Controller;

import in.eric.springbootmongodb.Model.Task;
import in.eric.springbootmongodb.Repository.TasksRepository;
import in.eric.springbootmongodb.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
public class TasksController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/task")
    public List<Task> getAllTasks(Pageable page) {
        return taskService.getAllTasks(page).toList();
    }


//    @RequestBody maps the JSON HTTP details to JAVA objects
//    @Valid will check if the save request is NotNull or NotBlank
//    added validation to the fields, so that it checks if the details are valid or not
//    POST {{url}}/tasks
    @ResponseStatus(value = HttpStatus.CREATED)
    @PostMapping("/task")
    public Task saveTaskDetails(@Valid @RequestBody Task task) {
        return taskService.saveTaskDetails(task);
    }


}
