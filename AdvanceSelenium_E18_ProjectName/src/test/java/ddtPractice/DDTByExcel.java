package ddtPractice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class DDTByExcel 
{
public static void main(String[] args) throws EncryptedDocumentException, IOException 
{
	FileInputStream file= new FileInputStream("E:\\Workspace1\\AdvanceSelenium_E18_ProjectName\\src\\test\\resources\\TestData1.xlsx");
	Workbook book= WorkbookFactory.create(file);
	String Campaign = book.getSheet("DDT").getRow(1).getCell(2).getStringCellValue();
	double targetSize = book.getSheet("DDT").getRow(1).getCell(3).getNumericCellValue();
	System.out.println(Campaign);
	System.out.println(targetSize);
	System.out.println("=============================================");
	String testCase = (book.getSheet("DDT").getRow(2).getCell(1).getStringCellValue())+" AND "+(book.getSheet("DDT").getRow(2).getCell(2).getStringCellValue());
	System.out.println(testCase);
	

}
}
