package delivery.app.entities;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
@Entity
@Table(name = "meals")
public class Meal {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty("meal_id")
	private Long mealId;
	@Column(length = 50,name = "meal_name")
	private String mealName;
	@Column(name = "meal_price")
	private float mealPrice;
	

	public Long getMealId() {
		return mealId;
	}

	public void setMealId(Long mealId) {
		this.mealId = mealId;
	}

	public String getMealName() {
		return mealName;
	}

	public void setMealName(String mealName) {
		this.mealName = mealName;
	}

	public float getMealPrice() {
		return mealPrice;
	}

	public void setMealPrice(float mealPrice) {
		this.mealPrice = mealPrice;
	}

	public Meal() {}

	public Meal(String mealName, float mealPrice) {
		super();
		this.mealName = mealName;
		this.mealPrice = mealPrice;
	}


}
