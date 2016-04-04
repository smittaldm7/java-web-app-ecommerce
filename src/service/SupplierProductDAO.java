package service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


import model.SupplierProduct;

public class SupplierProductDAO {
	
	Connection conn;

	public SupplierProductDAO(Connection conn) {
		this.conn=conn;
	}

	public void addSupplierProduct(SupplierProduct supplierProduct) throws SQLException {

		//Connection conn = Database.getInstance().getConnection();

		CallableStatement c = conn
				.prepareCall("{call addSupplierProduct(?,?,?,?,?)}");

		c.setString(1, supplierProduct.getSupplierName());
		c.setString(2, supplierProduct.getProductName());
		c.setFloat(3, supplierProduct.getPrice());
		c.setFloat(4, supplierProduct.getDiscountPercent());
		c.setInt(5,supplierProduct.getStock());
		
		c.execute();

		c.close();
		
	}
	
	public List<SupplierProduct> getSuppliersForProduct(String productName) throws SQLException
	{
		List<SupplierProduct> supplierProductList = new ArrayList<>();
		
		CallableStatement c = conn
				.prepareCall("{call getSuppliersForProduct(?)}");
		
		c.setString(1, productName);
		
		c.execute();

		ResultSet rs = c.getResultSet();
		
		while(rs.next())
		{
			SupplierProduct supplierProduct = new SupplierProduct(rs.getString(1), productName, rs.getInt(4), rs.getFloat(2), rs.getFloat(3));
			supplierProductList.add(supplierProduct);
		}
		
		c.close();
		
		return supplierProductList;
		
	}
}
