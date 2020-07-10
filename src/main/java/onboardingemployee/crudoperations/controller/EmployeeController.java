package onboardingemployee.crudoperations.controller;

import java.util.HashMap;
import java.util.List;
//import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import onboardingemployee.crudoperations.Repository.EmployeeRepository;
import onboardingemployee.crudoperations.exception.ResourceNotFoundException;
import onboardingemployee.crudoperations.model.Employee;
import onboardingemployee.crudoperations.responsemodel.CustomResponse;

@RestController
@RequestMapping("/api/v1")
public class EmployeeController {

	@Autowired
	private EmployeeRepository employeeRepository;

	@GetMapping("/employees/{Id}")
	public ResponseEntity<CustomResponse> getEmployee(@PathVariable(value = "Id") Long Id) {
		CustomResponse cr = new CustomResponse();
  	  	Map<String, Object> metaData = new HashMap<>();
  	  	try {
  	  	  	Employee emp = employeeRepository.findById(Id).get();
  	  	cr.setData(emp);
      	metaData.put("status", "success");
      	cr.setMetaData(metaData);
    	return new ResponseEntity<>(cr,HttpStatus.CREATED); 
  	  	}
  	  	catch(NoSuchElementException e)
  	  	{
  	  	  metaData.put("status", "failure");
    	  metaData.put("message", "Employee not found with ID :"+Id);
    	  cr.setMetaData(metaData);
    	  return new ResponseEntity<>(cr,HttpStatus.NOT_FOUND);
  	  	}
	}
	
	@GetMapping("/employees")
	public ResponseEntity<List<Employee>> getEmployee() {
		return new ResponseEntity<>(employeeRepository.findAll(),HttpStatus.OK);
	}

	@PostMapping("/employees")
	public ResponseEntity<CustomResponse> createEmployee(@Valid @RequestBody Employee employee) {
	      String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
    	  CustomResponse cr = new CustomResponse();
    	  Map<String, Object> metaData = new HashMap<>();
	      if(employee.getEmailId().matches(regex)) {
	      	cr.setData(employeeRepository.save(employee));
	      	metaData.put("status", "success");
	      	cr.setMetaData(metaData);
	    	return new ResponseEntity<>(cr,HttpStatus.CREATED); 
	      }
	      else {
	    	  metaData.put("status", "failure");
	    	  metaData.put("message", "Invalid email");
	    	  cr.setMetaData(metaData);
	    	  return new ResponseEntity<>(cr,HttpStatus.BAD_REQUEST);
	      }
	    	  
	}

	 @PutMapping("/employees/{id}")
    public Employee updateEmployee(@PathVariable(value = "id") Long Id,
     @Valid @RequestBody Employee employeeDetails) throws ResourceNotFoundException {
       Employee employee = employeeRepository.findById(Id).orElseThrow(() -> new ResourceNotFoundException("Employee not found for this id :: " + Id));

        employee.setEmailId(employeeDetails.getEmailId());
        employee.setLastName(employeeDetails.getLastName());
        employee.setFirstName(employeeDetails.getFirstName());
        employee.setPhoneNumber(employeeDetails.getPhoneNumber());
        employee.setCurrentOrganisation(employeeDetails.getCurrentOrganisation());
        employee.setAddress(employeeDetails.getAddress());
        Employee updatedEmployee = employeeRepository.save(employee);
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
