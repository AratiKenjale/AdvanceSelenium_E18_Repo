package genericUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DatabaseUtility 
{
	Connection conn;
	public void gettDBConnection(String url,String uname, String password) throws SQLException
	{
		try 
		{
		Driver driver= new Driver();
		DriverManager.registerDriver(driver);
		Connection conn = DriverManager.getConnection(url, uname, password);
		}
		catch(Exception e)
		{
			System.out.println("Connection not established");
		}
	}
	
	public void closeDBConnection()
	{
		try
		{
		conn.close();
		}
		catch(Exception e)
		{
			
		}
	}
	
	public ResultSet executeSelectQuery(String query) 
	{
		ResultSet result=null;
		try {
		Statement statement = conn.createStatement();
		result = statement.executeQuery(query);
		}
		catch(Exception e)
		{
		}
		return result;
	}

	public int updateQuery(String query) 
	{
		int result=0;
		try {
		Statement statement = conn.createStatement();
		result = statement.executeUpdate(query);
		}
		catch(Exception e){
		}
		return result;
	}
}
