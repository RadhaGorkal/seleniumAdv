package practice;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class ToWriteDataIntoProp {

	public static void main(String[] args) throws IOException 
	{
		FileOutputStream fos=new FileOutputStream("E:\\AdvanceSelenium\\TowriteDataIntoPr.properties");
		Properties pop=new Properties();
	    pop.setProperty("Name", "Radha");
	    pop.setProperty("favFood", "Biryani");
	    pop.setProperty("Colour", "blue");
	    pop.store(fos, "Student Info");
	    
	    System.out.println("successfully entered data");
	    
	    

	}

}
