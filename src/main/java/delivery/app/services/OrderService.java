package delivery.app.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;
import delivery.app.DTO.OrderRequestDTO;
import delivery.app.DTO.OrderedAdditionalDTO;
import delivery.app.entities.Beverage;
import delivery.app.entities.BeverageAdditional;
import delivery.app.entities.Lunch;
import delivery.app.entities.Order;
import delivery.app.entities.OrderedAdditional;
import delivery.app.entities.Waiter;
import delivery.app.exceptions.DatabaseInsertionException;
import delivery.app.exceptions.ResourceNotFoundException;
import delivery.app.repositories.BeverageAdditionalRepository;
import delivery.app.repositories.BeverageRepository;
import delivery.app.repositories.LunchRepository;
import delivery.app.repositories.OrderRepository;
import delivery.app.repositories.OrderedAdditionalRepository;
import delivery.app.repositories.WaiterRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

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
	@Autowired
	private BeverageAdditionalRepository beverageAdditionalRepository;
	@Autowired
	private OrderedAdditionalRepository orderedAdditionalRepository;

	public void saveOrder(OrderRequestDTO orderDTO) {
		try {
			Beverage orderedBeverage = fetchBeverage(orderDTO.getBeverage_id());
			Waiter servicingWaiter = fetchWaiter(orderDTO.getWaiter_id());
			Lunch lunch = fetchLunch(orderDTO.getLunch_id());

			Order newOrder = new Order();
			newOrder.setBeverage(orderedBeverage);
			newOrder.setBeverage_price(orderedBeverage.getBeverage_price());
			newOrder.setWaiter(servicingWaiter);
			newOrder.setLunch(lunch);
			newOrder.setCreated_at(LocalDateTime.now());
			newOrder.setDessert_price(lunch.getDessert().getDessert_price());
			newOrder.setMain_course_price(lunch.getMainCourse().getMeal_price());
			newOrder.setBeverage_price(orderedBeverage.getBeverage_price());

			newOrder = saveEntity(orderRepository, newOrder);

			List<OrderedAdditional> orderedAdditionals = new ArrayList<>();
			for (OrderedAdditionalDTO additionalDTO : orderDTO.getOrderedAdditions()) {
				OrderedAdditional orderedAdditional = createOrderedAdditional(additionalDTO, newOrder);
				orderedAdditionals.add(orderedAdditional);
			}

			orderedAdditionals = saveEntity(orderedAdditionalRepository, orderedAdditionals);

			newOrder.setOrderedBeverageAdditionals(orderedAdditionals);

			saveEntity(orderRepository, newOrder);

		} catch (Exception e) {
			String errorMessage = "Error occurred while saving order: " + e.getMessage();
			throw new DatabaseInsertionException(errorMessage);
		}
	}

	private Beverage fetchBeverage(Long beverageId) {
		return beverageRepository.findById(beverageId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid beverage ID"));
	}

	private Waiter fetchWaiter(Long waiterId) {
		return waiterRepository.findById(waiterId).orElseThrow(() -> new IllegalArgumentException("Invalid waiter ID"));
	}

	private Lunch fetchLunch(Long lunchId) {
		return lunchRepository.findById(lunchId).orElseThrow(() -> new IllegalArgumentException("Invalid lunch ID"));
	}

	private OrderedAdditional createOrderedAdditional(OrderedAdditionalDTO additionalDTO, Order order) {
		Beverage beverage = fetchBeverage(additionalDTO.getBeverage_id());
		BeverageAdditional beverageAdditional = beverageAdditionalRepository.findById(additionalDTO.getAddition_id())
				.orElseThrow(() -> new IllegalArgumentException("Invalid additional ID"));
		return new OrderedAdditional(beverage, order, beverageAdditional, additionalDTO.getQuantity());
	}

	private <T> T saveEntity(JpaRepository<T, ?> repository, T entity) {
		T savedEntity = repository.save(entity);
		if (savedEntity == null) {
			throw new DatabaseInsertionException("Failed to save entity: returned null.");
		}
		return savedEntity;
	}

	private <T> List<T> saveEntity(JpaRepository<T, ?> repository, List<T> entities) {
		List<T> savedEntities = repository.saveAll(entities);
		if (savedEntities == null || savedEntities.isEmpty()) {
			throw new DatabaseInsertionException("Failed to save entities: returned null or empty.");
		}
		return savedEntities;
	}

	public Order findOrderById(Long order_id) {
		return this.orderRepository.findById(order_id)
				.orElseThrow(() -> new ResourceNotFoundException("resource not found"));
	}

	public List<Order> findOrdersByDateAndTimeRange(LocalDateTime startDate, LocalDateTime endDate) {
		return null;
	}

	public List<Order> findOrderByWaiter(Waiter waiter) {
		return orderRepository.findByWaiter(waiter);
	}

	@Transactional
	public void deleteOrder(Long orderId) {
	    Order order = orderRepository.findById(orderId)
	        .orElseThrow(() -> new EntityNotFoundException("Order not found"));
	    order.getOrderedBeverageAdditionals().clear();
	    orderRepository.save(order);
	    orderRepository.delete(order);
	}

}
