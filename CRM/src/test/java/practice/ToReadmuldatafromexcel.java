package practice;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ToReadmuldatafromexcel 
{

	public static void main(String[] args) throws IOException, InterruptedException 
	{
		FileInputStream fis=new FileInputStream("E:\\AdvanceSelenium\\CreateMultipulData.xlsx");
		Workbook wb = WorkbookFactory.create(fis);
		Sheet sh = wb.getSheet("ProductsInfo");
		int lastrow = sh.getLastRowNum();
		for(int i=1;i<=lastrow ;i++)
		{
			String pName = sh.getRow(i).getCell(0).getStringCellValue();
			String bName = sh.getRow(i).getCell(1).getStringCellValue();
			System.out.println("ProductName :"+pName +"\t"+ "BrandName:"+bName);
			Thread.sleep(2000);
		}
		wb.close();
	}

}
