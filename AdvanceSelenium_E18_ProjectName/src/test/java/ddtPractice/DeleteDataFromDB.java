package ddtPractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class DeleteDataFromDB 
{
public static void main(String[] args) throws SQLException 
{
	//register driver
	Driver driver= new Driver();
	DriverManager.registerDriver(driver);
	//connect to database
	Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Ninza_E18", "root", "Mysqlroot@123");
	
	//create sql statement
	Statement statement = con.createStatement();
	
	//delete from db
	int result = statement.executeUpdate("delete from Ninza_CRM_Details where Browser='chrome'");
	
	System.out.println(result);
	
	if(result !=0)
	{
		System.out.println("operation successful");
	}
	else
	{
		System.out.println("Operation failed");
	}
	
	con.close();
}
}
