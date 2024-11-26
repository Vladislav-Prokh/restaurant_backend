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
    
    @PostMapping("/beverage-additionals")
    public ResponseEntity<?> addBeverageAdditional(@RequestBody BeverageAdditional additional){
    	return addEntityToDB(additional, menuService::saveBeverageAdditional, additional.toString());
    }
    @PostMapping("/beverages")
    public ResponseEntity<?> addBeverage(@RequestBody Beverage beverage) {
        return addEntityToDB(beverage, menuService::saveBeverage,beverage.toString() );
    }

    @PostMapping("/meals")
    public ResponseEntity<?> addMeal(@RequestBody Meal meal) {
        return addEntityToDB(meal, menuService::saveMeal, meal.toString());
    }

    @PostMapping("/desserts")
    public ResponseEntity<?> addDessert(@RequestBody Dessert dessert) {
        return addEntityToDB(dessert, menuService::saveDessert, dessert.toString());
    }
 
    private <T> ResponseEntity<?> addEntityToDB(T item, Function<T, Boolean> saveFunction, String itemName) {
        try {
            boolean isEntitySaved = saveFunction.apply(item); 
            if (isEntitySaved) {
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

