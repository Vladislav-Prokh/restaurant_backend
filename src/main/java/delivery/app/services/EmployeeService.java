package delivery.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import delivery.app.entities.Employee;
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

}
