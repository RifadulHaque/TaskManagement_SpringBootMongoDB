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


    @Override
    public Page<Task> getAllTasks(Pageable page) {
        List<Task> taskList = tasksRepository.findAll();
        if(taskList.size() > 0){
            return tasksRepository.findAll(page);
        }
        throw new ResourceNotFoundException("No task found");
    }

    @Override
    public Task getTaskById(String id) {
        Optional<Task> task = tasksRepository.findById(id);

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
    public void deleteTaskByCode(String code) {
    }

    @Override
    public Task saveTaskDetails(Task task) {
        task.setCreatedAt(new Date(System.currentTimeMillis()));
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
    public Page<Task> readByTaskStatus(String status, Pageable page) {
        return null;
    }

    @Override
    public Page<Task> readByTaskCode(String code, Pageable page) {
        return null;
    }

    @Override
    public Page<Task> readByTaskCodeContaining(String keyword, Pageable page) {
        return null;
    }
}
