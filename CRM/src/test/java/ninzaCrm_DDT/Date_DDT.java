package ninzaCrm_DDT;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Date_DDT {

	public static void main(String[] args) 
	{
		Date d=new Date();
		System.out.println(d);
		
		SimpleDateFormat simple = new SimpleDateFormat("yyyy-MM-dd");
		String currentDate = simple.format(d);
		System.out.println(currentDate);
		
	    Calendar cal = simple.getCalendar();

		cal.add(Calendar.DAY_OF_MONTH, 5);
		String reqDate = simple.format(cal.getTime());
		System.out.println(reqDate);
	}

}
