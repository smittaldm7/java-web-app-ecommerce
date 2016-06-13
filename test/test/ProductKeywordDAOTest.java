package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.List;

import model.Keyword;
import model.Product;
import model.ProductKeyword;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import service.DeleteDAO;
import service.KeywordDAO;
import service.ProductDAO;
import service.ProductKeywordDAO;

public class ProductKeywordDAOTest {

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
	public void testAddProductKeyword() throws SQLException {
		Product product = new Product("Lenovo Desktop 211");
		Keyword keyword1 = new Keyword("computer");
		Keyword keyword2 = new Keyword("lenovo");
		Keyword keyword3 = new Keyword("desktop");
		
		ProductDAO productDAO = new ProductDAO(Database.getInstance().getConnection());
		productDAO.addProduct(product);
		int productID = productDAO.getProductIDFromName("Lenovo Desktop 211");
		
		
		KeywordDAO keywordDAO = new KeywordDAO(Database.getInstance().getConnection());
		keywordDAO.addKeyword(keyword1);
		keywordDAO.addKeyword(keyword2);
		keywordDAO.addKeyword(keyword3);
		int keywordID1 = keywordDAO.getKeywordIDFromText("computer");
		int keywordID2 = keywordDAO.getKeywordIDFromText("lenovo");
		int keywordID3 = keywordDAO.getKeywordIDFromText("desktop");
		
		
		ProductKeyword productKeyword1 = new ProductKeyword(productID, keywordID1);
		ProductKeyword productKeyword2 = new ProductKeyword(productID, keywordID2);
		ProductKeyword productKeyword3 = new ProductKeyword(productID, keywordID3);
		ProductKeywordDAO productKeywordDAO = new ProductKeywordDAO(Database.getInstance().getConnection());
		productKeywordDAO.addProductKeyword(productKeyword1);
		productKeywordDAO.addProductKeyword(productKeyword2);
		productKeywordDAO.addProductKeyword(productKeyword3);
		
		ProductKeyword retrievedProductKeyword1 = productKeywordDAO.getProductKeyword(productID, keywordID1);
		ProductKeyword retrievedProductKeyword2 = productKeywordDAO.getProductKeyword(productID, keywordID2);
		ProductKeyword retrievedProductKeyword3 = productKeywordDAO.getProductKeyword(productID, keywordID3);
		
		assertEquals(productKeyword1, retrievedProductKeyword1);
		assertEquals(productKeyword2, retrievedProductKeyword2);
		assertEquals(productKeyword3, retrievedProductKeyword3);
		
	}
	
	@Test
	public void testGetProductFromSearchString() throws SQLException {
		
		String searchString1 = new String("computer");
		String searchString2 = new String("dell");
		
		Product product1 = new Product("Lenovo Desktop 211");
		Keyword keyword1 = new Keyword("computer");
		Keyword keyword2 = new Keyword("lenovo");
		Keyword keyword3 = new Keyword("desktop");
		
		Product product2 = new Product("Dell Laptop 32A");
		Keyword keyword4 = new Keyword("dell");
		Keyword keyword5 = new Keyword("laptop");
	
		
		ProductDAO productDAO = new ProductDAO(Database.getInstance().getConnection());
		productDAO.addProduct(product1);
		productDAO.addProduct(product2);
		int product1ID = productDAO.getProductIDFromName("Lenovo Desktop 211");
		int product2ID = productDAO.getProductIDFromName("Dell Laptop 32A");
		
		
		KeywordDAO keywordDAO = new KeywordDAO(Database.getInstance().getConnection());
		keywordDAO.addKeyword(keyword1);
		keywordDAO.addKeyword(keyword2);
		keywordDAO.addKeyword(keyword3);
		keywordDAO.addKeyword(keyword4);
		keywordDAO.addKeyword(keyword5);
				
		int keyword1ID = keywordDAO.getKeywordIDFromText("computer");
		int keyword2ID = keywordDAO.getKeywordIDFromText("lenovo");
		int keyword3ID = keywordDAO.getKeywordIDFromText("desktop");
		int keyword4ID = keywordDAO.getKeywordIDFromText("dell");
		int keyword5ID = keywordDAO.getKeywordIDFromText("laptop");
		
		
		ProductKeyword productKeyword1 = new ProductKeyword(product1ID, keyword1ID);
		ProductKeyword productKeyword2 = new ProductKeyword(product1ID, keyword2ID);
		ProductKeyword productKeyword3 = new ProductKeyword(product1ID, keyword3ID);
		ProductKeyword productKeyword4 = new ProductKeyword(product2ID, keyword1ID);
		ProductKeyword productKeyword5 = new ProductKeyword(product2ID, keyword4ID);
		ProductKeyword productKeyword6 = new ProductKeyword(product2ID, keyword5ID);
		
		ProductKeywordDAO productKeywordDAO = new ProductKeywordDAO(Database.getInstance().getConnection());
		
		productKeywordDAO.addProductKeyword(productKeyword1);
		productKeywordDAO.addProductKeyword(productKeyword2);
		productKeywordDAO.addProductKeyword(productKeyword3);
		productKeywordDAO.addProductKeyword(productKeyword4);
		productKeywordDAO.addProductKeyword(productKeyword5);
		productKeywordDAO.addProductKeyword(productKeyword6);
		
		List<Product> searchResultProducts1 = 
				productKeywordDAO.getProductFromSearchString(searchString1);
		
		List<Product> searchResultProducts2 = 
				productKeywordDAO.getProductFromSearchString(searchString2);
		
		assertEquals(searchResultProducts1.size(),2);
		assertEquals(searchResultProducts1.get(0),product1);
		assertEquals(searchResultProducts1.get(1),product2);
		
		assertEquals(searchResultProducts2.size(),1);
		assertEquals(searchResultProducts2.get(0),product2);
		
		
	}

}
