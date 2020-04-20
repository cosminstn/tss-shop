package school.tss.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.tss.shop.exceptions.auth.AuthenticationException;
import school.tss.shop.exceptions.auth.BadCredentialsException;
import school.tss.shop.exceptions.auth.InvalidCredentialsException;
import school.tss.shop.exceptions.auth.register.*;
import school.tss.shop.persistence.dao.UserDAO;
import school.tss.shop.persistence.entity.User;

import java.util.concurrent.atomic.AtomicReference;

@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;

	private final AtomicReference<User> loggedUser = new AtomicReference<>();

	public User login(String username, String password) throws InvalidCredentialsException, BadCredentialsException {
		if (username == null || password == null || username.trim().equals("") || password.trim().equals("")) {
			throw new InvalidCredentialsException();
		}
		User user = userDAO.getByUsername(username);

		if (user == null) {
			throw new BadCredentialsException();
		}

		if (!user.getPassword().equals(password)) {
			throw new BadCredentialsException();
		}
		loggedUser.set(user);
		return user;
	}

	public void logout() throws AuthenticationException {
		if (loggedUser.get() == null) {
			throw new AuthenticationException("UNAUTHENTICATED!");
		}
		loggedUser.set(null);
	}

	public User registerCustomer(String username, String password, String passwordConfirmation) throws UserRegistrationException {
		if (username == null || username.length() < 4) {
			throw new InvalidUsernameException();
		}
		if (password == null || password.length() < 8) {
			throw new InvalidPasswordException();
		}
		if (!password.equals(passwordConfirmation)) {
			throw new InvalidPasswordConfirmationException();
		}
		boolean taken = false;
		try {
			if (!userDAO.isUsernameAvailable(username)) {
				taken = true;
			}
		} catch (Exception ex) {
			throw new InvalidUsernameException(ex);
		}
		// check if username is taken
		if (taken) {
			throw new UsernameTakenException();
		}

		User user = new User();
		user.setUsername(username);
		user.setPassword(password);
		user.setRole(User.Role.CUSTOMER);

		return userDAO.create(user);
	}

}
