package onboardingemployee.crudoperations.serviceimpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import onboardingemployee.crudoperations.Repository.EmployeeRepository;
import onboardingemployee.crudoperations.model.Employee;
import onboardingemployee.crudoperations.service.IEmployeeService;

@Service
public class EmployeeServiceImpl implements IEmployeeService{
	
	@Autowired
	EmployeeRepository er;
	
	@Override
	public Employee save(Employee emp) {
		return er.save(emp);
	} 
	
	@Override
	public Optional<Employee> findById(Long id) {
		return er.findById(id);
	}
	
	@Override
	public List<Employee> getAllEmployees() {
		return er.findAll();
	}
	
	@Override
	public void delete(Employee emp){
		 er.delete(emp);
	}

}
