package jpabook.jpashop.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Table(name = "orders")
@Entity
public class Order {

	@Column(name = "order_id")
	@Id @GeneratedValue
	private Long id;

	@ManyToOne @JoinColumn(name = "member_id")
	private Member member;

	@OneToMany(mappedBy = "order")
	private List<OrderItem> orderItems = new ArrayList<>();

	@OneToOne @JoinColumn(name = "delivery_id")
	private Delivery delivery;

	private LocalDateTime orderDate; // 주문 시간

	@Enumerated(EnumType.STRING)
	private OrderStatus status; // 주문 상태 [ORDER, CANCEL]
}
