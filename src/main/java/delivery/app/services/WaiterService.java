package delivery.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;
import delivery.app.entities.Waiter;
import delivery.app.exceptions.DatabaseInsertionException;

import delivery.app.repositories.WaiterRepository;

@Service
public class WaiterService {

	@Autowired
	private WaiterRepository waiterRepository;

	public void saveWaiter(Waiter waiter) {
		Waiter savedWaiter = this.waiterRepository.save(waiter);
		if (savedWaiter == null) {
			throw new DatabaseInsertionException("Failed to save waiter: returned null.");
		}

	}

	public Waiter findWaiterById(Long waiterId) throws NotFoundException {
		return this.waiterRepository.findById(waiterId).orElseThrow(() -> new NotFoundException());
	}

}
