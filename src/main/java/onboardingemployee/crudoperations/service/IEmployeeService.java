package onboardingemployee.crudoperations.service;

import java.util.List;
import java.util.Optional;

import onboardingemployee.crudoperations.model.Employee;

public interface IEmployeeService {

	Employee save(Employee emp);

	Optional<Employee> findById(Long id);

	List<Employee> getAllEmployees();

	void delete(Employee emp);

}
