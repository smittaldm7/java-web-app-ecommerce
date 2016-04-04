package service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Product;
import model.SupplierProduct;

public class ProductDAO {
	
	Connection conn;
	
	public ProductDAO(Connection conn) {
		this.conn=conn;
	}

	public int addProduct(Product product) throws SQLException {

		//Connection conn = Database.getInstance().getConnection();

		PreparedStatement p = conn
				.prepareStatement("insert into product (name) values (?)");

		p.setString(1, product.getName());
		
		int status = p.executeUpdate();

		p.close();
		
		return status;
	}

	public int getProductIDFromName(String name) throws SQLException {
		//Connection conn = Database.getInstance().getConnection();
		
		PreparedStatement p = conn
				.prepareStatement("select * from product where name = ?");
		
		p.setString(1,name);
		
		ResultSet rs = p.executeQuery();
		
		rs.next();
		
		int id = rs.getInt("Id");
		
		p.close();
		
		return id;
	}

	public List<SupplierProduct> getProductSuppliers(String productName) throws SQLException {

		
		List<SupplierProduct> productSuppliersList = new ArrayList<>();
		
		//Connection conn = Database.getInstance().getConnection();
		
		CallableStatement c = conn
				.prepareCall("{call getProductSuppliers(?)}");
		
		c.setString(1,productName);
		
		c.execute();
		
		ResultSet rs = c.getResultSet();
				
		while(rs.next())
		{
			SupplierProduct sp = new SupplierProduct(productName, rs.getString(1),
					rs.getInt(2),	rs.getFloat(3), rs.getFloat(4));
			productSuppliersList.add(sp);
		}
		
		c.close();
		
		return productSuppliersList;
	}

	public List<Product> getProducts() throws SQLException {
		List<Product> products = new ArrayList<>();
		
		//Connection conn = Database.getInstance().getConnection();
		
		PreparedStatement pstmt = 
				conn.prepareStatement("select name from product");
		
		ResultSet rs =pstmt.executeQuery();
		
		
		while(rs.next())
		{
			Product product = new Product(rs.getString(1));
			products.add(product);
			
		}
		
		return products;
	}
	




}
