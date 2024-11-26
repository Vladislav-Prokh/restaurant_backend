package delivery.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "beverage_additionals")
public class BeverageAdditional {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long beverage_additional_id;

	@Column(length = 20, nullable = false)
	private String beverage_additional_name;

	@Column(nullable = false)
	private float beverage_additional_price;

	public BeverageAdditional(String beverage_additional_name, float beverage_additional_price) {
		this.beverage_additional_name = beverage_additional_name;
		this.beverage_additional_price = beverage_additional_price;
	}

	public BeverageAdditional() {
	}

	public String getBeverage_additional_name() {
		return beverage_additional_name;
	}

	public void setBeverage_additional_name(String beverage_additional_name) {
		this.beverage_additional_name = beverage_additional_name;
	}

	public float getBeverage_additional_price() {
		return beverage_additional_price;
	}

	public void setBeverage_additional_price(float beverage_additional_price) {
		this.beverage_additional_price = beverage_additional_price;
	}

	public Long getBeverage_additional_id() {
		return beverage_additional_id;
	}

	public void setBeverage_additional_id(Long beverage_additional_id) {
		this.beverage_additional_id = beverage_additional_id;
	}

}
