package onboardingemployee.crudoperations.controller;
import java.util.HashMap;
//import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import onboardingemployee.crudoperations.Repository.EmployeeRepository;
import onboardingemployee.crudoperations.exception.ResourceNotFoundException;
import onboardingemployee.crudoperations.model.Employee;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;
	
	

	@GetMapping("/employees/{id}")
    public Employee getEmployeeById(@PathVariable("id") Long id, @RequestParam(value = "emailid", required = false) String emailId)
        throws ResourceNotFoundException {
		
		if(emailId!=null)
		{   
	
        Optional<Employee> employee1 = employeeRepository.findByEmailId(emailId);
	     System.out.println(employee1.get()+"with email id");
        return employee1.get();
        
		}
		else {
		Optional<Employee> employee = employeeRepository.findById(id);
		System.out.println(employee.get()+"with id");
        return employee.get();
		}
		
    }
	  @PostMapping("/employees")
	     public Employee createEmployee(@Valid @RequestBody Employee employee) {
	         return employeeRepository.save(employee);
	     }

	 @PutMapping("/employees/{id}")
     public Employee updateEmployee(@PathVariable(value = "id") Long Id,
      @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
        Employee employee = employeeRepository.findById(Id).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + Id));
 
         employee.setemailId(employeeDetails.getemailId());
         employee.setlastName(employeeDetails.getlastName());
         employee.setfirstName(employeeDetails.getfirstName());
         employee.setphonenumber(employeeDetails.getphonenumber());
         employee.setcurrentorganisation(employeeDetails.getcurrentorganisation());
         employee.setaddress(employeeDetails.getaddress());
         final Employee updatedEmployee = employeeRepository.save(employee);
         return updatedEmployee;
     }
 
     @DeleteMapping("/employees/{id}")
     public Map<String, Boolean> deleteEmployee(@PathVariable(value = "id") Long employeeId)
       throws ResourceNotFoundException {
         Employee employee = employeeRepository.findById(employeeId)
           .orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + employeeId));
 
         employeeRepository.delete(employee);
         Map<String, Boolean> response = new HashMap<>();
         response.put("deleted", Boolean.TRUE);
         return response;
     }
 
    }


