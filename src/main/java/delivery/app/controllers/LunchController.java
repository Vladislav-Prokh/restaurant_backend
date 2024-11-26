package delivery.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import delivery.app.DTO.LunchRequestDTO;
import delivery.app.entities.Lunch;
import delivery.app.services.LunchService;

@RestController
@RequestMapping("/lunches")
public class LunchController {

	@Autowired
	private LunchService lunchService;

	@PostMapping
	public void addLuncToDB(@RequestBody LunchRequestDTO lunchRequestDto) {
		lunchService.saveLunch(lunchRequestDto);
	}
	
	public List<Lunch> getAllLunchesByDateRange() {
		return null;
	}
}
