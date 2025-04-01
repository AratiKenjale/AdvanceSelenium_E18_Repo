package ddtPractice;

import java.io.FileReader;
import java.io.IOException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DDTByJson
{
public static void main(String[] args) throws IOException, ParseException {
	JSONParser parser= new JSONParser();
	FileReader file= new FileReader("E:\\Workspace1\\AdvanceSelenium_E18_ProjectName\\src\\test\\resources\\Data.json");
	Object javaObj = parser.parse(file);
	
	JSONObject obj= (JSONObject) javaObj;
	String name = obj.get("name").toString();
	String id = obj.get("id").toString();
	String branch=obj.get("branch").toString();
	String age=obj.get("age").toString();
	String gender=obj.get("gender").toString();
	String isStudent=obj.get("isStudent").toString();
	Object backlog = obj.get("backlog");
	
	System.out.println(name);
	System.out.println(id);
	System.out.println(branch);
	System.out.println(age);
	System.out.println(gender);
	System.out.println(isStudent);
	System.out.println(backlog);
	
	
}
}
