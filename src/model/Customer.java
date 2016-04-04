package model;
import java.util.ArrayList;
import java.util.List;


public class Customer {

	String name;
	String address;
	long phoneNumber;
	String email;
	String password;
	float amount;
	List<Order> orders = new ArrayList<Order>();
	
	public Customer(String name, String address, long phoneNumber,
			String email, String password) {
		super();
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.password = password;
		this.amount = 0;
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
	public float getAmount() {
		return amount;
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
				+ ", password=" + password + ", amount=" + amount + ", orders="
				+ orders + "]";
	}
	

	public String view() {
		return "Customer [name=" + name + ", address=" + address
				+ ", phoneNumber=" + phoneNumber + ", email=" + email
				+ ", password=" + password + "]";
	}
	
	

}
