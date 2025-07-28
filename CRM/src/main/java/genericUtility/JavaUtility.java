package genericUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class JavaUtility 
{
	public int GenerateRandomNumber()
	{
		Random ran=new Random();
		int randomNum = ran.nextInt(5000);
		return randomNum;
	}
	
	//to capture current date
	public String getSystemDateddMMyyyy()
	{
	Date d=new Date();
	SimpleDateFormat sim = new SimpleDateFormat("yyyy-MM-dd");
	
	String currentDate = sim.format(d);
	return currentDate;
	}
	
	//to capture required date
	public String GetRequiredDate(int days)
	{
		Date dateObj=new Date();
	    SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
	    simple.format(dateObj);
	    Calendar cal=simple.getCalendar();
	    cal.add(Calendar.DAY_OF_MONTH, days);
	    String reqDate = simple.format(cal.getTime());
	    return  reqDate;
	    
	
	}
	
	
	
	
	
	
	
	
	

}
