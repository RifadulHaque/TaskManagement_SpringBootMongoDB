package in.eric.springbootmongodb.Controller;

import in.eric.springbootmongodb.Model.Employee;
import in.eric.springbootmongodb.Model.UserModel;
import in.eric.springbootmongodb.Service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    //{{url}}/profile
    @GetMapping("/profile")
    public ResponseEntity<Employee>registerEmployee() {
        return new ResponseEntity<Employee>(employeeService.readEmployee(), HttpStatus.OK);
    }

    //used for updating the details
    //{{url}}/profile
    @PutMapping("/profile")
    public ResponseEntity<Employee> updateUserDetails(@RequestBody UserModel userModel) {
        return new ResponseEntity<Employee>(employeeService.updateEmployee(userModel), HttpStatus.OK);
    }

    //{{url}}/deactivate
    @ResponseStatus(value = HttpStatus.NO_CONTENT)
    @DeleteMapping("/deactivate")
    public ResponseEntity<HttpStatus> deleteUser() {
        employeeService.deleteEmployee();
        return new ResponseEntity<HttpStatus>(HttpStatus.NO_CONTENT);
    }

}
