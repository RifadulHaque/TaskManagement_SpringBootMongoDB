package Service;

import Entity.Task;
import Exceptions.ResourceNotFoundException;
import Repository.TasksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TaskServiceImplementation implements TaskService{

    @Autowired
    private TasksRepository tasksRepository;


    @Override
    public Page<Task> getAllTasks(Pageable page) {
        return tasksRepository.findAll(page);
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

    }

    @Override
    public void deleteTaskByTitle(String title) {

    }

    @Override
    public void deleteTaskByCode(String code) {

    }

    @Override
    public Task saveTaskDetails(Task task) {
        return null;
    }

    @Override
    public Task updateTaskDetails(Task task) {
        return null;
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
