package delivery.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity
@Table(name = "desserts")
public class Dessert {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "dessert_id")
	private Long dessertId;
	@Column(length = 50,name = "dessert_name")
	private String dessertName;
	@Column(length = 50,name = "dessert_price")
	private float dessertPrice;



	public Dessert() {}
	public Long getDessertId() {
		return dessertId;
	}
	public void setDessertId(Long dessertId) {
		this.dessertId = dessertId;
	}
	public String getDessertName() {
		return dessertName;
	}
	public void setDessertName(String dessertName) {
		this.dessertName = dessertName;
	}
	public float getDessertPrice() {
		return dessertPrice;
	}
	public void setDessertPrice(float dessertPrice) {
		this.dessertPrice = dessertPrice;
	}
	public Dessert(String dessertName, float dessertPrice) {
		super();
		this.dessertName = dessertName;
		this.dessertPrice = dessertPrice;
	}
	
}
