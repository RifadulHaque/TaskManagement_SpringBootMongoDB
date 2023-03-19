package in.eric.springbootmongodb.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "tasks")
public class Task {

    @Id
    private String id;

    @NotBlank(message = "task code should not be null")
    @Size(min = 3, message = "Code must be at least 3 characters")
    private String code;

    @NotBlank(message = "Title must not be null")
    private String title;

    private String description;

    private String status;

    private Date createdAt;

    //THis is used for MySQl
    //it is uni-directional as in the employee class, one to many is not mentioned
   // @ManyToOne(fetch = FetchType.LAZY, optional = false)//when the lazy loading is false, if this class has any reference document when we will get that document as well
    //@JoinColumn(name = "employee_id", nullable = false)//adding a new column for each task


    //THis is used for mongoDb
    //when the lazy loading is false, if this class has any reference document when we will get that document as well
    @DBRef//    @DBRef//adding relationship with a different collection
    @OnDelete(action = OnDeleteAction.CASCADE)//On deletion of the employee, the task needs to be deleted also
    @JsonIgnore//when we fecth the course, we will not fetch the user
    private Employee employee;

}
