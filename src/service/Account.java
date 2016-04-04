package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Account {
	
	private Connection conn;
	
	public Account(Connection conn)
	{
		this.conn = conn;
	}
	public Boolean login(String email,String password) throws SQLException
	{
		int count=0; 
		
		String sql = "select count(*) from users2 where email=? and password=?";
		
	
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setString(1, email);
		ps.setString(2, password);
		
		ResultSet rs = ps.executeQuery();
	
		if(rs.next())
		{
			count=rs.getInt(1);
		}
		rs.close();
		if (count==0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	
	public void create(String email,String password) throws SQLException
	{
			
		String sql = "insert into users2 values( ?,?)";
		
	
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setString(1, email);
		ps.setString(2, password);
		
		ps.executeUpdate();
		
	}
	public boolean exists(String email, String password) throws SQLException{
		int count=0; 
		
		String sql = "select count(*) from users2 where email=?";
		
	
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setString(1, email);
		
		
		ResultSet rs = ps.executeQuery();
	
		if(rs.next())
		{
			count=rs.getInt(1);
		}
		rs.close();
		if (count==0)
		{
			return false;
		}
		else
		{
			return true;
		}
	}

}
