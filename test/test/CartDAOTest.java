package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.sql.SQLException;
import java.util.List;

import model.CartItem;
import model.Customer;
import model.Product;
import model.Supplier;
import model.SupplierProduct;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import service.CartDAO;
import service.CustomerDAO;
import service.DeleteDAO;
import service.ProductDAO;
import service.SupplierDAO;
import service.SupplierProductDAO;

public class CartDAOTest {

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
	public void testAddCartItem() throws SQLException {
		Customer customer = new Customer("sidharth", "St. Marks Road, Bangalore", 22067130,
				"smittaldm7@gmail.com", "password"	);
		CustomerDAO customerDAO = new CustomerDAO(Database.getInstance().getConnection());
		customerDAO.addCustomer(customer);
		int customerID = customerDAO.getCustomerId("smittaldm7@gmail.com");
		
		Product product = new Product("Haier Mini fridge");
		ProductDAO productDAO = new ProductDAO(Database.getInstance().getConnection());
		productDAO.addProduct(product);
		
		Supplier supplier1 = new Supplier("Guptas", "#43, Grand Central Park Road, New York");
		SupplierDAO supplierDAO = new SupplierDAO(Database.getInstance().getConnection());
		supplierDAO.addSupplier(supplier1);
		
		SupplierProductDAO supplierProductDAO = new SupplierProductDAO(Database.getInstance().getConnection());
		SupplierProduct sp1 = new SupplierProduct("Guptas", "Haier Mini fridge", 5, 3000, 0);
		supplierProductDAO.addSupplierProduct(sp1);
		
		CartDAO cartDAO = new CartDAO(Database.getInstance().getConnection());
		
		cartDAO.addCartItem(customerID, "Haier Mini fridge", "Guptas", 3);
		
		List<CartItem> cartItems = cartDAO.getCartItems(customerID);
		
		assertEquals(cartItems.size(),1);
		assertEquals(cartItems.get(0),new CartItem("Haier Mini fridge","Guptas", 3, 3000 ,9000));
		
		
	}

	@Test
	public void testGetCartItems() throws SQLException {
		Customer customer = new Customer("sidharth", "St. Marks Road, Bangalore", 22067130,
				"smittaldm7@gmail.com", "password"	);
		CustomerDAO customerDAO = new CustomerDAO(Database.getInstance().getConnection());
		customerDAO.addCustomer(customer);
		int customerID = customerDAO.getCustomerId("smittaldm7@gmail.com");
		
		Product product = new Product("Haier Mini fridge");
		ProductDAO productDAO = new ProductDAO(Database.getInstance().getConnection());
		productDAO.addProduct(product);
		
		Product product2 = new Product("Front Water Bottle");
		productDAO.addProduct(product2);
		
		
		
		Supplier supplier1 = new Supplier("Guptas", "#43, Grand Central Park Road, New York");
		SupplierDAO supplierDAO = new SupplierDAO(Database.getInstance().getConnection());
		supplierDAO.addSupplier(supplier1);
		
		SupplierProductDAO supplierProductDAO = new SupplierProductDAO(Database.getInstance().getConnection());
		SupplierProduct sp1 = new SupplierProduct("Guptas", "Haier Mini fridge", 5, 3000, 0);
		supplierProductDAO.addSupplierProduct(sp1);
		
		SupplierProduct sp2 = new SupplierProduct("Guptas", "Front Water Bottle", 5, 250, 10);
		supplierProductDAO.addSupplierProduct(sp2);
		
		CartDAO cartDAO = new CartDAO(Database.getInstance().getConnection());
		
		cartDAO.addCartItem(customerID, "Haier Mini fridge", "Guptas", 3);
		cartDAO.addCartItem(customerID, "Front Water Bottle", "Guptas", 2);
		
		
		
		List<CartItem> cartItems = cartDAO.getCartItems(customerID);
		
		assertEquals(cartItems.size(),2);
		assertTrue(cartItems.contains(new CartItem("Haier Mini fridge","Guptas", 3, 3000 ,9000)));
		assertTrue(cartItems.contains(new CartItem("Front Water Bottle","Guptas", 2, 225, 450)));
		
	}
	
	@Test
	public void testClearCart() throws SQLException {
		Customer customer = new Customer("sidharth", "St. Marks Road, Bangalore", 22067130,
				"smittaldm7@gmail.com", "password"	);
		CustomerDAO customerDAO = new CustomerDAO(Database.getInstance().getConnection());
		customerDAO.addCustomer(customer);
		int customerID = customerDAO.getCustomerId("smittaldm7@gmail.com");
		
		Product product = new Product("Haier Mini fridge");
		ProductDAO productDAO = new ProductDAO(Database.getInstance().getConnection());
		productDAO.addProduct(product);
		
		Product product2 = new Product("Front Water Bottle");
		productDAO.addProduct(product2);
		
		
		
		Supplier supplier1 = new Supplier("Guptas", "#43, Grand Central Park Road, New York");
		SupplierDAO supplierDAO = new SupplierDAO(Database.getInstance().getConnection());
		supplierDAO.addSupplier(supplier1);
		
		SupplierProductDAO supplierProductDAO = new SupplierProductDAO(Database.getInstance().getConnection());
		SupplierProduct sp1 = new SupplierProduct("Guptas", "Haier Mini fridge", 5, 3000, 0);
		supplierProductDAO.addSupplierProduct(sp1);
		
		SupplierProduct sp2 = new SupplierProduct("Guptas", "Front Water Bottle", 5, 250, 10);
		supplierProductDAO.addSupplierProduct(sp2);
		
		CartDAO cartDAO = new CartDAO(Database.getInstance().getConnection());
		
		cartDAO.addCartItem(customerID, "Haier Mini fridge", "Guptas", 3);
		cartDAO.addCartItem(customerID, "Front Water Bottle", "Guptas", 2);
		
		
		cartDAO.clearCart(customerID);
		
		List<CartItem> cartItems = cartDAO.getCartItems(customerID);
		
		assertEquals(cartItems.size(),0);
		assertTrue(cartItems.isEmpty());
	}
}
