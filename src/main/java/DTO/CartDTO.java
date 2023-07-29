package DTO;

import java.util.List;

import entity.CartItem;

public class CartDTO extends DTO {
 
	private DTO user;
	
	private List<CartItem> items;
	
	private float cartTotal;

	public CartDTO() {}
	
	public DTO getUser() {
		return user;
	}

	public void setUser(DTO user) {
		this.user = user;
	}

	public List<CartItem> getItems() {
		return items;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}

	public float getCartTotal() {
		return cartTotal;
	}

	public void setCartTotal(float total) {
		this.cartTotal = total;
	}
}
