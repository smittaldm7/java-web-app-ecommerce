package service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import model.Order;

public class OrderDAO {
	
//official DAO for order and order item
	Connection conn;
	
	public OrderDAO(Connection conn) {
		this.conn=conn;
	}
	
	public Map<String, Boolean> addOrder(int currentCustomerID) throws SQLException {
		//Connection conn = Database.getInstance().getConnection();
		
		CallableStatement cstmt = 
				conn.prepareCall
				("{call addorder(?,?,?)} ");
		
		cstmt.setInt(1, currentCustomerID);
		cstmt.registerOutParameter(2, java.sql.Types.BOOLEAN);;
		cstmt.registerOutParameter(2, java.sql.Types.BOOLEAN);;
		cstmt.execute();
		
		boolean cartEmpty = cstmt.getBoolean(2);
		boolean insufficientStock = cstmt.getBoolean(3);
		
		cstmt.close();
		//return a map to return multiple values from this method
		Map<String, Boolean> map= new HashMap<>();
		map.put("cartEmpty",cartEmpty);
		map.put("insufficientStock", insufficientStock);
		return map;
	}

	public List<Order> getOrders(int customerID) throws SQLException {
		List<Order> orders = new ArrayList<>();
		
		//Connection conn = Database.getInstance().getConnection();
		
		PreparedStatement ps = conn.prepareStatement
				("select id, date, totalamount from ordertable where customer_id = ? order by date desc;");
		
		ps.setInt(1, customerID);
		
		ResultSet rs = ps.executeQuery();
		
		while (rs.next())
		{
			Order order = new Order(rs.getInt(1), rs.getTimestamp(2), rs.getFloat(3));
			orders.add(order);
		}
		return orders;
		
	}
	
	

}
