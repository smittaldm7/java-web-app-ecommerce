package test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.sql.SQLException;

import model.Keyword;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import service.DeleteDAO;
import service.KeywordDAO;

public class KeywordDAOTest {

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
	public void testAddKeyword() throws SQLException {
		Keyword keyword = new Keyword("Computer");
		KeywordDAO keywordDAO = new KeywordDAO(Database.getInstance().getConnection());
		keywordDAO.addKeyword(keyword);
		int id = keywordDAO.getKeywordIDFromText("Computer");
		assertTrue(id>0);
	}
	
	@Test
	public void testGetKeywordIDFromText() throws SQLException {
		Keyword keyword = new Keyword("Computer");
		KeywordDAO keywordDAO = new KeywordDAO(Database.getInstance().getConnection());
		keywordDAO.addKeyword(keyword);
		int id = keywordDAO.getKeywordIDFromText("Computer");
		//dummy comment
		assertTrue(id>0);
	}
	

}
