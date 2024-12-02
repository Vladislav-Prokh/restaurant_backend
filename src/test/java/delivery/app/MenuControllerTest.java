package delivery.app;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;

import delivery.app.controllers.MenuController;
import delivery.app.entities.Beverage;
import delivery.app.entities.CuisineType;
import delivery.app.entities.Dessert;
import delivery.app.entities.Lunch;
import delivery.app.entities.Meal;
import delivery.app.services.MenuService;

import static org.mockito.Mockito.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.List;

@WebMvcTest(MenuController.class) 
public class MenuControllerTest {
	
    @Autowired
    private MockMvc mockMvc;
    
    @MockBean
    private MenuService menuService; 

    @Test
    public void testGetBeverages() throws Exception {
        int page = 0;
        int size = 10;
        Page<Beverage> beveragesPage = new PageImpl<>(List.of(new Beverage("Coke", 0.99f) ));
        

        when(menuService.getBeverages(page, size)).thenReturn(beveragesPage);

        mockMvc.perform(get("/menu/beverages")
                        .param("page", String.valueOf(page))
                        .param("size", String.valueOf(size)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].beverage_name").value("Coke"));

        verify(menuService, times(1)).getBeverages(page, size);
    }
    
    @Test
    public void testGetLunches() throws Exception {
        int page = 0;
        int size = 10;
        
        Meal meal = new Meal("grilled chicken breast",12.0f,CuisineType.Mexican);
        Dessert dessert = new Dessert("Chocolate Cake",6.0f,CuisineType.Mexican);
        
        Lunch lunch  = new Lunch();
        lunch.setDessert(dessert);
        lunch.setMainCourse(meal);
        
        Page<Lunch> lunchesPage = new PageImpl<>(List.of(lunch));

        when(menuService.getLunches(page, size)).thenReturn(lunchesPage);

        mockMvc.perform(get("/menu/lunches")
                            .param("page", String.valueOf(page))
                            .param("size", String.valueOf(size)))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.content[0].mainCourse.meal_name").value("grilled chicken breast"))
                    .andExpect(jsonPath("$.content[0].dessert.dessert_name").value("Chocolate Cake"));
        verify(menuService, times(1)).getLunches(page, size);
    }

}
