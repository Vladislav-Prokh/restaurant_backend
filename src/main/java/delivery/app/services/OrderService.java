package delivery.app.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import delivery.app.entities.Order;
import delivery.app.entities.Waiter;
import delivery.app.repositories.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository orderRepository;
	
	public boolean saveOrder(Order order) {
		 try {
		        return this.orderRepository.save(order) != null;
		    } catch (Exception e) {
		        e.printStackTrace();
		        return false;
		    }
	}
	
	public Order findOrderById(Long order_id) throws NotFoundException {
		return this.orderRepository.findById(order_id).orElseThrow(()->new NotFoundException());
	}
	
	public List<Order> findOrdersByDateAndTimeRange(LocalDateTime startDate,LocalDateTime endDate){
		return null;	
	}
	public List<Order> findOrderByWaiter(Waiter waiter){
		return null;	
	}
}
