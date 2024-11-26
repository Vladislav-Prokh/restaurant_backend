package delivery.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import delivery.app.DTO.OrderRequestDTO;
import delivery.app.services.OrderService;
 
@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
    @PostMapping("/add")
    public ResponseEntity<?> addOrder(@RequestBody OrderRequestDTO order) {
                return ResponseEntity.status(HttpStatus.CREATED).body(order);
        
    }
    
  
}
