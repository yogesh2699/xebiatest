package problem1;

public class ProductInfo {

	private int quantity;
	private String name;
	private float price;
	private ItemType itemType;

	public ProductInfo(int quantity, String name, float price, ItemType itemType) {
		this.quantity = quantity;
		this.name = name;
		this.price = price;
		this.itemType = itemType;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public ItemType getItemType() {
		return itemType;
	}

	public void setItemType(ItemType itemType) {
		this.itemType = itemType;
	}

	public boolean isSalesTaxable() {
		return !this.itemType.isExempted();
	}

	public boolean isImportedTaxable() {
		return this.itemType.isImported();
	}

	@Override
	public String toString() {
		return "ProductInfo [quantity=" + quantity + ", name=" + name + ", price=" + price + ", itemType=" + itemType
				+ "]";
	}
	
}
