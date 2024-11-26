package delivery.app.controllers;

import java.util.function.Function;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import delivery.app.entities.Beverage;
import delivery.app.entities.BeverageAdditional;
import delivery.app.entities.Dessert;
import delivery.app.entities.Meal;
import delivery.app.services.MenuService;

@RestController
@RequestMapping("/menu")
public class MenuController {
    
    @Autowired
    private MenuService menuService;
    
    @PostMapping("/privileged/add/beverageAdditional")
    public ResponseEntity<?> addBeverageAdditional(@RequestBody BeverageAdditional additional){
    	return addItem(additional, menuService::saveBeverageAdditional, additional.toString());
    }
    @PostMapping("/privileged/add/beverage")
    public ResponseEntity<?> addBeverage(@RequestBody Beverage beverage) {
        return addItem(beverage, menuService::saveBeverage,beverage.toString() );
    }

    @PostMapping("/privileged/add/meal")
    public ResponseEntity<?> addMeal(@RequestBody Meal meal) {
        return addItem(meal, menuService::saveMeal, meal.toString());
    }

    @PostMapping("/privileged/add/dessert")
    public ResponseEntity<?> addDessert(@RequestBody Dessert dessert) {
        return addItem(dessert, menuService::saveDessert, dessert.toString());
    }
    
    private <T> ResponseEntity<?> addItem(T item, Function<T, Boolean> saveFunction, String itemName) {
        try {
            boolean saved = saveFunction.apply(item); 
            if (saved) {
                return ResponseEntity.status(HttpStatus.CREATED).body(item);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Failed to save " + itemName);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("An error occurred while saving the " + itemName + ": " + e.getMessage());
        }
    }

}

