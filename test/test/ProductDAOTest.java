package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import model.Product;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import service.DeleteDAO;
import service.ProductDAO;

public class ProductDAOTest {

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		Database.getInstance().connect();
	}

	@AfterClass
	public static void tearDownAfterClass() throws Exception {
		Database.getInstance().disconnect();
	}

	@Before
	public void setUp() throws Exception {
		DeleteDAO deleteDAO = new DeleteDAO(Database.getInstance().getConnection());
		deleteDAO.deleteAllData();
	}

	@After
	public void tearDown() throws Exception {
		DeleteDAO deleteDAO = new DeleteDAO(Database.getInstance().getConnection());
		deleteDAO.deleteAllData();
	}

	@Test
	public void testAddProduct() throws SQLException {
		Product product = new Product("Haier Mini fridge");
		ProductDAO productDAO = new ProductDAO(Database.getInstance().getConnection());
		productDAO.addProduct(product);
		int id = productDAO.getProductIDFromName("Haier Mini fridge");
		assertTrue(id>0);
	}

	@Test
	public void testGetProductIDFromName() throws SQLException {
		Product product = new Product("Haier Mini fridge");
		ProductDAO productDAO = new ProductDAO(Database.getInstance().getConnection());
		productDAO.addProduct(product);
		int id = productDAO.getProductIDFromName("Haier Mini fridge");
		assertTrue(id>0);
	}
	
	@Test
	public void testGetProducts() throws SQLException
	{
		Product product1 = new Product("Haier Mini fridge");
		Product product2 = new Product("Bajaj Mixer Grinder");
		ProductDAO productDAO = new ProductDAO(Database.getInstance().getConnection());
		productDAO.addProduct(product1);
		productDAO.addProduct(product2);
		List<Product> products = productDAO.getProducts();
		
		assertEquals(products.size(),2);
		assertTrue(products.contains(product1));
		assertTrue(products.contains(product2));
		
	}
}
