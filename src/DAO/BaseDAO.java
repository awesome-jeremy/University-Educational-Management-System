package DAO;

import java.sql.Connection;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class BaseDAO {
	public DataSource dataSource;
	public BaseDAO() {
		// TODO Auto-generated constructor stub
		
		try {
			Context initContext = new InitialContext();
			dataSource = (DataSource)initContext.lookup("java:/comp/env/jdbc/For_Educational_Management_System");
			
		} catch (Exception e) {
			System.out.println(e);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConnection() throws SQLException{
		return dataSource.getConnection();
	}
}
