package delivery.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import delivery.app.entities.Employee;

public interface EmployeeRepository extends JpaRepository<Employee,Long>{
	public Employee findByEmployeeName(String employeeName);
}
