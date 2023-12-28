package jpabook.jpashop.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jpabook.jpashop.domain.item.Item;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Entity
public class OrderItem {

	@Column(name = "order_item_id")
	@GeneratedValue
	@Id
	private Long id;

	@ManyToOne
	@JoinColumn(name = "item_id")
	private Item item;

	@JoinColumn(name = "order_id")
	@ManyToOne
	private Order order; // 하나의 오더가 여러개의 아이템을 가질 수 있다.

	private int orderPrice; // 주문 가격

	private int count; // 주문 수량
}
