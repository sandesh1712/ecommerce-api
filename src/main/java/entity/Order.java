package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import types.OrderStatus;

@Entity
@Table(name="orders")
public class Order extends SuperEntity {
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;
	
	@OneToOne(mappedBy = "order")
	private OrderDetail detail;
	
	@Column
	private OrderStatus status;
	
	@Column(name = "date")
	private String orderDate;
	
}
