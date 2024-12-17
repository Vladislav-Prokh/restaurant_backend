package delivery.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import delivery.app.entities.Employee;
import delivery.app.entities.Role;
import delivery.app.exceptions.InvalidRoleException;
import delivery.app.exceptions.ResourceNotFoundException;
import delivery.app.repositories.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	public Employee saveEmployee(Employee employee) {
		return this.employeeRepository.save(employee);
	}

	public Employee findEmployeeById(Long employeeId) throws NotFoundException {
		return this.employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("employee not found"));
	}

	public Employee findByEmployeeEmail(String email) {
		return this.employeeRepository.findByEmployeeEmail(email);
	}
	

	public Page<Employee> getEmployees(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return employeeRepository.findAll(pageable);
	}

	public void deleteEmployeById(Long employee_id) {
		this.employeeRepository.deleteById(employee_id);
	}

	public void assignRole(Long employeeId, String role) throws InvalidRoleException {

	    Employee employee = employeeRepository.findById(employeeId).orElseThrow(() -> new ResourceNotFoundException("employee not found"));
        Role newRole = Role.valueOf(role.toUpperCase());
        
        employee.setRole(newRole);
        this.employeeRepository.save(employee);

	}



}
