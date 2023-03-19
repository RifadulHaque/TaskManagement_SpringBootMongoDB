package in.eric.springbootmongodb.Repository;

import in.eric.springbootmongodb.Model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EmployeeRepository extends MongoRepository<Employee,String> {

    //key word is existsBy
    Boolean existsByEmail(String email);

    //key word is findBy and Email also exists in Employee class
    Optional<Employee> findByEmail(String email);

}
