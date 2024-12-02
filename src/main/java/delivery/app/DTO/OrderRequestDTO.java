package delivery.app.DTO;

import java.util.List;


public class OrderRequestDTO {

	private Long lunch_id;
	private Long beverage_id;
	private Long waiter_id;
	private List<OrderedAdditionalDTO> orderedAdditions;
	
	
	public OrderRequestDTO(long lunch_id, long beverage_id, long waiter_id,List<OrderedAdditionalDTO> orderedAdditions) {
		this.lunch_id = lunch_id;
		this.beverage_id = beverage_id;
		this.waiter_id = waiter_id;
		this.orderedAdditions = orderedAdditions;
	}

	public List<OrderedAdditionalDTO> getOrderedAdditions() {
		return orderedAdditions;
	}

	public void setOrderedAdditions(List<OrderedAdditionalDTO> orderedAdditions) {
		this.orderedAdditions = orderedAdditions;
	}

	public Long getWaiter_id() {
		return waiter_id;
	}

	public void setWaiter_id(Long waiter_id) {
		this.waiter_id = waiter_id;
	}


	public Long getLunch_id() {
		return lunch_id;
	}

	public void setLunch_id(Long lunch_id) {
		this.lunch_id = lunch_id;
	}

	public Long getBeverage_id() {
		return beverage_id;
	}

	public void setBeverage_id(Long beverage_id) {
		this.beverage_id = beverage_id;
	}

}
