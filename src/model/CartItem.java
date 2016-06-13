package model;

public class CartItem {
	String productName;
	String supplierName;
	int quantity;
	float itemPriceAfterDiscount;
	float totalPriceAfterDiscount;
	
	public String getProductName() {
		return productName;
	}



	public String getSupplierName() {
		return supplierName;
	}



	public int getQuantity() {
		return quantity;
	}



	public float getItemPriceAfterDiscount() {
		return itemPriceAfterDiscount;
	}



	public float getTotalPriceAfterDiscount() {
		return totalPriceAfterDiscount;
	}



	@Override
	public String toString() {
		return "CartItem [productName=" + productName + ", supplierName="
				+ supplierName + ", quantity=" + quantity
				+ ", itemPriceAfterDiscount=" + itemPriceAfterDiscount
				+ ", totalPriceAfterDiscount=" + totalPriceAfterDiscount + "]";
	}



	public CartItem(String productName, String supplierName, int quantity,
			float itemPriceAfterDiscount, float totalPriceAfterDiscount) {
		super();
		this.productName = productName;
		this.supplierName = supplierName;
		this.quantity = quantity;
		this.itemPriceAfterDiscount = itemPriceAfterDiscount;
		this.totalPriceAfterDiscount = totalPriceAfterDiscount;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(itemPriceAfterDiscount);
		result = prime * result
				+ ((productName == null) ? 0 : productName.hashCode());
		result = prime * result + quantity;
		result = prime * result
				+ ((supplierName == null) ? 0 : supplierName.hashCode());
		result = prime * result + Float.floatToIntBits(totalPriceAfterDiscount);
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CartItem other = (CartItem) obj;
		if (Float.floatToIntBits(itemPriceAfterDiscount) != Float
				.floatToIntBits(other.itemPriceAfterDiscount))
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (quantity != other.quantity)
			return false;
		if (supplierName == null) {
			if (other.supplierName != null)
				return false;
		} else if (!supplierName.equals(other.supplierName))
			return false;
		if (Float.floatToIntBits(totalPriceAfterDiscount) != Float
				.floatToIntBits(other.totalPriceAfterDiscount))
			return false;
		return true;
	}



	

}
