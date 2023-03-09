package in.eric.springbootmongodb.Service;

import in.eric.springbootmongodb.Exceptions.ItemAlreadyExistsException;
import in.eric.springbootmongodb.Exceptions.ResourceNotFoundException;
import in.eric.springbootmongodb.Model.Employee;
import in.eric.springbootmongodb.Model.UserModel;
import in.eric.springbootmongodb.Repository.EmployeeRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
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

        String employeeId = getLoggedInEmployee().getId();

        return employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("No employee was found with this employeeId: " + employeeId));
    }

    @Override
    public Employee updateEmployee(UserModel userModel) {
        Employee existingEmployee = readEmployee();
        //checks if the object that is passed contains the changes or not, if not then use the existing one
        existingEmployee.setEmail(userModel.getEmail() != null ? userModel.getEmail() : existingEmployee.getEmail());
        existingEmployee.setName(userModel.getName() != null ? userModel.getName() : existingEmployee.getName());
        //existingUser.setPassword(userModel.getPassword() != null ? userModel.getPassword() : existingUser.getPassword());
        existingEmployee.setPassword(userModel.getPassword() != null ? bcryptEncoder.encode(userModel.getPassword()) : existingEmployee.getPassword());
        existingEmployee.setAge(userModel.getAge() != null ? userModel.getAge() : existingEmployee.getAge());

        return employeeRepository.save(existingEmployee);
    }

    @Override
    public void deleteEmployee() {
        Employee existingEmployee = readEmployee();
        employeeRepository.delete(existingEmployee);
    }

    @Override
    public Employee getLoggedInEmployee() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();

        return employeeRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("User not found for the email :" + email));
    }
}
