package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Customer;

public class CustomerDAO {

	Connection conn;
	
	public CustomerDAO(Connection conn) {
		this.conn=conn;
	}

	public void addCustomer(Customer customer) throws SQLException{
		//Connection conn = Database.getInstance().getConnection();
		
		PreparedStatement pstmt = 
				conn.prepareStatement("Insert into Customer (name, address, phonenumber,"
						+ "email, password )values (?,?,?,?,?)");
		
		pstmt.setString(1, customer.getName());
		pstmt.setString(2, customer.getAddress());
		pstmt.setLong(3, customer.getPhoneNumber());
		pstmt.setString(4, customer.getEmail());
		pstmt.setString(5, customer.getPassword());
		
		pstmt.executeUpdate();
		
		pstmt.close();
	}

	public int getCustomerId(String email, String password) throws SQLException {
		
		//Connection conn = Database.getInstance().getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement
				("select * from Customer where email = ? and password = ?");
		
		pstmt.setString(1,email);
		pstmt.setString(2,password);
		
		
		ResultSet rs = pstmt.executeQuery();
		
		int id = 0;
		if(rs.next())
			id = rs.getInt("id");
			
		pstmt.close();
		
		return id;
	}

	public int getCustomerId(String email) throws SQLException 
	{
		
		//Connection conn = Database.getInstance().getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement
				("select * from Customer where email = ?");
		
		pstmt.setString(1,email);
		
		ResultSet rs = pstmt.executeQuery();
		
		int id = 0;
		if(rs.next())
			id = rs.getInt("id");
			
		pstmt.close();
		
		return id;
	}

	
	public Customer getCustomer(int customerID) throws SQLException 
	{
		//Connection conn = Database.getInstance().getConnection();
		
		PreparedStatement pstmt = conn.prepareStatement
				("select * from Customer where id = ?");
		
		pstmt.setInt(1,customerID);
		
		ResultSet rs = pstmt.executeQuery();
		
		rs.next();
			
		Customer customer = new Customer(rs.getString(2), rs.getString(3),
					rs.getLong(4), rs.getString(5), rs.getString(6));
					
		pstmt.close();
		
		return customer;
	}


	
	

}
