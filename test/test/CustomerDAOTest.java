package test;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;

import model.Customer;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import service.CustomerDAO;
import service.DeleteDAO;

public class CustomerDAOTest {

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
	public void testAddCustomer() throws SQLException {
		Customer customer = new Customer("sidharth", "St. Marks Road, Bangalore", 22067130,
				"smittaldm7@gmail.com", "password"	);
		CustomerDAO customerDAO = new CustomerDAO(Database.getInstance().getConnection());
		customerDAO.addCustomer(customer);
		int customerID = customerDAO.getCustomerId("smittaldm7@gmail.com");
		Customer retrievedCustomer = customerDAO.getCustomer(customerID);
		assertEquals(customer, retrievedCustomer);
	}
	
	@Test
	public void testGetCustomer() throws SQLException {
		Customer customer = new Customer("sidharth", "St. Marks Road, Bangalore", 22067130,
				"smittaldm7@gmail.com", "password"	);
		CustomerDAO customerDAO = new CustomerDAO(Database.getInstance().getConnection());
		customerDAO.addCustomer(customer);
		int customerID = customerDAO.getCustomerId("smittaldm7@gmail.com");
		Customer retrievedCustomer = customerDAO.getCustomer(customerID);
		assertEquals(customer, retrievedCustomer);
	}

	@Test
	public void testGetCustomerIDFromEmail() throws SQLException {
		Customer customer = new Customer("sidharth", "St. Marks Road, Bangalore", 22067130,
				"smittaldm7@gmail.com", "password"	);
		CustomerDAO customerDAO = new CustomerDAO(Database.getInstance().getConnection());
		customerDAO.addCustomer(customer);
		int customerID = customerDAO.getCustomerId("smittaldm7@gmail.com");
		Customer retrievedCustomer = customerDAO.getCustomer(customerID);
		assertEquals("smittaldm7@gmail.com", retrievedCustomer.getEmail());
	}
	
	@Test
	public void testGetCustomerIDFromEmailAndPassword() throws SQLException {
		Customer customer = new Customer("sidharth", "St. Marks Road, Bangalore", 22067130,
				"smittaldm7@gmail.com", "password"	);
		CustomerDAO customerDAO = new CustomerDAO(Database.getInstance().getConnection());
		customerDAO.addCustomer(customer);
		int customerID = customerDAO.getCustomerId("smittaldm7@gmail.com","password");
		Customer retrievedCustomer = customerDAO.getCustomer(customerID);
		assertEquals("smittaldm7@gmail.com", retrievedCustomer.getEmail());
	}
	
}
