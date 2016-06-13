package test;

import static org.junit.Assert.*;

import java.sql.SQLException;
import java.util.List;

import model.Product;
import model.Supplier;
import model.SupplierProduct;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import service.DeleteDAO;
import service.ProductDAO;
import service.SupplierDAO;
import service.SupplierProductDAO;

public class SupplierProductDAOTest {

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
	public void testAddSupplierProduct() throws SQLException {
		ProductDAO productDAO = new ProductDAO(Database.getInstance().getConnection());
		productDAO.addProduct(new Product("Puma Running Shoes"));
	
		SupplierDAO supplierDAO = new SupplierDAO(Database.getInstance().getConnection());
		supplierDAO.addSupplier(new Supplier("Guptas", "#101, MG Road, Bangalore"));
		supplierDAO.addSupplier(new Supplier("Mehras", "#12, 5th Block Kormangala, Bangalore"));
		
		SupplierProductDAO supplierProductDAO = new SupplierProductDAO(Database.getInstance().getConnection());
		SupplierProduct sp1 = new SupplierProduct("Guptas", "Puma Running Shoes", 5, 3000, 0);
		SupplierProduct sp2 = new SupplierProduct("Mehras", "Puma Running Shoes" , 1, 2800, 0);
		supplierProductDAO.addSupplierProduct(sp1);
		supplierProductDAO.addSupplierProduct(sp2);
		
		List<SupplierProduct> spList = supplierProductDAO.getSuppliersForProduct("Puma Running Shoes");

		assertEquals(2,spList.size());
		assertEquals(sp1,spList.get(0));
		assertEquals(sp2,spList.get(1));
	}

	
	
	@Test
	public void testGetSuppliersForProduct() throws SQLException {
		ProductDAO productDAO = new ProductDAO(Database.getInstance().getConnection());
		productDAO.addProduct(new Product("Puma Running Shoes"));
	
		SupplierDAO supplierDAO = new SupplierDAO(Database.getInstance().getConnection());
		supplierDAO.addSupplier(new Supplier("Guptas", "#101, MG Road, Bangalore"));
		supplierDAO.addSupplier(new Supplier("Mehras", "#12, 5th Block Kormangala, Bangalore"));
		
		SupplierProductDAO supplierProductDAO = new SupplierProductDAO(Database.getInstance().getConnection());
		SupplierProduct sp1 = new SupplierProduct("Guptas", "Puma Running Shoes", 5, 3000, 0);
		SupplierProduct sp2 = new SupplierProduct("Mehras", "Puma Running Shoes" , 1, 2800, 0);
		supplierProductDAO.addSupplierProduct(sp1);
		supplierProductDAO.addSupplierProduct(sp2);
		
		List<SupplierProduct> spList = supplierProductDAO.getSuppliersForProduct("Puma Running Shoes");

		assertEquals(2,spList.size());
		assertEquals(sp1,spList.get(0));
		assertEquals(sp2,spList.get(1));
		
	}
	
}
