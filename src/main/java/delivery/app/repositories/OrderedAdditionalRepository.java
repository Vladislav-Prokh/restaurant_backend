package delivery.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import delivery.app.entities.Order;
import delivery.app.entities.OrderedAdditional;

@Repository
public interface OrderedAdditionalRepository extends JpaRepository<OrderedAdditional, Long> {

	void deleteByOrder(Order order);
}
