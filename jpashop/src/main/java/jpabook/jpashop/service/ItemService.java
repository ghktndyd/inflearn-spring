package jpabook.jpashop.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jpabook.jpashop.domain.item.Item;
import jpabook.jpashop.repository.ItemRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ItemService {

	private final ItemRepository itemRepository;

	@Transactional
	public void saveItem(Item item) {
		itemRepository.save(item);
	}

	public List<Item> findItems() {
		return itemRepository.findAll();
	}

	public Item findOne(Long itemId) {
		return itemRepository.findOne(itemId);
	}

	/**
	 * 변경감지로 업데이트
	 */
	@Transactional
	public void updateItem(Long itemId, String name, int price, int stockQuantity) {
		Item findItem = itemRepository.findOne(itemId);

		findItem.setName(name);
		findItem.setPrice(price);
		findItem.setStockQuantity(stockQuantity);

		// 원래 여기서 save를 호출해야 하지만 Jpa가 변경감지를 해서 자동으로 Update를 해주는가?
		// flush를 날린다?
		// flush -> Jpa가 변경이 발생한 애를 찾는다.
	}

}
