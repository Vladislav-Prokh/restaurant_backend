package delivery.app.entities;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.persistence.Table;

@Entity
@Table(name = "orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "orders_id")
	private Long order_id;

	@Column
	private LocalDateTime created_at;
	private float main_course_price;
	private float dessert_price;
	private float beverage_price;
	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "lunch_id")
	private Lunch lunch;

	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "beverage_id")
	private Beverage beverage;

	@OneToOne(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "waiter_id")
	private Waiter waiter;

	@OneToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinColumn(name = "orders_id")
	private List<OrderedAdditional> orderedBeverageAdditionals;
	public Order(){}
	Order(Lunch orderedLunch, Beverage orderedBeverage, Waiter servicingWaiter,List<OrderedAdditional>  orderedBeverageAdditionals) {
		this.lunch = orderedLunch;
		this.beverage = orderedBeverage;
		this.orderedBeverageAdditionals = orderedBeverageAdditionals;
		this.waiter = servicingWaiter;
		this.created_at = LocalDateTime.now();
		this.main_course_price = orderedLunch.getMainCourse().getMeal_price();
		this.dessert_price = orderedLunch.getDessert().getDessert_price();
		this.beverage_price = orderedBeverage.getBeverage_price();
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public float getMain_course_price() {
		return main_course_price;
	}

	public void setMain_course_price(float main_course_price) {
		this.main_course_price = main_course_price;
	}

	public float getDessert_price() {
		return dessert_price;
	}

	public void setDessert_price(float dessert_price) {
		this.dessert_price = dessert_price;
	}

	public float getBeverage_price() {
		return beverage_price;
	}

	public void setBeverage_price(float beverage_price) {
		this.beverage_price = beverage_price;
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

	public Waiter getWaiter() {
		return waiter;
	}

	public void setWaiter(Waiter waiter) {
		this.waiter = waiter;
	}
	

}
