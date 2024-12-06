package delivery.app.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "beverage_additionals")
public class BeverageAdditional {
	@JsonProperty("beverage_additional_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "beverage_additional_id")
	private Long beverageAdditionalId;

	@Column(length = 20, nullable = false, name = "beverage_additional_name")
	private String beverageAdditionalName;

	@Column(nullable = false, name = "beverage_additional_price")
	private float beverageAdditionalPrice;

	public Long getBeverageAdditionalId() {
		return beverageAdditionalId;
	}

	public void setBeverageAdditionalId(Long beverageAdditionalId) {
		this.beverageAdditionalId = beverageAdditionalId;
	}

	public String getBeverageAdditionalName() {
		return beverageAdditionalName;
	}

	public void setBeverageAdditionalName(String beverageAdditionalName) {
		this.beverageAdditionalName = beverageAdditionalName;
	}

	public float getBeverageAdditionalPrice() {
		return beverageAdditionalPrice;
	}

	public void setBeverageAdditionalPrice(float beverageAdditionalPrice) {
		this.beverageAdditionalPrice = beverageAdditionalPrice;
	}

	public BeverageAdditional() {
	}

	public BeverageAdditional(Long beverageAdditionalId, String beverageAdditionalName, float beverageAdditionalPrice) {
		super();
		this.beverageAdditionalId = beverageAdditionalId;
		this.beverageAdditionalName = beverageAdditionalName;
		this.beverageAdditionalPrice = beverageAdditionalPrice;
	}

}
