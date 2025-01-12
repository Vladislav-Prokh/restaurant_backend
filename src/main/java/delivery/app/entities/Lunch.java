package delivery.app.entities;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import jakarta.persistence.Column;
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
	@Column(name = "lunch_id")
	private Long lunchId;

	@OneToOne
	@JoinColumn(name = "mealId", nullable = true)
	@OnDelete(action = OnDeleteAction.SET_NULL)
	private Meal mainCourse;

	@OneToOne
	@JoinColumn(name = "dessertId", nullable = true)
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

	public Long getLunchId() {
		return lunchId;
	}

	public void setLunchId(Long lunchId) {
		this.lunchId = lunchId;
	}

	public Lunch() {
	}

	public Lunch(Meal mainCourse, Dessert dessert) {
		this.mainCourse = mainCourse;
		this.dessert = dessert;
	}

}
