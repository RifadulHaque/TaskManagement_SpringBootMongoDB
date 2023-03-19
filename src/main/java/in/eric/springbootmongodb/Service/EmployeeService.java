package in.eric.springbootmongodb.Service;

import in.eric.springbootmongodb.Model.Employee;
import in.eric.springbootmongodb.Model.UserModel;

public interface EmployeeService {

    Employee createEmployee(UserModel user);

    Employee readEmployee();

    Employee updateEmployee(UserModel userModel);

    void deleteEmployee();

    Employee getLoggedInEmployee();
}
