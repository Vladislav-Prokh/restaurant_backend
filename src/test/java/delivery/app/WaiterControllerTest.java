package delivery.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import delivery.app.controllers.WaiterController;
import delivery.app.entities.Waiter;
import delivery.app.services.WaiterService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(WaiterController.class) 
public class WaiterControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private WaiterService waiterService; 

    @Test
    public void testFindByIdSuccess() throws Exception {
        Long waiterId = 1L;
        Waiter waiter = new Waiter();
        waiter.setWaiter_id(waiterId);
        waiter.setWaiter_name("test_waiter");
        when(waiterService.findWaiterById(waiterId)).thenReturn(waiter);
        mockMvc.perform(get("/waiters/{waiter-id}", waiterId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.waiter_id").value(waiterId)) 
                .andExpect(jsonPath("$.waiter_name").value("test_waiter"));

        verify(waiterService, times(1)).findWaiterById(waiterId);
    }

    @Test
    public void testSaveWaiter() throws Exception {
        Waiter waiter = new Waiter();
        waiter.setWaiter_name("John");
        mockMvc.perform(post("/waiters")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"waiter_name\":\"John\"}"))
                .andExpect(status().isOk());

        verify(waiterService, times(1)).saveWaiter(any(Waiter.class));
    }
}
