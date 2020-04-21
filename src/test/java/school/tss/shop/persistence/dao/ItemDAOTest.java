package school.tss.shop.persistence.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import school.tss.shop.BaseTest;
import school.tss.shop.persistence.entity.Item;

class ItemDAOTest extends BaseTest {

	@Autowired
	private ItemDAO itemDAO;

	@Test
	void create() {
		Item item = new Item();
		item.setCategoryId(1);
		item.setName("Random product");
		item.setPrice(20);

		itemDAO.create(item);
	}
}