package delivery.app.DTO;

import java.util.List;

import delivery.app.entities.CuisineType;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;


public class OrderRequestDTO {

	private Long lunchId;
	@Enumerated(EnumType.STRING)
	private CuisineType mainCourseCuisine;
	@Enumerated(EnumType.STRING)
	private CuisineType dessertCuisine;
	private Long beverageId;
	private Long waiterId;
	private List<OrderedAdditionalDTO> orderedAdditions;
	


	public OrderRequestDTO(Long lunchId, CuisineType mainCourseCuisine, CuisineType dessertCuisine, Long beverageId,
			Long waiterId, List<OrderedAdditionalDTO> orderedAdditions) {
		super();
		this.lunchId = lunchId;
		this.mainCourseCuisine = mainCourseCuisine;
		this.dessertCuisine = dessertCuisine;
		this.beverageId = beverageId;
		this.waiterId = waiterId;
		this.orderedAdditions = orderedAdditions;
	}

	public Long getLunchId() {
		return lunchId;
	}

	public void setLunchId(Long lunchId) {
		this.lunchId = lunchId;
	}

	public Long getBeverageId() {
		return beverageId;
	}

	public void setBeverageId(Long beverageId) {
		this.beverageId = beverageId;
	}

	public Long getWaiterId() {
		return waiterId;
	}

	public void setWaiterId(Long waiterId) {
		this.waiterId = waiterId;
	}

	public List<OrderedAdditionalDTO> getOrderedAdditions() {
		return orderedAdditions;
	}

	public void setOrderedAdditions(List<OrderedAdditionalDTO> orderedAdditions) {
		this.orderedAdditions = orderedAdditions;
	}

	public CuisineType getMainCourseCuisine() {
		return mainCourseCuisine;
	}

	public void setMainCourseCuisine(CuisineType mainCourseCuisine) {
		this.mainCourseCuisine = mainCourseCuisine;
	}

	public CuisineType getDessertCuisine() {
		return dessertCuisine;
	}

	public void setDessertCuisine(CuisineType dessertCuisine) {
		this.dessertCuisine = dessertCuisine;
	}


}
