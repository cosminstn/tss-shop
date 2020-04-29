package school.tss.shop.service;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import school.tss.shop.BaseTest;
import school.tss.shop.exceptions.auth.AuthenticationException;
import school.tss.shop.exceptions.auth.BadCredentialsException;
import school.tss.shop.exceptions.auth.InvalidCredentialsException;
import school.tss.shop.exceptions.auth.register.InvalidPasswordConfirmationException;
import school.tss.shop.exceptions.auth.register.InvalidPasswordException;
import school.tss.shop.exceptions.auth.register.InvalidUsernameException;
import school.tss.shop.exceptions.auth.register.UsernameTakenException;
import school.tss.shop.exceptions.cart.InvalidProductIdException;

import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class UserServiceTest extends BaseTest{

    private static final Logger LOG = LoggerFactory.getLogger(UserServiceTest.class);

    @Autowired
    private UserService userService;

    private Instant testStart, testEnd;

    @BeforeEach
    void beforeEach() {
        testStart = Instant.now();
    }

    @Tag("login")
    @DisplayName("Tests if login works as expected for existing users.")
    @Test
    void login()throws Exception {
        userService.login("andreea", "abcd1234");
    }

    @Tag("login")
    @DisplayName("Tests if login fails for null username and password, throwing InvalidCredentialsException")
    @Test
    void whenNullUsernamePasswordEntry() {
        assertThrows(InvalidCredentialsException.class, () -> userService.login("", ""));
    }

    @Tag("login")
    @DisplayName("Tests if login fails for invalid password, throwing BadCredentialsException")
    @Test
    void whenWrongPassword() {
        assertThrows(BadCredentialsException.class, () -> userService.login("andreea", "parola"));
    }

    @Tag("register")
    @DisplayName("Tests if register works as expected for new customer")
    @Test
    void registerCustomer()throws Exception {
        userService.registerCustomer("newCustomer", "parola1234", "parola1234");
    }

    @Tag("register")
    @DisplayName("Tests if register fails for short username, throwing InvalidUsernameException")
    @Test
    void whenGivenShortUsername() {
        assertThrows(InvalidUsernameException.class, () -> userService.registerCustomer("abc", "parola1234", "parola1234"));
    }

    @Tag("register")
    @DisplayName("Tests if register fails for short password, throwing InvalidPasswordException")
    @Test
    void whenGivenShortPassword() {
        assertThrows(InvalidPasswordException.class, () -> userService.registerCustomer("abcdefg", "parola", "parola"));
    }

    @Tag("register")
    @DisplayName("Tests if register fails for password != passwordConfirmation, throwing InvalidPasswordConfirmationException")
    @Test
    void whenGivenWrongPasswordConfirmation() {
        assertThrows(InvalidPasswordConfirmationException.class, () -> userService.registerCustomer("abcdefg", "parola1234", "parola1234567"));
    }

    @Tag("register")
    @DisplayName("Tests if register fails for already taken username, throwing InvalidUsernameException")
    @Test
    void whenGivenTakenUsername() {
        assertThrows(UsernameTakenException.class, () -> userService.registerCustomer("andreea", "abcd1234", "abcd1234"));
    }

    //test with Disabled because we already have a test that does the same thing
    @Tag("register")
    @Disabled
    @DisplayName("Tests if register fails for already taken username, throwing InvalidUsernameException")
    @Test
    void whenGivenTakenUsername2() {
        assertThrows(UsernameTakenException.class, () -> userService.registerCustomer("andreea", "abcd1234", "abcd1234"));
    }

    @AfterEach
    void afterEach() {
        testEnd = Instant.now();
        LOG.info("Test took " + Duration.between(testStart, testEnd).toMillis() + " millis to complete.");
    }

}