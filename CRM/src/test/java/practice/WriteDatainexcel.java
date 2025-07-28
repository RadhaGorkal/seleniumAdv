package practice;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.EncryptedDocumentException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class WriteDatainexcel {

	public static void main(String[] args) throws EncryptedDocumentException, IOException 
	{
		FileInputStream fis=new FileInputStream("E:\\AdvanceSelenium\\CreateMultipulData.xlsx");
         Workbook wb = WorkbookFactory.create(fis);
         Row expectedRow = wb.getSheet("ProductsInfo").getRow(11);
       Cell c1 = expectedRow.createCell(0);
       c1.setCellValue("Iphone");
        Cell c2 = expectedRow.createCell(1);
        c2.setCellValue("Iphone15");
        
        
        FileOutputStream foi=new FileOutputStream("E:\\AdvanceSelenium\\CreateMultipulData.xlsx");
        wb.write(foi);
        wb.close();
        
        System.out.println("successfully enterde the data");
        
        
        
       

	}

}
