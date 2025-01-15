package delivery.app.entities;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orders_id")
	@JsonProperty("orders_id")
	private Long orderId;
	@JsonProperty("created_at")
	@Column(name="created_at")
	private LocalDateTime createdAt;
	@Column(name="main_course_price")
	private float mainCoursePrice;
	@Column(name="dessert_price")
	private float dessertPrice;
	@Column(name="beverage_price")
	private float beveragePrice;
	@OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	@JoinColumn(name = "lunch_id", nullable = true)
	private Lunch lunch;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = true, name="main_course_cuisine")
	private CuisineType mainCourseCuisine;

	@Enumerated(EnumType.STRING)
	@Column(nullable = true, name="dessert_cuisine")
	private CuisineType dessertCuisine;

	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "beverage_id")
	private Beverage beverage;

	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "employee_id")
	private Employee employee;

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,orphanRemoval = true)
	@JoinColumn(name = "orders_id")
	private List<OrderedAdditional> orderedBeverageAdditionals;
	public List<OrderedAdditional> getOrderedBeverageAdditionals() {
		return orderedBeverageAdditionals;
	}
	public Order(){}
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}
	public float getMainCoursePrice() {
		return mainCoursePrice;
	}
	public void setMainCoursePrice(float mainCoursePrice) {
		this.mainCoursePrice = mainCoursePrice;
	}
	public float getDessertPrice() {
		return dessertPrice;
	}
	public void setDessertPrice(float dessertPrice) {
		this.dessertPrice = dessertPrice;
	}
	public float getBeveragePrice() {
		return beveragePrice;
	}
	public void setBeveragePrice(float beveragePrice) {
		this.beveragePrice = beveragePrice;
	}
	public Lunch getLunch() {
		return lunch;
	}
	public void setLunch(Lunch lunch) {
		this.lunch = lunch;
	}
	public Beverage getBeverage() {
		return beverage;
	}
	public void setBeverage(Beverage beverage) {
		this.beverage = beverage;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public void setOrderedBeverageAdditionals(List<OrderedAdditional> orderedBeverageAdditionals) {
		this.orderedBeverageAdditionals = orderedBeverageAdditionals;
	}
	public Employee getEmployee() {
		return employee;
	}
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
	
	public CuisineType getMainCourseCuisine() {
		return mainCourseCuisine;
	}
	public void setMainCourseCuisine(CuisineType mainCourseCuisine) {
		this.mainCourseCuisine = mainCourseCuisine;
	}
	public CuisineType getDessertCuisine() {
		return dessertCuisine;
	}
	public void setDessertCuisine(CuisineType dessertCuisine) {
		this.dessertCuisine = dessertCuisine;
	}
	public Order(Long orderId, LocalDateTime createdAt, float mainCoursePrice, float dessertPrice, float beveragePrice,
			Lunch lunch, CuisineType mainCourseCuisine, CuisineType dessertCuisine, Beverage beverage,
			Employee employee, List<OrderedAdditional> orderedBeverageAdditionals) {
		super();
		this.orderId = orderId;
		this.createdAt = createdAt;
		this.mainCoursePrice = mainCoursePrice;
		this.dessertPrice = dessertPrice;
		this.beveragePrice = beveragePrice;
		this.lunch = lunch;
		this.mainCourseCuisine = mainCourseCuisine;
		this.dessertCuisine = dessertCuisine;
		this.beverage = beverage;
		this.employee = employee;
		this.orderedBeverageAdditionals = orderedBeverageAdditionals;
	}

	

}
