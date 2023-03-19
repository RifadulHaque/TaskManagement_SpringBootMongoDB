package in.eric.springbootmongodb.Service;

import in.eric.springbootmongodb.Model.Task;
import in.eric.springbootmongodb.Exceptions.ResourceNotFoundException;
import in.eric.springbootmongodb.Repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImplementation implements TaskService{

    @Autowired
    private TasksRepository tasksRepository;

    @Autowired
    private EmployeeService employeeService;


    @Override
    public Page<Task> getAllTasks(Pageable page) {
        List<Task> taskList = tasksRepository.findByEmployeeId(employeeService.getLoggedInEmployee().getId(),page).toList();
        if(taskList.size() > 0){
            return tasksRepository.findByEmployeeId(employeeService.getLoggedInEmployee().getId(),page);
        }
        throw new ResourceNotFoundException("No task found");
    }

    @Override
    public Task getTaskById(String id) {
        Optional<Task> task = tasksRepository.findByEmployeeIdAndId(employeeService.getLoggedInEmployee().getId(), id);

        if(task.isPresent()){
            return task.get();
        }
        throw new ResourceNotFoundException("Task is not found with the id" + id);
    }

    @Override
    public void deleteTaskById(String id) {
        Task task = getTaskById(id);
        tasksRepository.delete(task);
    }


    @Override
    public Task saveTaskDetails(Task task) {
        task.setCreatedAt(new Date(System.currentTimeMillis()));
        task.setEmployee(employeeService.getLoggedInEmployee());
        return tasksRepository.save(task);
    }

    @Override
    public Task updateTaskDetails(String id, Task task) {
        //store the current details inside an object and then keep comparing the object
        Task existingTask = getTaskById(id);

        //checks if the object that is passed contains the changes or not, if not then use the existing one
        existingTask.setCode(task.getCode() != null ? task.getCode() : existingTask.getCode());
        existingTask.setTitle(task.getTitle() != null ? task.getTitle() : existingTask.getTitle());
        existingTask.setStatus(task.getStatus() != null ? task.getStatus() : existingTask.getStatus());
        existingTask.setDescription(task.getDescription() != null? task.getDescription() : existingTask.getDescription());

        //save the changes on the object in which all the details are called at first
        return tasksRepository.save(existingTask);
    }

    @Override
    public List<Task> readByTaskStatus(String status, Pageable page) {

        return tasksRepository.findByEmployeeIdAndStatus(employeeService.getLoggedInEmployee().getId(), status, page).toList();
    }

    @Override
    public List<Task> readByTaskCode(String code, Pageable page) {
        return tasksRepository.findByEmployeeIdAndCode(employeeService.getLoggedInEmployee().getId(),code, page).toList();
    }

    @Override
    public List<Task> readByTaskCodeContaining(String keyword, Pageable page) {
        return tasksRepository.findByEmployeeIdAndCodeContaining(employeeService.getLoggedInEmployee().getId(), keyword, page).toList();
    }

    @Override
    public List<Task> readByTaskTitleContaining(String keyword, Pageable page) {
        return tasksRepository.findByEmployeeIdAndTitleContaining(employeeService.getLoggedInEmployee().getId(), keyword, page).toList();
    }
}
