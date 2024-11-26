package delivery.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import delivery.app.entities.Lunch;
import delivery.app.services.LunchService;

@RestController
@RequestMapping("/lunches")
public class LunchController {

	@Autowired
	private LunchService lunchService;

	@GetMapping("/all")
	public List<Lunch> getAllLunches() {
		return this.lunchService.findAllLunches();
	}

	public List<Lunch> getAllLunchesByDateRange() {
		return null;
	}
}
