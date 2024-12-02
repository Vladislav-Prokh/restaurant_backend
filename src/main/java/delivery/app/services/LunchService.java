package delivery.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import delivery.app.DTO.LunchRequestDTO;
import delivery.app.entities.Dessert;
import delivery.app.entities.Lunch;
import delivery.app.entities.Meal;
import delivery.app.repositories.DessertRepository;
import delivery.app.repositories.LunchRepository;
import delivery.app.repositories.MealRepository;

@Service
public class LunchService {
	
	@Autowired
	private LunchRepository lunchRepository;
	@Autowired
	private DessertRepository dessertRepository;
	@Autowired
	private MealRepository mealReposiory;
	
	public List<Lunch> findAllLunches(){
		return this.lunchRepository.findAll();
	}
	
	public void saveLunch(LunchRequestDTO lunchRequestDto) {
		try {
			Long mainCourseId = lunchRequestDto.getMainCourseId();
			Long dessertId =  lunchRequestDto.getDessertId();
			Meal mainCourse = this.mealReposiory.findById(mainCourseId).get();
			Dessert dessert = this.dessertRepository.findById(dessertId).get();
			Lunch lunch = new Lunch(mainCourse,dessert);
			this.lunchRepository.save(lunch);
	
		}
		catch(Exception e) {
			e.fillInStackTrace();
		}

	}

}
