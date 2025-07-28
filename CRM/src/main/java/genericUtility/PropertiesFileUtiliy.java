package genericUtility;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesFileUtiliy 
{
	public String ReadDatafromProFile(String key) throws IOException
	{
		FileInputStream fis=new FileInputStream("./configAppdata/commonData.properties");
		Properties pro=new Properties();
		pro.load(fis);
		String data = pro.getProperty(key);
		return data;
	}

}
