package ddtPractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DDTByDatabase 
{
public static void main(String[] args) throws SQLException 
{
	//register the database driver
	Driver driver= new Driver();
	DriverManager.registerDriver(driver);
	
	//connect to database
	Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Ninza_E18", "root", "Mysqlroot@123");
	
	//create sql statement
	Statement statement = con.createStatement();
	
	//sql query
	ResultSet result = statement.executeQuery("select * from Ninza_CRM_Details");
	
	while(result.next())
	{
		String browser = result.getString(1);
		String url = result.getString(2);
		String un = result.getString(3);
		String pwd = result.getString(4);
		
		System.out.println(browser);
		System.out.println(url);
		System.out.println(un);
		System.out.println(pwd);
		System.out.println("===================================");
	}
	
	con.close();
}
}
