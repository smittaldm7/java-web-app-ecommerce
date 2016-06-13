package model;

public class ProductKeyword {
	
	int productID;
	int keywordID;
	
	
	
	public ProductKeyword(int productID, int keywordID) {
		super();
		this.productID = productID;
		this.keywordID = keywordID;
	}
	public int getProductID() {
		return productID;
	}
	public int getKeywordID() {
		return keywordID;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + keywordID;
		result = prime * result + productID;
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
		ProductKeyword other = (ProductKeyword) obj;
		if (keywordID != other.keywordID)
			return false;
		if (productID != other.productID)
			return false;
		return true;
	}
	
	
	
	

}
