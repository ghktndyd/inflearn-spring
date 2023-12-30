package jpabook.jpashop.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jpabook.jpashop.domain.item.Item;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository
public class ItemRepository {

	private final EntityManager em;

	public void save(Item item) {
		if (item.getId() == null) { // 아이디가 없으면 새거라고 생각하고 새로 저장
			em.persist(item); // persist -> 완전히 새로 저장
		} else { // 아이디가 있다면 업데이트 하는 걸로 저장
			em.merge(item); // merge -> 업데이트라고 이해한다?? 추후 설명
		}
	}

	public Item findOne(Long id) {
		return em.find(Item.class, id);
	}

	public List<Item> findAll() {
		return em.createQuery("select i from Item i", Item.class)
			.getResultList();
	}
}
