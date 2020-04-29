package school.tss.shop.shell;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import school.tss.shop.exceptions.cart.InvalidProductIdException;
import school.tss.shop.exceptions.cart.InvalidQtyException;
import school.tss.shop.persistence.dao.ItemDAO;
import school.tss.shop.persistence.entity.Item;
import school.tss.shop.service.CartService;

import java.util.List;

@ShellComponent
public class ShoppingCommands {

	@Autowired
	private CartService cartService;

	@Autowired
	private ItemDAO itemDAO;

	@ShellMethod("Registers a new customer. ")
	public String listItems() {
		try {
			List<Item> items = itemDAO.findAll();
			StringBuilder output = new StringBuilder();
			for (Item item : items) {
				output.append(String.format("%d %s --> %f USD\n", item.getId(), item.getName(), item.getPrice()));
			}
			return output.toString();
		} catch (Exception ex) {
			return "Error connecting to the database!";
		}
	}

	@ShellMethod("Adds an item to the cart.")
	public String addToCart(long itemId, Integer qty) {
		try {
			if (qty == null) {
				qty = 1;
			}
			cartService.addProduct(itemId, qty);
			return "Added!";
		} catch (InvalidQtyException ex) {
			return "The qty must be between 1 and 100!";
		} catch (InvalidProductIdException ex) {
			return "No product found with this id!";
		} catch (Exception ex) {
			return "Unknown exception: " + ex.getMessage();
		}
	}

	@ShellMethod("Show detailed info about the cart.")
	public String cartInfo() {
		if (cartService.getCartItems() == null) {
			return "The cart is empty!";
		}
		StringBuilder output = new StringBuilder("Cart info: \n");
		cartService.getCartItems().forEach(pair -> {
			output.append(String.format("%d x %s\n", pair.getSecond(), pair.getFirst().getName()));
		});
		output.append(String.format("Total value: %f", cartService.getCartTotalValue()));
		return output.toString();
	}

}
