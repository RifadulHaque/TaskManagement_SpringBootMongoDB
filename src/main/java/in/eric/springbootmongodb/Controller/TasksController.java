package in.eric.springbootmongodb.Controller;

import in.eric.springbootmongodb.Model.Task;
import in.eric.springbootmongodb.Service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import org.springframework.data.domain.Pageable;
import java.util.List;

@RestController
public class TasksController {

    @Autowired
    private TaskService taskService;

    //Added Pageable which allows the data to be sorted in page with size of items in it.
    //converted it to a list for better display
    //GET {{url}}/task?page=0&size=2&sort=id,desc
    @GetMapping("/task")
    public List<Task> getAllTasks(Pageable page) {
        return taskService.getAllTasks(page).toList();
    }

    //using string query
    // GET {{url}}/task/sdjasidjs12
    @GetMapping("/task/{id}")
    public Task getCourseById(@PathVariable("id") String id) {
        return taskService.getTaskById(id);
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

    //DELETE {{url}}/task?id=6
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/task")
    public void deleteTaskById(@RequestParam("id") String id) {
        taskService.deleteTaskById(id);
    }

    //used for updating the details
    //PUT {{url}}/task?id=4
    @PutMapping("/task")
    public Task updateTaskDetails(@RequestBody Task task, @RequestParam("id") String id) {
        return taskService.updateTaskDetails(id, task);
    }

    // -------- Normal ones ends here ------

    //this method was first added in CourseRepository and then implemented in CourseServive and then called here
    //GET {{url}}/tasks/code?code=NFS
    @GetMapping("/tasks/code")
    public List<Task> getAllTasksByTaskCode(@RequestParam String code, Pageable page) {
        return taskService.readByTaskCode(code,page);
    }

    //GET {{url}}/tasks/status?status=Completed
    @GetMapping("/tasks/status")
    public List<Task> getAllTasksByTaskStatus(@RequestParam String status, Pageable page) {
        return taskService.readByTaskStatus(status,page);
    }

    // GET {{url}}/tasks/codeKeyword?keyword=NFS
    @GetMapping("/tasks/codeKeyword")
    public List<Task> getAllTasksByCodeKeyword(@RequestParam String keyword, Pageable page) {
        return taskService.readByTaskCodeContaining(keyword,page);
    }

    // GET {{url}}/tasks/titleKeyword?keyword=NFS
    @GetMapping("/tasks/titleKeyword")
    public List<Task> getAllTaskByTitleKeyword(@RequestParam String keyword, Pageable page) {
        return taskService.readByTaskTitleContaining(keyword,page);
    }
    
    /*

        code:"NFS-2"
        title:"Fix the parameters of getAllTasks"
         description:"The parameter is wrong which is giving the error"
        status:"Completed"
        createdAt:2023-03-02T10:58:15.903+00:00
        _class:"in.eric.springbootmongodb.Model.Task"
    */


}
