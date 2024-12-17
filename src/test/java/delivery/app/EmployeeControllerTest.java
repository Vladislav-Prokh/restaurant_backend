package delivery.app;
import static org.mockito.Mockito.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import delivery.app.controllers.EmployeeController;
import delivery.app.entities.Employee;
import delivery.app.entities.Role;
import delivery.app.services.EmployeeService;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;


public class EmployeeControllerTest {

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this); 
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController).build();
    }

    @Test
    public void testFindById() throws Exception {

        Long employeeId = 1L;
        Employee employee = new Employee();
        employee.setEmployeeName("test_waiter");
        employee.setRole(Role.WAITER);

        when(employeeService.findEmployeeById(employeeId)).thenReturn(employee);
        mockMvc.perform(get("/employees/{employee-id}", employeeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.employeeName").value("test_waiter"))
                .andExpect(jsonPath("$.role").value("WAITER"));

        verify(employeeService, times(1)).findEmployeeById(employeeId);
    }
    
    @Test
    public void testSaveEmployee() throws Exception {

        Employee employee = new Employee();
        employee.setEmployeeName("TestEmp");
        employee.setEmployeeId(1L);  
        employee.setRole(Role.WAITER);


        String employeeJson = "{"
                + "\"employeeId\": 1,"
                + "\"employeeName\": \"TestEmp\","
                + "\"role\": \"WAITER\""
                + "}";

        mockMvc.perform(post("/employees")
                    .contentType("application/json")
                    .content(employeeJson))
                .andExpect(status().isOk());

        verify(employeeService, times(1)).saveEmployee(any(Employee.class));
    }

}
