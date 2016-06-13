package service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.OrderItem;

public class OrderItemDAO {
	
Connection conn;
	
	public OrderItemDAO(Connection conn) {
		this.conn=conn;
	}
	
	public List<OrderItem> getOrderItems(int customerID, int orderID) throws SQLException
	{
		List<OrderItem> orderItemList = new ArrayList<>();

		//Connection conn = Database.getInstance().getConnection();
		
		CallableStatement cstmt = conn.prepareCall("{call getOrderItems(?,?)}");
		
		cstmt.setInt(1,customerID);
		cstmt.setInt(2,orderID);
		
		cstmt.execute();
		
		ResultSet rs = cstmt.getResultSet();
		
		while(rs.next())
			{
			OrderItem orderItem = new OrderItem(rs.getString(1), rs.getString(2),
							rs.getInt(3), rs.getTimestamp(4), rs.getFloat(5)
							,rs.getFloat(6));
			orderItemList.add(orderItem);
			}
			
		cstmt.close();
		
		return orderItemList;
	}
		
	

}
