package ddtPractice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class CreateDataInExcel 
{
public static void main(String[] args) throws EncryptedDocumentException, IOException 
{
	FileInputStream file= new FileInputStream("E:\\Workspace1\\AdvanceSelenium_E18_ProjectName\\src\\test\\resources\\TestData1.xlsx");
	Workbook wb = WorkbookFactory.create(file);
	wb.createSheet("Sheet2").createRow(1).createCell(0).setCellValue("Aabc");
	
	FileOutputStream fos= new FileOutputStream("E:\\\\Workspace1\\\\AdvanceSelenium_E18_ProjectName\\\\src\\\\test\\\\resources\\\\TestData1.xlsx");
	wb.write(fos);
	wb.close();
	System.out.println("Data written successfully");
	
}
}
