package delivery.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import delivery.app.entities.Employee;
import delivery.app.services.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService waiterService;

	@GetMapping("/{employee-id}")
	public Employee findById(@PathVariable("employee-id") Long employee_id) throws NotFoundException {
		return this.waiterService.findEmployeeById(employee_id);
	}

	@PostMapping
	public void saveEmployee(@RequestBody Employee employee) {
		 this.waiterService.saveEmployee(employee);
	}

}
