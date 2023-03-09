package in.eric.springbootmongodb.Model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.util.List;

@Data//getters and setters
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "employee")
public class Employee {

    @Id
    private String id;

    @Indexed(unique = true)//for mongodb we use @Indexed to make the email unique
    private String email;

    //JsonIgnore helps to hide the password from being displayed
    @JsonIgnore//this is used to that the password is not returned to the user, as we want it to remain private
    private String password;

    private String name;

    private Long age;

    private List<Task> tasks;

}

