package onboardingemployee.crudoperations.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Repository;

import onboardingemployee.crudoperations.model.Employee;
@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>
{

	Optional<Employee> findByEmailId(String emailid);

	Optional<Employee> findByLastName(String string);

	Optional<Employee> findByFirstName(String string);

	Optional<Employee> findByCurrentOrganisation(String string);

	Optional<Employee> findByPhoneNumber(String string);

	Optional<Employee> findByAddress(String string);

     Employee deleteById(int id);

}
