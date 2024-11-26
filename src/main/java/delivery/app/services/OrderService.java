package delivery.app.services;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import delivery.app.DTO.OrderRequestDTO;
import delivery.app.entities.Beverage;
import delivery.app.entities.Lunch;
import delivery.app.entities.Order;
import delivery.app.entities.Waiter;
import delivery.app.repositories.BeverageRepository;
import delivery.app.repositories.LunchRepository;
import delivery.app.repositories.OrderRepository;
import delivery.app.repositories.WaiterRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private BeverageRepository beverageRepository;
	@Autowired
	private WaiterRepository waiterRepository;
	@Autowired
	private LunchRepository lunchRepository;

	public boolean saveOrder(OrderRequestDTO orderDTO) {
		try {
			Order newOrder = new Order();

			Beverage orderedBeverage = beverageRepository.findById(orderDTO.getBeverage_id())
					.orElseThrow(() -> new IllegalArgumentException("Invalid beverage ID"));
			newOrder.setBeverage_price(orderedBeverage.getBeverage_price());
			Waiter servicingWaiter = waiterRepository.findById(orderDTO.getWaiter_id())
					.orElseThrow(() -> new IllegalArgumentException("Invalid waiter ID"));
			Lunch lunch = lunchRepository.findById(orderDTO.getLunch_id())
					.orElseThrow(() -> new IllegalArgumentException("Invalid waiter ID"));
			
			newOrder.setLunch(lunch);
			newOrder.setBeverage(orderedBeverage);
			newOrder.setWaiter(servicingWaiter);
			newOrder.setCreated_at(LocalDateTime.now());

			orderRepository.save(newOrder);
			return true;
		} catch (Exception e) {

			System.err.println("Error saving order: " + e.getMessage());
			return false;
		}
	}

	public Order findOrderById(Long order_id) throws NotFoundException {
		return this.orderRepository.findById(order_id).orElseThrow(() -> new NotFoundException());
	}

	public List<Order> findOrdersByDateAndTimeRange(LocalDateTime startDate, LocalDateTime endDate) {
		return null;
	}

	public List<Order> findOrderByWaiter(Waiter waiter) {
		return null;
	}
}
