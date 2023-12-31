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
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
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

	//== 비즈니스 로직==// -> 도메인 주도 설계를 할 때는 도메인에 대한 비즈니스 로직을 엔티티 안에 작성한다.

	/**
	 * 재고를 증가하게 하는 메서드
	 * @param quantity
	 */
	public void addStock(int quantity) {
		this.stockQuantity += quantity;
	}

	/**
	 * 재고를 줄인다.
	 * @param quantity
	 */
	public void removeStock(int quantity) {
		int restStock = this.stockQuantity - quantity;

		if (restStock < 0) {
			throw new NotEnoughStockException("need more stock");
		}

		this.stockQuantity = restStock;
	}
}
