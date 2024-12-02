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
@Table(name = "desserts")
public class Dessert {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long dessert_id;
	@Column(length = 50)
	private String dessert_name;
	private float dessert_price;
	@Enumerated(EnumType.STRING)
	private CuisineType cuisine;
	public Long getDessert_id() {
		return dessert_id;
	}
	public void setDessert_id(Long dessert_id) {
		this.dessert_id = dessert_id;
	}
	public String getDessert_name() {
		return dessert_name;
	}
	public void setDessert_name(String dessert_name) {
		this.dessert_name = dessert_name;
	}
	public float getDessert_price() {
		return dessert_price;
	}
	public void setDessert_price(float dessert_price) {
		this.dessert_price = dessert_price;
	}
	public CuisineType getCuisine() {
		return cuisine;
	}
	public void setCuisine(CuisineType cuisine) {
		this.cuisine = cuisine;
	}
	public Dessert() {}
	public Dessert(String dessert_name, float dessert_price, CuisineType cuisine) {
		super();
		this.dessert_name = dessert_name;
		this.dessert_price = dessert_price;
		this.cuisine = cuisine;
	}

	
}
