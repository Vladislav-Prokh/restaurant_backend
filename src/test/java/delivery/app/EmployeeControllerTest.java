package delivery.app;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import delivery.app.controllers.EmployeeController;
import delivery.app.entities.Employee;
import delivery.app.entities.Role;
import delivery.app.services.EmployeeService;



@WebMvcTest(EmployeeController.class) 
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService waiterService; 

    @Test
    public void testFindByIdSuccess() throws Exception {
        Long waiterId = 1L;
        Employee waiter = new Employee();
        waiter.setEmployeeId(waiterId);
        waiter.setEmployeeName("test_waiter");
        waiter.setRole(Role.DEFAULT);
        when(waiterService.findWaiterById(waiterId)).thenReturn(waiter);
        mockMvc.perform(get("/waiters/{waiter-id}", waiterId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeId").value(waiterId)) 
                .andExpect(jsonPath("$.employeeName").value("test_waiter"));

        verify(waiterService, times(1)).findWaiterById(waiterId);
    }

  @Test
    public void testSaveWaiter() throws Exception {
    	Employee waiter = new Employee();
        waiter.setEmployeeName("John");
        mockMvc.perform(post("/waiters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"employeeName\":\"John\"}"))
                .andExpect(status().isOk());

        verify(waiterService, times(1)).saveEmployee(any(Employee.class));
    }
}
