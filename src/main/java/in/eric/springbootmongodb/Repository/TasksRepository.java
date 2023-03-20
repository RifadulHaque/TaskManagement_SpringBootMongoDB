package in.eric.springbootmongodb.Repository;

import in.eric.springbootmongodb.Model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

//for each entity class there is a repository class that helps to call the inbuilt functions/methods
@Repository
public interface TasksRepository extends MongoRepository<Task,String> {

    Page<Task> findByEmployeeIdAndStatus(String employeeId, String status, Pageable page);

    Page<Task> findByEmployeeIdAndCode(String employeeId, String code, Pageable page);

    //Page<Task> findByEmployeeIdAndCodeContaining(String employeeId, String keyword, Pageable page);
    //This below can be used like IsLike instead of Containing keyword
    Page<Task> findByEmployeeIdAndCodeIsLike(String employeeId, String keyword, Pageable page);

    Page<Task> findByEmployeeIdAndTitleContaining(String employeeId, String titleKeyword, Pageable page);

    Page<Task> findByEmployeeId(String employeeId, Pageable page);
    Optional<Task> findByEmployeeIdAndId(String employeeId, String id);

    //Native Mongo Query has been used here, the name of the method does not matter here
    //copy the query and the ?1 represents the second parameter, the ?0 will represent first parameter
    //this method can be used instead of  Page<Task> findByEmployeeIdAndStatus(String employeeId, String status, Pageable page);
    //This method below is for mongoDB and the above is used mainly for MySQL
    @Query("{\"status\":\"?1\"}")
    Page<Task> getByEmployeeIdAndStatus(String employeeId, String status, Pageable page);

}
