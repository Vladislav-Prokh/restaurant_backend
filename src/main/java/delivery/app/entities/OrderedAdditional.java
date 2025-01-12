package delivery.app.entities;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
@Entity
@Table(name = "ordered_additionals")
public class OrderedAdditional {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderedAdditional_id;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "beverage_id", nullable = false)
    private Beverage beverage;
   
    @ManyToOne
    @JoinColumn(name = "orders_id", nullable = true)
    @JsonIgnore
    private Order order;
    @JsonProperty("beverageAdditional")
    @ManyToOne
    @JoinColumn(name = "beverage_additional_id", nullable = false)
    private BeverageAdditional beverageAdditional;

    @Column(nullable = false)
    private int quantity;

    public OrderedAdditional() {}

    public OrderedAdditional(Beverage beverage, Order order, BeverageAdditional beverageAdditional, int quantity) {
        this.beverage = beverage;
        this.order = order;
        this.beverageAdditional = beverageAdditional;
        this.quantity = quantity;
    }

	public Long getOrderedAdditional_id() {
        return orderedAdditional_id;
    }

    public void setOrderedAdditional_id(Long orderedAdditional_id) {
        this.orderedAdditional_id = orderedAdditional_id;
    }

    public Beverage getBeverage() {
        return beverage;
    }

    public void setBeverage(Beverage beverage) {
        this.beverage = beverage;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public BeverageAdditional getBeverageAdditional() {
        return beverageAdditional;
    }

    public void setBeverageAdditional(BeverageAdditional beverageAdditional) {
        this.beverageAdditional = beverageAdditional;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
