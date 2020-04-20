package school.tss.shop.persistence.entity;

public class Item {

	private long id;
	private long categoryId;
	private String name;
	private double price;
	private double currentDiscount;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(long categoryId) {
		this.categoryId = categoryId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public double getCurrentDiscount() {
		return currentDiscount;
	}

	public void setCurrentDiscount(double currentDiscount) {
		this.currentDiscount = currentDiscount;
	}

	public double getDiscountedPrice() {
		if (currentDiscount == 0)
			return price;
		if (currentDiscount < 0 || currentDiscount > 100)
			return price;
		return price - price * (currentDiscount / 100);
	}
}
