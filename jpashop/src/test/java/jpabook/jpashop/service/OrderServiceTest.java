package jpabook.jpashop.service;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.Address;
import jpabook.jpashop.domain.Member;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderStatus;
import jpabook.jpashop.domain.item.Book;
import jpabook.jpashop.exception.NotEnoughStockException;
import jpabook.jpashop.repository.OrderRepository;

@Transactional
@SpringBootTest
@RunWith(SpringRunner.class)
public class OrderServiceTest {

	@Autowired
	EntityManager em;

	@Autowired
	OrderService orderService;

	@Autowired
	OrderRepository orderRepository;

	@Test
	public void 상품주문() throws Exception {
		//Given
		Member member = createMember();

		Book book = createBook("재밌는 Jpa", 10000, 10);

		int orderCount = 2;

		//When
		Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

		//Then
		Order getOrder = orderRepository.findOne(orderId);

		assertEquals("상품 주문시 상태는 ORDER", OrderStatus.ORDER, getOrder.getStatus());
		assertEquals("주문한 상품 종류가 정확해야 한다.", 1, getOrder.getOrderItems().size());
		assertEquals("주문 가격은 가격 * 수량이다.", orderCount * 10000, getOrder.getTotalPrice());
		assertEquals("주문 수량만큼 재고가 줄어야한다.", 8, book.getStockQuantity());
	}

	@Test(expected = NotEnoughStockException.class)
	public void 상품주문_재고수량초과() throws Exception {
		//Given
		Member member = createMember();
		Book book = createBook("재밌는 Jpa", 10000, 10);

		int orderCount = 11;

		//When
		orderService.order(member.getId(), book.getId(), orderCount);

		//Then
		fail("재고 수량 부족 예외가 발생되어야 한다.");
	}

	@Test
	public void 주문취소() throws Exception {
		//Given
		Member member = createMember();
		Book book = createBook("메롱", 10000, 10);

		int orderCount = 2;

		Long orderId = orderService.order(member.getId(), book.getId(), orderCount);

		//When
		orderService.cancelOrder(orderId);

		//Then
		Order getOrder = orderRepository.findOne(orderId);

		assertEquals("주문 취소시 상태는 CANCEL로 변경", OrderStatus.CANCEL, getOrder.getStatus());
		assertEquals("주문이 취소되었다면 재고가 복구되어야 한다.", 10, book.getStockQuantity());
	}

	private Book createBook(String name, int price, int stockQuantity) {
		Book book = new Book();
		book.setName(name);
		book.setPrice(price);
		book.setStockQuantity(stockQuantity);
		em.persist(book);
		return book;
	}

	private Member createMember() {
		Member member = new Member();
		member.setName("회원1");
		member.setAddress(new Address("서울", "경기", "123-123"));
		em.persist(member);
		return member;
	}
}