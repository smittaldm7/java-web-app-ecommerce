package service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import model.Product;
import model.ProductKeyword;



public class ProductKeywordDAO {

Connection conn;
	
	public ProductKeywordDAO(Connection conn) {
		this.conn=conn;
	}
	
	public int addProductKeyword(ProductKeyword productKeyword) throws SQLException 
	{
	//Connection conn = Database.getInstance().getConnection();

	PreparedStatement p = conn
			.prepareStatement("insert into ProductKeywordRelationship ( Product_Id,"
					+ "Keyword_Id) values (?,?)");

	p.setInt(1, productKeyword.getProductID());
	p.setInt(2, productKeyword.getKeywordID());
	
	int status = p.executeUpdate();

	p.close();
	
	return status;
	
	}
	//only for unit test
	public ProductKeyword getProductKeyword(int productID, int keywordID) throws SQLException {
		
		PreparedStatement p = conn
				.prepareStatement("select * from ProductKeywordRelationship where Product_Id "
						+ "= ? and Keyword_Id = ?");

		p.setInt(1, productID);
		p.setInt(2, keywordID);
		
		ResultSet rs = p.executeQuery();

		rs.next();
		
		ProductKeyword productKeyword = new ProductKeyword(rs.getInt(2),rs.getInt(3));
		
		p.close();
		
		return productKeyword;

	}

	public List<Product> getProductFromSearchString(String searchString) throws SQLException {
		//Connection conn = Database.getInstance().getConnection();
		
		List<Product> favourableProducts = new ArrayList<Product>();
		
		CallableStatement cstmt = conn.prepareCall("{call getProductsFromSearchString(?)}");

		cstmt.setString(1, searchString);
				
		cstmt.execute();
		
		ResultSet rs = cstmt.getResultSet();
		
		while(rs.next())
		{
			Product p = new Product(rs.getString("name"));
			favourableProducts.add(p);
		}
		
		cstmt.close();
		
		return favourableProducts;
		
		
	}

	

}
