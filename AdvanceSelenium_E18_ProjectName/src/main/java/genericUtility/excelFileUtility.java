package genericUtility;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class excelFileUtility 
{
public String readingDataFromExcel(String sheet,int rowNum,int cellNum) throws EncryptedDocumentException, IOException
{
	FileInputStream fis= new FileInputStream("E:\\Workspace1\\AdvanceSelenium_E18_ProjectName\\src\\test\\resources\\CreateCampaignTestData.xlsx");
	Workbook book = WorkbookFactory.create(fis);
	String data = book.getSheet(sheet).getRow(rowNum).getCell(cellNum).getStringCellValue();
	return data;
}
}
