package delivery.app.DTO;

import java.util.List;


public class OrderRequestDTO {

	private Long lunchId;
	private Long beverageId;
	private Long waiterId;
	private List<OrderedAdditionalDTO> orderedAdditions;
	
	public OrderRequestDTO(Long lunchId, Long beverageId, Long waiterId, List<OrderedAdditionalDTO> orderedAdditions) {
		super();
		this.lunchId = lunchId;
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


}
