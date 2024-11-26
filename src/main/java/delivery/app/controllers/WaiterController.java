package delivery.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import delivery.app.entities.Waiter;
import delivery.app.services.WaiterService;

@RestController
@RequestMapping("/waiters")
public class WaiterController {

	@Autowired
	private WaiterService waiterService;

	@GetMapping("/{waiter-id}")
	public Waiter findById(@PathVariable("waiter-id") Long waiter_id) throws NotFoundException {
		return this.waiterService.findWaiterById(waiter_id);
	}

	@PostMapping("")
	public boolean saveWaiter(@RequestBody Waiter waiter) {
		return this.waiterService.saveWaiter(waiter);
	}

	@DeleteMapping("/waiter-id")
	public boolean deleteWaiterById(@PathVariable("waiter-id") Long waiter_id) {
		return this.waiterService.deleteWaiterById(waiter_id);
	}
}
