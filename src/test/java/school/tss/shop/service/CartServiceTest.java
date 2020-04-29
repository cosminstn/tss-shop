package school.tss.shop.service;

import org.junit.jupiter.api.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import school.tss.shop.BaseTest;
import school.tss.shop.exceptions.cart.InvalidProductIdException;
import school.tss.shop.exceptions.cart.InvalidQtyException;
import school.tss.shop.exceptions.cart.QtyTooLargeException;

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
	@Disabled
	void addProduct() throws Exception {
		cartService.addProduct(1, 1);
	}

	@DisplayName("Tests if addProduct fails for invalid item id, throwing InvalidProductIdException.")
	@Test
	@Disabled
	void whenInvalidProductId_thenFailAddProduct() {
		assertThrows(InvalidProductIdException.class, () -> cartService.addProduct(19999, 1));
	}

	@DisplayName("Tests if addProduct fails for qty greater then 100, throwing QtyTooLargeException.")
	@Test
	void whenQtyTooLarge_thenFailAddProduct(){
		assertThrows(QtyTooLargeException.class, () -> cartService.addProduct(1,120));
	}

	@DisplayName("Tests if addProduct fails for qty less then 100, throwing InvalidQtyException.")
	@Test
	void whenQtyTooSmall_thenFailAddProduct(){
		assertThrows(InvalidQtyException.class, () -> cartService.addProduct(1,-10));
	}

	@Tag("cart")
	@Test
	@Disabled
	void getCartItems() throws Exception {
			cartService.addProduct(1,1);
			assertEquals(1,cartService.getCartItems().get(0).getFirst().getId());


	}

	@Tag("cart")
	@Test
	@Disabled
	void getCartTotalValue() throws Exception {
		cartService.addProduct(1 ,1);
		assertTrue(cartService.getCartTotalValue() > 0);
	}

	@Test
	void getCartTotalValueWhenEmpty() throws Exception {

		assertEquals(0, cartService.getCartTotalValue());
	}

	@Tag("cart")
	@Test
	@Disabled
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