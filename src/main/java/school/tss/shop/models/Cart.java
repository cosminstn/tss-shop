package school.tss.shop.models;

import java.util.HashMap;
import java.util.Map;

public class Cart {

	private final Map<Long, Integer> productIdQuantityMap;

	public Cart() {
		this.productIdQuantityMap = new HashMap<>();
	}

	public void addProduct(Long productId, Integer quantity) {
		productIdQuantityMap.put(productId, quantity);
	}

	public Map<Long, Integer> getProductIdQuantityMap() {
		return productIdQuantityMap;
	}
}
