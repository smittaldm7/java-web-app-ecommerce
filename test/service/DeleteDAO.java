package service;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class DeleteDAO {
	Connection conn;
	
	public DeleteDAO(Connection conn) {
		this.conn=conn;
	}
	
	public void deleteAllData() throws SQLException
	{

		CallableStatement cstmt = conn.prepareCall("{call deleteData()} ");
		
		cstmt.execute();
				
		cstmt.close();

	}

}
