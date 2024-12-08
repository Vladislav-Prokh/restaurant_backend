package delivery.app.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import delivery.app.DTO.OrderRequestDTO;
import delivery.app.DTO.OrderResponseDTO;
import delivery.app.DTO.OrderedAdditionalDTO;
import delivery.app.entities.Beverage;
import delivery.app.entities.BeverageAdditional;
import delivery.app.entities.Employee;
import delivery.app.entities.Lunch;
import delivery.app.entities.Order;
import delivery.app.entities.OrderedAdditional;
import delivery.app.exceptions.ResourceNotFoundException;
import delivery.app.repositories.BeverageAdditionalRepository;
import delivery.app.repositories.BeverageRepository;
import delivery.app.repositories.EmployeeRepository;
import delivery.app.repositories.LunchRepository;
import delivery.app.repositories.OrderRepository;
import delivery.app.repositories.OrderedAdditionalRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;
	@Autowired
	private BeverageRepository beverageRepository;
	@Autowired
	private EmployeeRepository employeeRepository;
	@Autowired
	private LunchRepository lunchRepository;
	@Autowired
	private BeverageAdditionalRepository beverageAdditionalRepository;
	@Autowired
	private OrderedAdditionalRepository orderedAdditionalRepository;

	public OrderResponseDTO saveOrder(OrderRequestDTO orderDTO) {

	    Beverage orderedBeverage = fetchBeverage(orderDTO.getBeverageId());
	    Employee servicingWaiter = fetchEmployee(orderDTO.getWaiterId());
	    Lunch lunch = fetchLunch(orderDTO.getLunchId());


	    Order newOrder = new Order();
	    newOrder.setEmployee(servicingWaiter);
	    newOrder.setBeverage(orderedBeverage);
	    newOrder.setLunch(lunch);
	    newOrder.setCreatedAt(LocalDateTime.now());

	
	    newOrder.setBeveragePrice(orderedBeverage != null ? orderedBeverage.getBeveragePrice() : 0.0f);
	    newOrder.setMainCoursePrice(lunch != null && lunch.getMainCourse() != null ? lunch.getMainCourse().getMealPrice() : 0.0f);
	    newOrder.setDessertPrice(lunch != null && lunch.getDessert() != null ? lunch.getDessert().getDessertPrice() : 0.0f);

	
	    newOrder = orderRepository.save(newOrder);
	    
	    List<OrderedAdditional> orderedAdditionals = new ArrayList<>();
	    for (OrderedAdditionalDTO additionalDTO : orderDTO.getOrderedAdditions()) {
	        OrderedAdditional orderedAdditional = createOrderedAdditional(additionalDTO, newOrder);
	        orderedAdditionals.add(orderedAdditional);
	    }


	    orderedAdditionals = orderedAdditionalRepository.saveAll(orderedAdditionals);
	    newOrder.setOrderedBeverageAdditionals(orderedAdditionals);

	    newOrder = orderRepository.save(newOrder);

	    List<OrderedAdditionalDTO> orderedAdditionalsDTO = orderedAdditionals.stream()
	        .map(orderedAdditional -> new OrderedAdditionalDTO(
	            orderedAdditional.getBeverage().getBeverageId(),
	            orderedAdditional.getBeverageAdditional().getBeverageAdditionalId(),
	            orderedAdditional.getQuantity()))
	        .collect(Collectors.toList());

	    return new OrderResponseDTO(
	        newOrder.getOrderId(),
	        lunch != null ? lunch.getLunchId() : null,
	        orderedBeverage != null ? orderedBeverage.getBeverageId() : null, 
	        servicingWaiter != null ? servicingWaiter.getEmployeeId() : null,
	        orderedAdditionalsDTO
	    );
	}

	private Beverage fetchBeverage(Long beverageId) {
		return beverageRepository.findById(beverageId)
				.orElseThrow(() -> new IllegalArgumentException("Invalid beverage ID"));
	}

	private Employee fetchEmployee(Long waiterId) {
		return employeeRepository.findById(waiterId).orElseThrow(() -> new IllegalArgumentException("Invalid waiter ID"));
	}

	private Lunch fetchLunch(Long lunchId) {
		return lunchRepository.findById(lunchId).orElseThrow(() -> new IllegalArgumentException("Invalid lunch ID"));
	}

	private OrderedAdditional createOrderedAdditional(OrderedAdditionalDTO additionalDTO, Order order) {
		Beverage beverage = fetchBeverage(additionalDTO.getBeverageId());
		BeverageAdditional beverageAdditional = beverageAdditionalRepository.findById(additionalDTO.getAdditionId())
				.orElseThrow(() -> new IllegalArgumentException("Invalid additional ID"));
		return new OrderedAdditional(beverage, order, beverageAdditional, additionalDTO.getQuantity());
	}


	public Order findOrderById(Long order_id) {
		return this.orderRepository.findById(order_id)
				.orElseThrow(() -> new ResourceNotFoundException("resource not found"));
	}

	public List<Order> findOrdersByDateAndTimeRange(LocalDateTime startDate, LocalDateTime endDate) {
		return null;
	}

	public List<Order> findOrderByEmployee(Employee waiter) {
		return orderRepository.findByEmployee(waiter);
	}

	public Page<Order> getOrders(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return orderRepository.findAll(pageable);
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
