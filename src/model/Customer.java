package model;
import java.util.ArrayList;
import java.util.List;


public class Customer {

	String name;
	String address;
	long phoneNumber;
	String email;
	String password;
	float totalCartAmount;
	List<Order> orders = new ArrayList<Order>();
	
	public Customer(String name, String address, long phoneNumber,
			String email, String password) {
		super();
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
		this.totalCartAmount = 0;
	}
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	public long getPhoneNumber() {
		return phoneNumber;
	}
	public String getEmail() {
		return email;
	}
	public String getPassword() {
		return password;
	}
	public float getTotalCartAmount() {
		return totalCartAmount;
	}

	//delete()
	//edit()

	//void placeOrder() defined in ECommerceMarket class
	{
		
	}
	//
	

	
	
	String viewOrders()
	{
		return orders.toString();
	}
	
	
	
	@Override
	public String toString() {
		return "Customer [name=" + name + ", address=" + address
				+ ", phoneNumber=" + phoneNumber + ", email=" + email
				+ ", password=" + password + ", totalCartAmount=" + totalCartAmount + ", orders="
				+ orders + "]";
	}
	

	public String view() {
		return "Customer [name=" + name + ", address=" + address
				+ ", phoneNumber=" + phoneNumber + ", email=" + email
				+ ", password=" + password + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((address == null) ? 0 : address.hashCode());
		result = prime * result + ((email == null) ? 0 : email.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((orders == null) ? 0 : orders.hashCode());
		result = prime * result
				+ ((password == null) ? 0 : password.hashCode());
		result = prime * result + (int) (phoneNumber ^ (phoneNumber >>> 32));
		result = prime * result + Float.floatToIntBits(totalCartAmount);
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
		Customer other = (Customer) obj;
		if (address == null) {
			if (other.address != null)
				return false;
		} else if (!address.equals(other.address))
			return false;
		if (email == null) {
			if (other.email != null)
				return false;
		} else if (!email.equals(other.email))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (orders == null) {
			if (other.orders != null)
				return false;
		} else if (!orders.equals(other.orders))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (phoneNumber != other.phoneNumber)
			return false;
		if (Float.floatToIntBits(totalCartAmount) != Float
				.floatToIntBits(other.totalCartAmount))
			return false;
		return true;
	}
	
	

}
