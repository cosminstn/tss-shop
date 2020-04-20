package school.tss.shop.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import school.tss.shop.exceptions.auth.AuthenticationException;
import school.tss.shop.exceptions.auth.BadCredentialsException;
import school.tss.shop.exceptions.auth.InvalidCredentialsException;
import school.tss.shop.exceptions.auth.register.InvalidUsernameException;
import school.tss.shop.exceptions.auth.register.UserRegistrationException;
import school.tss.shop.persistence.entity.User;
import school.tss.shop.service.UserService;

@ShellComponent
public class UserCommands {

	@Autowired
	private UserService userService;

	@ShellMethod("Registers a new customer. ")
	public String register(String username, String password, String passwordConfirmation) {
		try {
			User registeredUser = userService.registerCustomer(username, password, passwordConfirmation);
			return "OK! User id: " + registeredUser.getId();
		} catch (InvalidUsernameException ex) {
			return "This username is invalid! Username required min. 4 characters.";
		} catch (UserRegistrationException ex) {
			return String.format("The customer was not registered! Exception: %s", ex.getMessage());
		}
	}

	@ShellMethod("Login.")
	public String login(String username, String password) {
		try {
			User user = userService.login(username, password);
			return "OK! Welcome back " + user.getUsername() + "!";
		} catch (InvalidCredentialsException ex) {
			return "Could not login! Username and password required!";
		} catch (BadCredentialsException ex) {
			return "No user found for these credentials!";
		}
	}

	@ShellMethod("Logout.")
	public String logout() {
		try {
			userService.logout();
			return "Bye bye!";
		} catch (AuthenticationException ex) {
			return "You are not authenticated!";
		}
	}
}

