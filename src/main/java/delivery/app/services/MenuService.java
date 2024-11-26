package delivery.app.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.stereotype.Service;

import delivery.app.entities.Beverage;
import delivery.app.entities.BeverageAdditional;
import delivery.app.entities.Dessert;
import delivery.app.entities.Meal;
import delivery.app.repositories.BeverageAdditionalRepository;
import delivery.app.repositories.BeverageRepository;
import delivery.app.repositories.DessertRepository;
import delivery.app.repositories.MealRepository;

@Service
public class MenuService {

	@Autowired
	private BeverageRepository beverageRepository;
	@Autowired
	private DessertRepository dessertRepository;
	@Autowired
	private MealRepository mealReposiory;
	@Autowired
	private BeverageAdditionalRepository additionalRepository;

	public List<Beverage> findAllBeverages() {
		return this.beverageRepository.findAll();
	}

	public Beverage findBeverageById(Long beverage_id) throws NotFoundException {
		return this.beverageRepository.findById(beverage_id).orElseThrow(() -> new NotFoundException());
	}

	public boolean saveBeverage(Beverage beverage) {
		try {
			return this.beverageRepository.save(beverage) != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public List<Dessert> findAllDessert() {
		return this.dessertRepository.findAll();
	}

	public Dessert findDessertById(Long dessert_id) throws NotFoundException {
		return this.dessertRepository.findById(dessert_id).orElseThrow(() -> new NotFoundException());
	}

	public boolean saveDessert(Dessert dessert) {
		try {
			return this.dessertRepository.save(dessert) != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public Meal findMealById(Long meal_id) throws NotFoundException {
		return this.mealReposiory.findById(meal_id).orElseThrow(() -> new NotFoundException());
	}

	public boolean saveMeal(Meal meal) {
		try {
			return this.mealReposiory.save(meal) != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	public boolean saveBeverageAdditional(BeverageAdditional additional) {
		try {
			return this.additionalRepository.save(additional) != null;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
}
