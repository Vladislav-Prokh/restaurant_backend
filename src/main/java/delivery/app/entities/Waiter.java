package delivery.app.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "waiters")
public class Waiter {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long waiter_id;

	@Column(length = 20)
	private String waiter_name;
	@Column(length = 20)
	private String waiter_surname;
	@Column(length = 20)
	private String waiter_lastname;

	public String getWaiter_name() {
		return waiter_name;
	}

	public void setWaiter_name(String waiter_name) {
		this.waiter_name = waiter_name;
	}

	public String getWaiter_surename() {
		return waiter_surname;
	}

	public void setWaiter_surename(String waiter_surename) {
		this.waiter_surname = waiter_surename;
	}

	public String getWaiter_lastname() {
		return waiter_lastname;
	}

	public void setWaiter_lastname(String waiter_lastname) {
		this.waiter_lastname = waiter_lastname;
	}

	public Waiter(String waiter_name, String waiter_surname, String waiter_lastname) {
		super();
		this.waiter_name = waiter_name;
		this.waiter_surname = waiter_surname;
		this.waiter_lastname = waiter_lastname;
	}
	public Waiter() {}

}
