package test;

import static org.junit.Assert.*;

import java.sql.SQLException;

import model.Supplier;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import service.DeleteDAO;
import service.SupplierDAO;

public class SupplierDAOTest {

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
		//Database should be empty before test;
		DeleteDAO deleteDAO = new DeleteDAO(Database.getInstance().getConnection());
		deleteDAO.deleteAllData();
	}

	@After
	public void tearDown() throws Exception {
		//Database should be empty after test
		DeleteDAO deleteDAO = new DeleteDAO(Database.getInstance().getConnection());
		deleteDAO.deleteAllData();
	}

	@Test
	public void testAddSupplier() throws SQLException {
		Supplier supplier1 = new Supplier("Sidharth's", "#43, Grand Central Park Road, New York");
		Supplier supplier2 = new Supplier("Nalini's", "#120, Sunnyvale, California");
		SupplierDAO supplierDAO = new SupplierDAO(Database.getInstance().getConnection());
		supplierDAO.addSupplier(supplier1);
		supplierDAO.addSupplier(supplier2);
		
		Supplier retrievedSupplier1 = supplierDAO.getSupplierFromName("Sidharth's");
		Supplier retrievedSupplier2 = supplierDAO.getSupplierFromName("Nalini's");
		assertEquals(supplier1, retrievedSupplier1);
		assertEquals(supplier2, retrievedSupplier2);
	}
	
	@Test
	public void testGetSupplierIDFromName() throws SQLException {
		Supplier supplier1 = new Supplier("Sidharth's", "#43, Grand Central Park Road, New York");
		Supplier supplier2 = new Supplier("Nalini's", "#120, Sunnyvale, California");
		SupplierDAO supplierDAO = new SupplierDAO(Database.getInstance().getConnection());
		supplierDAO.addSupplier(supplier1);
		supplierDAO.addSupplier(supplier2);
		
		Supplier retrievedSupplier1 = supplierDAO.getSupplierFromName("Sidharth's");
		Supplier retrievedSupplier2 = supplierDAO.getSupplierFromName("Nalini's");
		assertEquals(supplier1, retrievedSupplier1);
		assertEquals(supplier2, retrievedSupplier2);
	}
	
	

}
