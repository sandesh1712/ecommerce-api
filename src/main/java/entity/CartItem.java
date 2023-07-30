package entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;


@Entity
@Table(name = "cart_items")
public class CartItem extends SuperEntity{
    
	@ManyToOne
	@JoinColumn(name = "cart_id")	
	private Cart cart;
	
	@ManyToOne
	@JoinColumn(name="product_id")
	private Product product;
	
	@Column(name="quantity")
	private int quantity;
	
	@Column(name="total")
	private float total;

	@JsonIgnore
	public Cart getCart() {
		return cart;
	}

	@JsonProperty
	public void setCart(Cart cart) {
		this.cart = cart;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public float getTotal() {
		return total;
	}

	public void setTotal(float total) {
		this.total = total;
	}

	@Override
	public boolean equals(Object obj) {
		CartItem item = (CartItem)obj;
		return this.getId()== item.getId();
	}	
}
