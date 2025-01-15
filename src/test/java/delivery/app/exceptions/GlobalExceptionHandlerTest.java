package delivery.app.exceptions;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import delivery.app.controllers.MenuController;
import delivery.app.services.MenuService;

public class GlobalExceptionHandlerTest {
	@Mock
	private MenuService menuService;

    @Mock
    private MenuController menuController;

    @InjectMocks
    private GlobalExceptionHandler globalExceptionHandler;

    @Autowired
    private MockMvc mockMvc;
    
    private int page = 1;
    private int size = 1;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(menuController)
                .setControllerAdvice(globalExceptionHandler)
                .build();
    }

    @Test
    void testHandleResourceNotFoundException() throws Exception {


        when(menuController.getBeverages(page, size)).thenThrow(new ResourceNotFoundException("Beverage not found"));

        mockMvc.perform(get("/menu/beverages")
                .param("page", String.valueOf(page))
                .param("size", String.valueOf(size))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andExpect(jsonPath("$.status").value(HttpStatus.NOT_FOUND.value()))
                .andExpect(jsonPath("$.message").value("Beverage not found"))
                .andExpect(jsonPath("$.timestamp").exists());
    }


    @Test
    void testHandleInsertionException() throws Exception {


        when(menuController.getBeverages(page, size))
                .thenThrow(new DataIntegrityViolationException("Constraint violation"));

        mockMvc.perform(get("/menu/beverages")
                .param("page", String.valueOf(page))
                .param("size", String.valueOf(size))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict()) 
                .andExpect(jsonPath("$.status").value(HttpStatus.CONFLICT.value()))
                .andExpect(jsonPath("$.message").value("An unexpected error occurred: Constraint violation"))
                .andExpect(jsonPath("$.timestamp").exists());
    }

    @Test
    void testHandleGeneralException() throws Exception {

        when(menuController.getBeverages(page, size)).thenThrow(new Exception("General error"));

        mockMvc.perform(get("/menu/beverages")
                .param("page", String.valueOf(page))
                .param("size", String.valueOf(size))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())  
                .andExpect(jsonPath("$.status").value(HttpStatus.INTERNAL_SERVER_ERROR.value())) 
                .andExpect(jsonPath("$.message").value("An unexpected error occurred: General error")) 
                .andExpect(jsonPath("$.timestamp").exists());  
    }

    @Test
    void testHandleIllegalArgumentException() throws Exception {
        when(menuController.getBeverages(page, size)).thenThrow(new IllegalArgumentException("Invalid argument"));

        mockMvc.perform(get("/menu/beverages")
		        .param("page", String.valueOf(page))
		        .param("size", String.valueOf(size))
		        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.status").value(HttpStatus.BAD_REQUEST.value()))
                .andExpect(jsonPath("$.message").value("An unexpected error occurred: Invalid argument"))
                .andExpect(jsonPath("$.timestamp").exists());
    }

    @Test
    void testHandleDeleteRecordException() throws Exception {
        Long beverageId = 1L;

        doThrow(new DeleteRecordException("Cannot delete beverage"))
                .when(menuController).deleteBeverage(beverageId);

        mockMvc.perform(delete("/menu/beverages/{beverage-id}", beverageId)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isInternalServerError())
                .andExpect(jsonPath("$.status").value(500))
                .andExpect(jsonPath("$.message").value("Cannot delete beverage"))
                .andExpect(jsonPath("$.timestamp").exists());

        verify(menuController, times(1)).deleteBeverage(beverageId);
    }
}
