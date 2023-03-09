package in.eric.springbootmongodb.Service;

import in.eric.springbootmongodb.Exceptions.ItemAlreadyExistsException;
import in.eric.springbootmongodb.Model.Employee;
import in.eric.springbootmongodb.Model.UserModel;
import in.eric.springbootmongodb.Repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImplementation implements EmployeeService{

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    private PasswordEncoder bcryptEncoder;

    @Override
    public Employee createEmployee(UserModel userModel) {

        if(employeeRepository.existsByEmail(userModel.getEmail())) {
            throw new ItemAlreadyExistsException("User already exists with email: " + userModel.getEmail());
        }

        Employee newEmployee = new Employee();
        //source(userModel) -> target(newUser)
        //copy from userModel to newUser
        BeanUtils.copyProperties(userModel,newEmployee);
        //encrypt the password before saving it to the database
        //this will convert the plain password to encrypted password to save it to the database
        newEmployee.setPassword(bcryptEncoder.encode(newEmployee.getPassword()));
        //save the properties from newUser
        return employeeRepository.save(newEmployee);

    }

    @Override
    public Employee readEmployee() {
        return null;
    }

    @Override
    public Employee updateEmployee(UserModel userModel) {
        return null;
    }

    @Override
    public void deleteEmployee() {
        Employee existingEmployee = readEmployee();
        employeeRepository.delete(existingEmployee);
    }

    @Override
    public Employee getLoggedInEmployee() {
        return null;
    }
}
