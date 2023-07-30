package entity;

import java.util.List;

import org.hibernate.service.spi.Stoppable;
import org.modelmapper.ModelMapper;

import com.fasterxml.jackson.annotation.JsonIgnore;

import DTO.CartDTO;
import DTO.ReviewDTO;
import helper.DTOHelper;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import types.CartStatus;

@Entity
@Table(name = "carts")
public class Cart extends SuperEntity {
	
	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private User user;

	@JsonIgnore
	@OneToMany(mappedBy = "cart",cascade = CascadeType.ALL,orphanRemoval = true)
	private List<CartItem> items;

	@Column(name = "status")
	@Enumerated(value = EnumType.STRING)
	private CartStatus status;

	@Column(name = "cart_total")
	private float cartTotal=0;

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}	

	public List<CartItem> getItems() {
		return items;
	}

	public void setItems(List<CartItem> items) {
		this.items = items;
	}

	public CartStatus getStatus() {
		return status;
	}

	public void setStatus(CartStatus status) {
		this.status = status;
	}

	public float getCartTotal() {
		return cartTotal;
	}

	public void setCartTotal(float cartTotal) {
		this.cartTotal = cartTotal;
	}
	
	public CartDTO convertToDto(){
		 ModelMapper mapper = DTOHelper.getDTOmapper();
		 CartDTO dto = 	mapper.map(this, CartDTO.class);	 
		 dto.getUser().setId(this.user.getId());
		 return dto;
	}	
}	