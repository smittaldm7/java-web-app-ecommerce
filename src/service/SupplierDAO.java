package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Supplier;

public class SupplierDAO {
	
	Connection conn;
	
	public SupplierDAO(Connection conn) {
		this.conn=conn;
	}

	public int addSupplier(Supplier supplier) throws SQLException {

		//Connection conn = Database.getInstance().getConnection();

		PreparedStatement p = conn
				.prepareStatement("insert into supplier (name,warehouseAddress) values (?,?)");

		p.setString(1, supplier.getName());
		p.setString(2,supplier.getWarehouseAddress());
		
		int status = p.executeUpdate();

		p.close();
		
		return status;
	}

	public int getSupplierIDFromName(String name) throws SQLException 
	{
		//Connection conn = Database.getInstance().getConnection();
		
		PreparedStatement p = conn
				.prepareStatement("select * from supplier where name = ?");
		
		p.setString(1,name);
		
		
		ResultSet rs = p.executeQuery();
		
		rs.next();
		
		int id = rs.getInt("Id");
		
		p.close();
			
			return id;
	}

}
