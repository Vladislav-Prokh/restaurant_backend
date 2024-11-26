package delivery.app.DTO;

import java.util.List;

import delivery.app.entities.OrderedAdditional;

public class OrderRequestDTO {

	private Long lunch_main_course_id;
	private Long lunch_dessert_id;
	private Long beverage_id;
	private Long waiter_id;
	private List<OrderedAdditional> orderedAdditions;
	
	
	public List<OrderedAdditional> getOrderedAdditions() {
		return orderedAdditions;
	}

	public void setOrderedAdditions(List<OrderedAdditional> orderedAdditions) {
		this.orderedAdditions = orderedAdditions;
	}

	public Long getWaiter_id() {
		return waiter_id;
	}

	public void setWaiter_id(Long waiter_id) {
		this.waiter_id = waiter_id;
	}

	public Long getLunch_main_course_id() {
		return lunch_main_course_id;
	}

	public void setLunch_main_course_id(Long lunch_main_course_id) {
		this.lunch_main_course_id = lunch_main_course_id;
	}

	public Long getLunch_dessert_id() {
		return lunch_dessert_id;
	}

	public void setLunch_dessert_id(Long lunch_dessert_id) {
		this.lunch_dessert_id = lunch_dessert_id;
	}

	public Long getBeverage_id() {
		return beverage_id;
	}

	public void setBeverage_id(Long beverage_id) {
		this.beverage_id = beverage_id;
	}

}
