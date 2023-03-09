package in.eric.springbootmongodb.Repository;

import in.eric.springbootmongodb.Model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//for each entity class there is a repository class that helps to call the inbuilt functions/methods
@Repository
public interface TasksRepository extends MongoRepository<Task,String> {

    Page<Task> findByEmployeeIdAndStatus(String employeeId, String status, Pageable page);

    Page<Task> findByEmployeeIdAndCode(String employeeId, String code, Pageable page);
    Page<Task> findByEmployeeIdAndTitle(String employeeId, String title, Pageable page);

    Page<Task> findByEmployeeIdAndCodeContaining(String userId, String keyword, Pageable page);

    Page<Task> findByEmployeeId(String userId, Pageable page);
    Optional<Task> findByEmployeeIdAndId(String employeeId, String id, Pageable page);

}
