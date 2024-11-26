package delivery.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import delivery.app.entities.Waiter;
import delivery.app.repositories.WaiterRepository;

@Service
public class WaiterService {
	
	@Autowired
	private WaiterRepository waiterRepository;
	
	public boolean saveWaiter(Waiter waiter) {
	    try {
	        return this.waiterRepository.save(waiter) != null;
	    } catch (Exception e) {
	        e.printStackTrace();
	        return false;
	    }
	}
	
	public boolean deleteWaiterById(Long waiterId) {
		try {
			this.waiterRepository.deleteById(waiterId);
			return true;
		}
		catch(Exception e) {
			e.fillInStackTrace();
			return false;
		}
	}
	
	public Waiter findWaiterById(Long waiterId) throws NotFoundException {
		return this.waiterRepository.findById(waiterId).orElseThrow(()->new NotFoundException());
	}

}
