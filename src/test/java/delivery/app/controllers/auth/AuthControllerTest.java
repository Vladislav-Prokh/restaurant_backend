package delivery.app.controllers.auth;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import delivery.app.entities.Employee;
import delivery.app.entities.Role;
import delivery.app.repositories.EmployeeRepository;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;

@SpringBootTest
@AutoConfigureMockMvc
@WithMockUser(roles = "DEFAULT") 
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeRepository employeeRepository;

    @Test
    public void testGetUserInfoAuthenticated() throws Exception {
        Employee employee = new Employee();
        employee.setEmployeeId(1L);
        employee.setEmployeeEmail("user@example.com");
        employee.setEmployeeName("John");
        employee.setEmployeeSurName("Doe");
        employee.setRole(Role.WAITER);

        Mockito.when(employeeRepository.findByEmployeeEmail(Mockito.anyString())).thenReturn(employee);

        OAuth2User oAuth2User = Mockito.mock(OAuth2User.class);
        Map<String, Object> attributes = new HashMap<>();
        attributes.put("email", "user@example.com");
        Mockito.when(oAuth2User.getAttributes()).thenReturn(attributes);

        Authentication authentication = new UsernamePasswordAuthenticationToken(oAuth2User, null, new ArrayList<>());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        mockMvc.perform(get("/api/auth/userinfo")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())  
                .andExpect(jsonPath("$.email").value("user@example.com"))
                .andExpect(jsonPath("$.role").value("WAITER"));  
    }


    @Test
    public void testGetUserInfoUnauthorized() throws Exception {
        mockMvc.perform(get("/api/auth/userinfo")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized())
                .andExpect(jsonPath("$.error").value("No authentication information available"));
    }

    @Test
    public void testGetUserInfoNotAuthorized() throws Exception {
        Mockito.when(employeeRepository.findByEmployeeEmail(Mockito.anyString())).thenReturn(null);

        mockMvc.perform(get("/api/auth/userinfo")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(401))  
                .andExpect(jsonPath("$.error").value("No authentication information available"));
    }
    
    @Test
    public void testLogout() throws Exception {
        mockMvc.perform(post("/api/auth/logout")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
