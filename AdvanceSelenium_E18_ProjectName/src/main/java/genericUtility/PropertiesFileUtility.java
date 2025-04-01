package genericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileUtility 
{
public String readingDataFromPropFile(String Key) throws IOException
{
	FileInputStream file= new FileInputStream("E:\\Workspace1\\AdvanceSelenium_E18_ProjectName\\src\\test\\resources\\ninza.properties");
	
	Properties prop= new Properties();
	prop.load(file);
	
	String data = prop.getProperty(Key);
	return data;

}
}
