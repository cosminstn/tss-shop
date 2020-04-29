package school.tss.shop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.tss.shop.exceptions.cart.InvalidProductIdException;
import school.tss.shop.exceptions.cart.InvalidQtyException;
import school.tss.shop.exceptions.cart.QtyTooLargeException;
import school.tss.shop.models.Cart;
import school.tss.shop.persistence.dao.ItemDAO;
import school.tss.shop.persistence.entity.Item;

import java.util.concurrent.atomic.AtomicReference;

@Service
public class CartService {

	@Autowired
	private ItemDAO itemDAO;

	private final AtomicReference<Cart> cart = new AtomicReference<>();

	public void addProduct(long itemId, int qty) throws Exception {
		if (qty <= 0) {
			throw new InvalidQtyException("Qty must be positive");
		}
		if (!itemDAO.exists(itemId)) {
			throw new InvalidProductIdException();
		}
		Item item = itemDAO.get(itemId);
		if (qty > 100) {
			throw new QtyTooLargeException("Qty cannot be more than 100");
		}
		if (cart.get() == null) {
			cart.set(new Cart());
		}
		cart.get().addProduct(itemId, qty);

	}

	public void clearCart() {
		cart.set(null);
	}
}
