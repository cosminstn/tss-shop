package school.tss.shop.service;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import school.tss.shop.BaseTest;
import school.tss.shop.exceptions.cart.InvalidProductIdException;

import java.time.Duration;
import java.time.Instant;

import static org.junit.jupiter.api.Assertions.*;

class CartServiceTest extends BaseTest {

	private static final Logger LOG = LoggerFactory.getLogger(CartServiceTest.class);

	@Autowired
	private CartService cartService;

	private Instant testStart, testEnd;

	@BeforeEach
	void beforeEach() {
		testStart = Instant.now();
	}

	@Tag("cart")
	@DisplayName("Tests if addProduct works as expected for existing items.")
	@Test
	void addProduct() throws Exception {
		cartService.addProduct(1, 1);
	}

	@DisplayName("Tests if addProduct fails for invalid item id, throwing InvalidProductIdException.")
	@Test
	void whenInvalidProductId_thenFailAddProduct() {
		assertThrows(InvalidProductIdException.class, () -> cartService.addProduct(19999, 1));
	}

	@Tag("cart")
	@Test
	void getCartItems() {
	}

	@Tag("cart")
	@Test
	void getCartTotalValue() throws Exception {
		cartService.addProduct(1 ,1);
		assertTrue(cartService.getCartTotalValue() > 0);
	}

	@Tag("cart")
	@Test
	void clearCart() throws Exception {
		cartService.addProduct(1, 2);
		cartService.clearCart();
		assertEquals(0, cartService.getCartTotalValue());
	}

	@AfterEach
	void afterEach() {
		testEnd = Instant.now();
		LOG.info("Test took " + Duration.between(testStart, testEnd).toMillis() + " millis to complete.");
	}
}