package in.eric.springbootmongodb.Model;

import lombok.Data;

import java.util.Date;

@Data // getters and setters
public class ErrorObject {

    private Integer statusCode;
    private String message;
    private Date timeStamp;
}
