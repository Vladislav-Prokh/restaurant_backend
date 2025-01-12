package delivery.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import delivery.app.DTO.RoleRequest;
import delivery.app.entities.Employee;
import delivery.app.services.EmployeeService;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@GetMapping("/{employee-id}")
	public Employee findById(@PathVariable("employee-id") Long employee_id) throws NotFoundException {
		return this.employeeService.findEmployeeById(employee_id);
	}
	
	@GetMapping
    public Page<Employee> findAllEmployees(
            @RequestParam(defaultValue = "0") int page, 
            @RequestParam(defaultValue = "10") int size) {
        return this.employeeService.getEmployees(page, size);
    }

	@PostMapping("/{employeeId}/role")
    public ResponseEntity<String> assignRole(@PathVariable("employeeId") Long employeeId,
                                             @RequestBody RoleRequest roleRequest) {
        try {
            employeeService.assignRole(employeeId, roleRequest.getRole());
            return ResponseEntity.ok("Role assigned successfully");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Failed to assign role");
        }
    }
	
	@DeleteMapping("/{employee-id}")
	public void deleteEmployeeById(@PathVariable("employee-id") Long employee_id) {
		 this.employeeService.deleteEmployeById(employee_id);
	}
}
