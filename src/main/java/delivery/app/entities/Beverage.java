package delivery.app.entities;

import java.util.List;

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
	private Long beverage_id;
	@Column(length = 20)
	private String beverage_name;
	private float beverage_price;

	@OneToMany(mappedBy = "beverage")
	private List<OrderedAdditional> orderedAdditionals;
	
	public String getBeverage_name() {
		return beverage_name;
	}
	public void setBeverage_name(String beverage_name) {
		this.beverage_name = beverage_name;
	}
	public float getBeverage_price() {
		return beverage_price;
	}
	public void setBeverage_price(float beverage_price) {
		this.beverage_price = beverage_price;
	}
	Beverage(){}
	
}
