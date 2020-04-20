package school.tss.shop.persistence.dao;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import school.tss.shop.BaseTest;
import school.tss.shop.persistence.entity.User;

import static org.junit.jupiter.api.Assertions.*;

class UserDAOTest extends BaseTest {

	@Autowired
	private UserDAO userDAO;

	@Test
	void create() {
		User user = new User();
		user.setUsername("usertry1");
		user.setPassword("jfdoisajfidosa");
		user.setRole(User.Role.MANAGEMENT);
		userDAO.create(user);
	}

	@Test
	void getByUsername() {
	}

	@Test
	void isUsernameAvailable() throws Exception {
		assertTrue(userDAO.isUsernameAvailable("kkk"));
		assertFalse(userDAO.isUsernameAvailable("cosmin"));

		assertThrows(Exception.class, () -> userDAO.isUsernameAvailable(null));
	}
}