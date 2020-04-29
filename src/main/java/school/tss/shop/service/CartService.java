package school.tss.shop.service;

import javafx.util.Pair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import school.tss.shop.exceptions.cart.InvalidProductIdException;
import school.tss.shop.exceptions.cart.InvalidQtyException;
import school.tss.shop.exceptions.cart.QtyTooLargeException;
import school.tss.shop.models.Cart;
import school.tss.shop.persistence.dao.ItemDAO;
import school.tss.shop.persistence.entity.Item;

import java.util.ArrayList;
import java.util.List;
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
		Integer existingQty = cart.get().getProductIdQuantityMap().get(itemId);
		if (existingQty != null) {
			if (qty + existingQty > 100) {
				throw new QtyTooLargeException("Qty cannot be more than 100");
			}
		}
		cart.get().addProduct(itemId, qty);
	}

	/**
	 * Returns detailed cart info
	 */
	public List<Pair<Item, Integer>> getCartItems() {
		if (cart.get() == null) {
			return null;
		}
		List<Pair<Item, Integer>> cartInfo = new ArrayList<>();
		cart.get().getProductIdQuantityMap().forEach((itemId, qty) -> {
			cartInfo.add(new Pair<>(itemDAO.get(itemId), qty));
		});
		return cartInfo;
	}

	public double getCartTotalValue() {
		if (cart.get() == null) {
			return 0;
		}
		AtomicReference<Double> value = new AtomicReference<>((double) 0);
		cart.get().getProductIdQuantityMap().forEach((itemId, qty) -> {
			Item item = itemDAO.get(itemId);
			value.updateAndGet(v -> v + qty * item.getDiscountedPrice());
		});
		return value.get();
	}

	public void clearCart() {
		cart.set(null);
	}
}
