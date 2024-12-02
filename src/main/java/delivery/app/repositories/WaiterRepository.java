package delivery.app.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import delivery.app.entities.Waiter;

@Repository
public interface WaiterRepository extends JpaRepository<Waiter,Long>{

}
