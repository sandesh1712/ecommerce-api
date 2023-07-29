package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "order_details")
public class OrderDetail extends SuperEntity {
   
	@OneToOne
	@JoinColumn(name = "order_id")
	private Order order;
	
//	@OneToOne
//	@JoinColumn(name = "cart_id")
//	private Cart cart;
	
	@OneToOne
	@JoinColumn(name = "payment_id")
	private PaymentCard card;
	
	@Column(name = "total")
	private float total;

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}
//
//	public Cart getCart() {
//		return cart;
//	}
//
//	public void setCart(Cart cart) {
//		this.cart = cart;
//	}

	public PaymentCard getCard() {
		return card;
	}

	public void setCard(PaymentCard card) {
		this.card = card;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}
}
