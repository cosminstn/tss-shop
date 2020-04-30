package school.tss.shop.persistence.dao;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import school.tss.shop.BaseTest;
import school.tss.shop.persistence.entity.Item;
import school.tss.shop.persistence.entity.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest extends BaseTest {

	@Autowired
	private UserDAO userDAO;

	@Test
	@Disabled
	void createUserTest() {
		User user = new User();
		user.setUsername("usertry1");
		user.setPassword("jfdoisajfidosa");
		user.setRole(User.Role.MANAGEMENT);
		//userDAO.create(user);

		assertEquals("usertry1", userDAO.create(user).getUsername());


	}


	@Test
	void getByUsername() {
		assertEquals("cosmin", userDAO.getByUsername("cosmin").getUsername());
		assertEquals("andra", userDAO.getByUsername("andra").getUsername());
		assertEquals("raissa", userDAO.getByUsername("raissa").getUsername());
	}

	@Test
	public void findAllUsersTest() {

		List<User> allUsers = userDAO.findAll();
		assertEquals(4, allUsers.size());
		assertNotEquals(0, allUsers.size());
	}

	@Test
	void isUsernameAvailable() throws Exception {
		assertTrue(userDAO.isUsernameAvailable("otilia"));
		assertFalse(userDAO.isUsernameAvailable("cosmin"));

		assertThrows(Exception.class, () -> userDAO.isUsernameAvailable(null));
	}

	@Test
	@Disabled
	public void deleteUserByIdTest() {

		assertEquals(1, userDAO.deleteById(2));
		assertEquals(0, userDAO.deleteById(6));
	}
}