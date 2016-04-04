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



	

}
