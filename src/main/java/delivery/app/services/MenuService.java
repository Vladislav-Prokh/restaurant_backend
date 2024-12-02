package delivery.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import delivery.app.entities.Beverage;
import delivery.app.entities.BeverageAdditional;
import delivery.app.entities.Dessert;
import delivery.app.entities.Meal;
import delivery.app.repositories.BeverageAdditionalRepository;
import delivery.app.repositories.BeverageRepository;
import delivery.app.repositories.DessertRepository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import delivery.app.DTO.LunchRequestDTO;
import delivery.app.entities.Lunch;
import delivery.app.exceptions.DatabaseInsertionException;
import delivery.app.exceptions.ResourceNotFoundException;
import delivery.app.repositories.LunchRepository;
import delivery.app.repositories.MealRepository;

@Service
public class MenuService {

	@Autowired
	private BeverageRepository beverageRepository;
	@Autowired
	private DessertRepository dessertRepository;
	@Autowired
	private MealRepository mealRepository;
	@Autowired
	private BeverageAdditionalRepository additionalRepository;
	@Autowired
	private LunchRepository lunchRepository;
	@Autowired
	private BeverageAdditionalRepository beverageAdditionalRepository;
	private String NOT_FOUND_MESSAGE = "Resource not found";

	public Beverage findBeverageById(Long beverage_id) {
		return this.beverageRepository.findById(beverage_id)
				.orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_MESSAGE));
	}

	public void saveBeverage(Beverage beverage) {
		saveEntity(beverage, beverageRepository, "saving beverage failed");
	}


	public Dessert findDessertById(Long dessert_id) {
		return this.dessertRepository.findById(dessert_id)
				.orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_MESSAGE));
	}

	public void saveDessert(Dessert dessert) {
		saveEntity(dessert, dessertRepository, "saving dessert failed");
	}

	public Meal findMealById(Long meal_id) {
		return this.mealRepository.findById(meal_id)
				.orElseThrow(() -> new ResourceNotFoundException(NOT_FOUND_MESSAGE));
	}

	public void saveMeal(Meal meal) {
		saveEntity(meal, mealRepository, "saving meal failed");
	}

	public void saveBeverageAdditional(BeverageAdditional additional) {
		saveEntity(additional, additionalRepository, "saving additional failed");
	}

	public void saveLunch(LunchRequestDTO lunchRequestDto) {
		Long mainCourseId = lunchRequestDto.getMainCourseId();
		Long dessertId = lunchRequestDto.getDessertId();
		Meal mainCourse = this.mealRepository.findById(mainCourseId)
				.orElseThrow(() -> new ResourceNotFoundException("main course not found"));
		Dessert dessert = this.dessertRepository.findById(dessertId)
				.orElseThrow(() -> new ResourceNotFoundException("dessert not found"));
		Lunch lunch = new Lunch(mainCourse, dessert);
		lunch = saveEntity(lunch, lunchRepository, "saving lunch failed");

	}

	private <T> T saveEntity(T entity, JpaRepository<T, Long> repository, String errorMessage) {

		T savedEntity = repository.save(entity);
		if (savedEntity == null) {
			throw new DatabaseInsertionException(errorMessage);
		}
		return savedEntity;

	}

	public <T> void deleteEntityById(Long id, JpaRepository<T, Long> repository) {
		Optional<T> entity = repository.findById(id);
		entity.ifPresent(repository::delete);
	}

	public void deleteBeverageAdditional(Long id) {
		deleteEntityById(id, beverageAdditionalRepository);
	}

	public void deleteBeverage(Long id) {
		deleteEntityById(id, beverageRepository);
	}

	public void deleteMeal(Long id) {
		deleteEntityById(id, mealRepository);
	}

	public void deleteDessert(Long id) {
		deleteEntityById(id, dessertRepository);
	}

	public void deleteLunch(Long id) {
		deleteEntityById(id, lunchRepository);
	}

	public void deleteAdditional(Long id) {
		deleteEntityById(id, additionalRepository);
	}

	public Page<Beverage> getBeverages(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return beverageRepository.findAll(pageable);
	}

	public Page<BeverageAdditional> getAdditionals(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return additionalRepository.findAll(pageable);
	}

	public Page<Lunch> getLunches(int page, int size) {
		Pageable pageable = PageRequest.of(page, size);
		return lunchRepository.findAll(pageable);
	}

}
