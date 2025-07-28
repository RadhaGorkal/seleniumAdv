package genericUtility;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class Excelfileutility
{
	public String ReadDataFromExcel(String SheetName,int RowNum, int cellNum ) throws IOException
	{
		FileInputStream fis=new FileInputStream("./configAppdata/createCampaign.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		String data=wb.getSheet(SheetName).getRow(RowNum).getCell(cellNum).getStringCellValue();
		wb.close();
		return data;
		}
	//get row count
	public int GetRowCount(String SheetName,int rowNum,int cellNum) throws IOException
	{
		
		FileInputStream fis=new FileInputStream("./configAppdata/createCampaign.xlsx");
		Workbook wb=WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("sheetName");
		int lastRow = sh.getLastRowNum();
		for(int i=1;i<=lastRow ;i++)
		{
			String pName = sh.getRow(i).getCell(0).getStringCellValue();
			String bName = sh.getRow(i).getCell(1).getStringCellValue();
		wb.close();
		}
		return lastRow;

	}
	
	//write data into excelfile
	public void WriteDataIntoExcel(int rowNum, String sheetName, int cellNum, Date cellValue) throws IOException
	{
		FileOutputStream fos=new FileOutputStream("./configAppdata/createCampaign.xlsx");
		
		Workbook wb=WorkbookFactory(fos);
		
		Row expectedRow = wb.getSheet(sheetName).getRow(rowNum);
	       Cell c1 = expectedRow.createCell(cellNum);
	       c1.setCellValue(cellValue);
	        
	       FileOutputStream foi=new FileOutputStream("./configAppdata/createCampaign.xlsx");
	        wb.write(foi);
	        wb.close();
	        
	        System.out.println("successfully enterde the data");
		
		
		
		
		
		
	}
	private Workbook WorkbookFactory(FileOutputStream fos) {
		// TODO Auto-generated method stub
		return null;
	}
}
		
		
		
		
		
		
		
		
	

	
		
	


