package service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import model.Keyword;



public class KeywordDAO {
	
	Connection conn;

	public KeywordDAO(Connection conn) {
		this.conn=conn;
	}

	public int addKeyword(Keyword keyword) throws SQLException {

		//Connection conn = Database.getInstance().getConnection();

		PreparedStatement p = conn
				.prepareStatement("insert into keyword (text) values (?)");

		p.setString(1, keyword.getText());
		
		int status = p.executeUpdate();

		p.close();

		return status;
	}
	
	public int getKeywordIDFromText(String text) throws SQLException {
		//Connection conn = Database.getInstance().getConnection();
		
		PreparedStatement p = conn
				.prepareStatement("select * from Keyword where text = ?");
		
		p.setString(1,text);
		
		ResultSet rs = p.executeQuery();
		
		rs.next();
		
		int id = rs.getInt("Id");
		
		p.close();
		
		return id;
	}

	/* (non-Javadoc)
	 * @see com.caveofprogramming.designpatterns.demo1.model.StringDAO#getString(int)
	 */
	
	
/*
	public String getKeyword(int id) throws SQLException {
		Connection conn = Database.getInstance().getConnection();

		String sql = "select id, name, password from people where id=? order by id";
		PreparedStatement selectStatement = conn.prepareStatement(sql);
		
		selectStatement.setInt(1, id);

		ResultSet results = selectStatement.executeQuery();

		String keyword = null;

		if (results.next()) {
			String name = results.getString("name");
			String password = results.getString("password");

			keyword = new String(id, name, password);
		}

		results.close();
		selectStatement.close();

		return keyword;
	}
*/
	/* (non-Javadoc)
	 * @see com.caveofprogramming.designpatterns.demo1.model.StringDAO#getPeople()
	 */
	/*
	public List<String> getKeywords() throws SQLException {

		List<String> people = new ArrayList<String>();

		Connection conn = Database.getInstance().getConnection();

		String sql = "select id, name, password from people order by id";
		Statement selectStatement = conn.createStatement();

		ResultSet results = selectStatement.executeQuery(sql);

		while (results.next()) {
			int id = results.getInt("id");
			String name = results.getString("name");
			String password = results.getString("password");

			String keyword = new String(id, name, password);
			people.add(keyword);
		}

		results.close();
		selectStatement.close();

		return people;
	}
	*/

	/* (non-Javadoc)
	 * @see com.caveofprogramming.designpatterns.demo1.model.StringDAO#updateString(com.caveofprogramming.designpatterns.demo1.model.String)
	 */
	/*
	public int updateKeyword(String keyword) throws SQLException {
		Connection conn = Database.getInstance().getConnection();

		PreparedStatement p = conn
				.prepareStatement("update people set name=?, password=? where id=?");

		p.setString(1, keyword.getName());
		p.setString(2, keyword.getPassword());
		p.setInt(3, keyword.getId());

		int updated = p.executeUpdate();

		p.close();
		
		return updated;
	}
	*/
	/* (non-Javadoc)
	 * @see com.caveofprogramming.designpatterns.demo1.model.StringDAO#deleteString(int)
	 */
	
	/*
	public int deleteKeyword(int id) throws SQLException {
		Connection conn = Database.getInstance().getConnection();

		PreparedStatement p = conn
				.prepareStatement("delete from people where id=?");

		p.setInt(1, id);

		int deleted = p.executeUpdate();

		p.close();
		
		return deleted;
	}
	*/
	/* (non-Javadoc)
	 * @see com.caveofprogramming.designpatterns.demo1.model.StringDAO#deleteAll()
	 */

	/*
	public int deleteAll() throws SQLException {
		Connection conn = Database.getInstance().getConnection();

		PreparedStatement p = conn
				.prepareStatement("delete from people");

		int deleted = p.executeUpdate();

		p.close();
		
		return deleted;
	}
	*/
}
