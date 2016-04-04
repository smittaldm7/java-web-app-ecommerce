package model;

public class SupplierProduct {

	String supplierName;
	String productName;
	float price;
	float discountPercent;
	int stock;
	
	public SupplierProduct(String supplierName, String productName, 
				int stock, float price, float discountPercent) 
	{
		super();
		this.supplierName = supplierName;
		this.productName = productName;
		this.stock = stock;
		this.price = price;
		this.discountPercent = discountPercent;
	
	}
	
	public String getSupplierName() {
		return supplierName;
	}

	public String getProductName() {
		return productName;
	}
	
	public int getStock() {
		return stock;
	}


	public float getPrice() {
		return price;
	}


	public float getDiscountPercent() {
		return discountPercent;
	}



	float priceAfterDiscount()
	{
		return (price * ((100f-discountPercent)/100f));
	}
	
	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Float.floatToIntBits(discountPercent);
		result = prime * result + Float.floatToIntBits(price);
		result = prime * result
				+ ((productName == null) ? 0 : productName.hashCode());
		result = prime * result + stock;
		result = prime * result
				+ ((supplierName == null) ? 0 : supplierName.hashCode());
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
		SupplierProduct other = (SupplierProduct) obj;
		if (Float.floatToIntBits(discountPercent) != Float
				.floatToIntBits(other.discountPercent))
			return false;
		if (Float.floatToIntBits(price) != Float.floatToIntBits(other.price))
			return false;
		if (productName == null) {
			if (other.productName != null)
				return false;
		} else if (!productName.equals(other.productName))
			return false;
		if (stock != other.stock)
			return false;
		if (supplierName == null) {
			if (other.supplierName != null)
				return false;
		} else if (!supplierName.equals(other.supplierName))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "SupplierProduct [supplierName=" + supplierName
				+ ", productName=" + productName + ", stock=" + stock
				+ ", price=" + price + ", discountPercent=" + discountPercent
				+ "]";
	}
}
