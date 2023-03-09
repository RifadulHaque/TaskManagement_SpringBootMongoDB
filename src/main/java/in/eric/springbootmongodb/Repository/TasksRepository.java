package in.eric.springbootmongodb.Repository;

import in.eric.springbootmongodb.Model.Task;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

//for each entity class there is a repository class that helps to call the inbuilt functions/methods
@Repository
public interface TasksRepository extends MongoRepository<Task,String> {

}
