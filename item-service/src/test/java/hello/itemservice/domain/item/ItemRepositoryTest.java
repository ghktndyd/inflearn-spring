package hello.itemservice.domain.item;

import static org.assertj.core.api.Assertions.*;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

class ItemRepositoryTest {

	ItemRepository itemRepository = new ItemRepository();

	@AfterEach
		// 테스트 하나 할 때마다 실행됨
	void afterEach() {
		itemRepository.clearStore();
	}

	@Test
	void save() {
		// Given
		Item item = new Item("itemA", 10000, 10);

		// When
		Item saveItem = itemRepository.save(item);

		// Then
		Item findItem = itemRepository.findById(item.getId());

		assertThat(findItem).isEqualTo(saveItem);
	}

	@Test
	void findAll() {
		// Given
		Item item1 = new Item("item1", 10000, 10);
		Item item2 = new Item("item2", 10000, 20);

		itemRepository.save(item1);
		itemRepository.save(item2);

		// When
		List<Item> items = itemRepository.findAll();

		// Then
		assertThat(items.size()).isEqualTo(2);
		assertThat(items).contains(item1, item2);
	}

	@Test
	void updateItem() {
		// Given
		Item item = new Item("item1", 10000, 10);

		Item saveItem = itemRepository.save(item);
		Long itemId = saveItem.getId();

		// When
		Item updateParam = new Item("item2", 20000, 20);
		itemRepository.update(itemId, updateParam);

		// Then
		Item findItem = itemRepository.findById(itemId);

		assertThat(findItem.getItemName()).isEqualTo(updateParam.getItemName());
		assertThat(findItem.getPrice()).isEqualTo(updateParam.getPrice());
		assertThat(findItem.getQuantity()).isEqualTo(updateParam.getQuantity());
	}
}