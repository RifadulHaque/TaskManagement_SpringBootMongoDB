package in.eric.springbootmongodb.Security;

import in.eric.springbootmongodb.Model.Employee;
import in.eric.springbootmongodb.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //optional return type for findByEmail
        Employee existingEmployee = employeeRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Employee not found for the email: " + email));

        //email, password, authorities(here i have not specified any authorities hence it is set as an empty arraylist)
        //the line below is already provided by spring also the User class is provided by the UserDetails class
        return new org.springframework.security.core.userdetails.User(existingEmployee.getEmail(), existingEmployee.getPassword(),new ArrayList<>());
    }
}
