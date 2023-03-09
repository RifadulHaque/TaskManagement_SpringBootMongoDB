package in.eric.springbootmongodb.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

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

}
