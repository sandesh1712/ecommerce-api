package entity;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "products")
public class Product extends SuperEntity {
	@Column(name = "name")
	private String name;

	@Column(name = "desc")
	private String desc;

	@ManyToOne
	@JoinColumn(name = "category_id", nullable = false)
	private Category category;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<ProductInventory> productInventories;

	@OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
	private List<ProductImages> productImages;
}
