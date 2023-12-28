package jpabook.jpashop.domain.item;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.ManyToMany;
import jpabook.jpashop.domain.Category;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Entity
public abstract class Item {

	@Column(name = "item_id")
	@GeneratedValue
	@Id
	private Long id;

	private String name;

	private int stockQuantity;

	private int price;

	@ManyToMany(mappedBy = "items")
	private List<Category> categories = new ArrayList<>();
}
