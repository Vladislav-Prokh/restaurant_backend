package delivery.app.DTO;

public class OrderedAdditionalDTO {

	private Long beverageId;
	private Long additionId;
	private int quantity;


	public OrderedAdditionalDTO(Long beverageId, Long additionId, int quantity) {
		super();
		this.beverageId = beverageId;
		this.additionId = additionId;
		this.quantity = quantity;
	}
	public Long getBeverageId() {
		return beverageId;
	}
	public void setBeverageId(Long beverageId) {
		this.beverageId = beverageId;
	}
	public Long getAdditionId() {
		return additionId;
	}
	public void setAdditionId(Long additionId) {
		this.additionId = additionId;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
