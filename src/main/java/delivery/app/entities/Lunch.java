package delivery.app.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "lunches")
public class Lunch {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long lunch_id;

	@OneToOne
	@JoinColumn(name = "meal_id", nullable = true)
	@OnDelete(action = OnDeleteAction.SET_NULL)
	private Meal mainCourse;

	@OneToOne
	@JoinColumn(name = "dessert_id", nullable = true)
	@OnDelete(action = OnDeleteAction.SET_NULL)
	private Dessert dessert;

	public Meal getMainCourse() {
		return mainCourse;
	}

	public void setMainCourse(Meal mainCourse) {
		this.mainCourse = mainCourse;
	}

	public Dessert getDessert() {
		return dessert;
	}

	public void setDessert(Dessert dessert) {
		this.dessert = dessert;
	}

	public Long getLunch_id() {
		return lunch_id;
	}

	public void setLunch_id(Long lunch_id) {
		this.lunch_id = lunch_id;
	}

	public Lunch() {
	}

	public Lunch(Meal mainCourse, Dessert dessert) {
		this.mainCourse = mainCourse;
		this.dessert = dessert;
	}


}
