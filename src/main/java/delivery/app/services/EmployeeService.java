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

	public Employee saveEmployee(Employee waiter) {
		return this.employeeRepository.save(waiter);
	}

	public Employee findWaiterById(Long waiterId) throws NotFoundException {
		return this.employeeRepository.findById(waiterId).orElseThrow(() -> new ResourceNotFoundException("waiter not found"));
	}

}
