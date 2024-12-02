package delivery.app.DTO;

public class OrderedAdditionalDTO {

	private Long beverage_id;
	private Long addition_id;
	private int quantity;
	public Long getBeverage_id() {
		return beverage_id;
	}
	public void setBeverage_id(Long beverage_id) {
		this.beverage_id = beverage_id;
	}

	public Long getAddition_id() {
		return addition_id;
	}
	public void setAddition_id(Long addition_id) {
		this.addition_id = addition_id;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
