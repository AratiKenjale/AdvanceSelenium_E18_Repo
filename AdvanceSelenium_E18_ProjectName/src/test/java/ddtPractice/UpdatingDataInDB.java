package ddtPractice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import com.mysql.cj.jdbc.Driver;

public class UpdatingDataInDB 
{
	public static void main(String[] args) throws SQLException 
	{
		//register driver
		Driver driver= new Driver();
		DriverManager.registerDriver(driver);
		
		//connect to database
		Connection con= DriverManager.getConnection("jdbc:mysql://localhost:3306/Ninza_E18", "root", "Mysqlroot@123");
		
		//create sql statement
		Statement statement = con.createStatement();
		//update data in DB
		int result = statement.executeUpdate("update Ninza_CRM_Details set uname='admn' where Browser='edge'");

		System.out.println(result);
		if(result!=0)
		{
			System.out.println("data stored successfully");
		}
		else
		{
			System.out.println("data not stored");
		}
		
		con.close();
	}
}
	
	
	
