package delivery.app.entities;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "beverages")
public class Beverage {
	@JsonProperty("beverage_id")
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "beverage_id")
	private Long beverageId;
	@Column(length = 20, name = "beverage_name")
	private String beverageName;
	@Column(name = "beverage_price")
	private float beveragePrice;
	@JsonIgnore
	@OneToMany(mappedBy = "beverage")
	private List<OrderedAdditional> orderedAdditionals;

	public Beverage() {
	}

	public Long getBeverageId() {
		return beverageId;
	}

	public void setBeverageId(Long beverageId) {
		this.beverageId = beverageId;
	}

	public String getBeverageName() {
		return beverageName;
	}

	public void setBeverageName(String beverageName) {
		this.beverageName = beverageName;
	}

	public float getBeveragePrice() {
		return beveragePrice;
	}

	public void setBeveragePrice(float beveragePrice) {
		this.beveragePrice = beveragePrice;
	}

	public List<OrderedAdditional> getOrderedAdditionals() {
		return orderedAdditionals;
	}

	public void setOrderedAdditionals(List<OrderedAdditional> orderedAdditionals) {
		this.orderedAdditionals = orderedAdditionals;
	}

	public Beverage(String beverageName, float beveragePrice, List<OrderedAdditional> orderedAdditionals) {
		super();
		this.beverageName = beverageName;
		this.beveragePrice = beveragePrice;
		this.orderedAdditionals = orderedAdditionals;
	}

	public Beverage(String beverageName, float beveragePrice) {
		super();
		this.beverageName = beverageName;
		this.beveragePrice = beveragePrice;
	}
	

}
