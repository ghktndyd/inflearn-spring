package hello.itemservice.domain.item;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

import org.springframework.stereotype.Repository;

@Repository
public class ItemRepository {

	private static final Map<Long, Item> store = new HashMap<>() {
	};
	// 실제는 해쉬맵 사용 X, 실제 업무때는 멀티스레드로 동작하기 때문에 ConcurrentMap을 사용해야한다.
	private static long squence = 0L;
	// 얘도 이렇게 atomicLong 등 다른 걸 사용해야 한다.

	public Item save(Item item) {
		item.setId(++squence);
		store.put(item.getId(), item);

		return item;
	}

	public Item findById(Long id) {
		return store.get(id);
	}

	public List<Item> findAll() {
		return new ArrayList<>(store.values());
	}

	public void update(Long itemId, Item updateParam) {
		Item findItem = findById(itemId);

		findItem.setItemName(updateParam.getItemName());
		findItem.setPrice(updateParam.getPrice());
		findItem.setQuantity(updateParam.getQuantity());
	}

	public void clearStore() {
		store.clear(); // HashMap 데이터 다 날라감
	}
}
