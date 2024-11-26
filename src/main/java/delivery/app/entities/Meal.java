package delivery.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "meals")
public class Meal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long meal_id;
	@Column(length = 50)
	private String meal_name;
	private float meal_price;
	@Enumerated(EnumType.STRING)
	private CuisineType cuisine;

	public String getMeal_name() {
		return meal_name;
	}

	public void setMeal_name(String meal_name) {
		this.meal_name = meal_name;
	}

	public float getMeal_price() {
		return meal_price;
	}

	public void setMeal_price(float meal_price) {
		this.meal_price = meal_price;
	}

	public CuisineType getCuisine() {
		return cuisine;
	}

	public void setCuisine(CuisineType cuisine) {
		this.cuisine = cuisine;
	}
	public Meal() {}
}
