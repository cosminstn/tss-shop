package school.tss.shop.persistence.dao;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import school.tss.shop.BaseTest;
import school.tss.shop.persistence.entity.Item;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

class ItemDAOTest extends BaseTest {

	@Autowired
	private ItemDAO itemDAO;

	@Test
	@Disabled
	void create() {
		Item item = new Item();
		item.setCategoryId(1);
		item.setName("Random product");
		item.setPrice(20);

		//itemDAO.create(item);

		assertEquals("Random product", itemDAO.create(item).getName());
	}


	@Test
	public void updateItemInDB() {

		Item item = new Item();

		item.setId(1);
		item.setName("Item1");
		item.setPrice(5000);
		item.setCategoryId(1);
		item.setCurrentDiscount(0);

		int result = itemDAO.update(item);

		assertEquals(1, result);

	}

	@Test
	public void updateItemNotInDB() {

		Item item = new Item();


		item.setId(7);
		item.setName("Item1");
		item.setPrice(5000);
		item.setCategoryId(1);
		item.setCurrentDiscount(0);

		int result = itemDAO.update(item);

		assertEquals(0, result);

	}



	@Test
	public void getByName() {

		assertEquals("Apple Macbook Pro", itemDAO.getByName("Apple Macbook Pro").getName());
		assertEquals("Lenovo X1 Carbon", itemDAO.getByName("Lenovo X1 Carbon").getName());
		assertEquals("Apple iPhone 11", itemDAO.getByName("Apple iPhone 11").getName());

	}

	@Test
	public void findAll(){

		List<Item> allItems = itemDAO.findAll();
		assertEquals(3, allItems.size());
		assertNotEquals(0, allItems.size());

	}

	@Test
	public void deleteById(){

		assertEquals(1, itemDAO.deleteById(2));
		assertEquals(0, itemDAO.deleteById(13));

	}


}