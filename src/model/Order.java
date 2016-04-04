package model;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;


public class Order {
	
	
	int id;	
	Timestamp date;
	float totalBillAmount=0;
	List<OrderItem> OrderItems = new ArrayList<OrderItem>();
	
	public Order(int id, Timestamp date, float totalBillAmount) {
		super();
		this.id = id;
		this.date = date;
		this.totalBillAmount = totalBillAmount;
	}
	
	public int getId() {
		return id;
	}

	public Timestamp getDate() {
		return date;
	}

	public float getTotalBillAmount() {
		return totalBillAmount;
	}

	public List<OrderItem> getOrderItems() {
		return OrderItems;
	}

	public void setOrderItems(List<OrderItem> orderItems) {
		OrderItems = orderItems;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", date=" + date + ", totalBillAmount="
				+ totalBillAmount + ", OrderItems=" + OrderItems + "]";
	}
	
	public String view() {
		return "Order [id=" + id + ", date=" + date + ", totalBillAmount="
				+ totalBillAmount  + "]";
	}

}
