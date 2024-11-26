package delivery.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import delivery.app.DTO.OrderRequestDTO;
import delivery.app.entities.Order;
import delivery.app.services.OrderService;
 
@RestController
@RequestMapping("/orders")
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
    @PostMapping
    public ResponseEntity<?> addOrder(@RequestBody OrderRequestDTO order) {
    	boolean isOrderSaved = false;
    	try {
    		isOrderSaved = this.orderService.saveOrder(order);
    	    return ResponseEntity.status(HttpStatus.CREATED).body(isOrderSaved);
    	    
    	}
    	catch(Exception e) {
    		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(isOrderSaved);
    	}  
    }
    
    @GetMapping("/{order-id}")
    public Order findOrderById(@PathVariable Long order_id) throws NotFoundException{
    	return  this.orderService.findOrderById(order_id);
    }
}
