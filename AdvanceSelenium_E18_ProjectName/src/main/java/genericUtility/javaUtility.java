package genericUtility;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

public class javaUtility
{
public int getRandomNum(int limit)
{
	Random ran= new Random();
	int randomNumber = ran.nextInt(limit);
	return randomNumber;
}

public String generateSystemDate()
{
	Date dateObj= new Date();
	SimpleDateFormat simple= new SimpleDateFormat("dd-MM-YYYY");
	String todaydate = simple.format(dateObj);
	return todaydate;
}

public String generateReqDate(int days)
{
	Date dateObj= new Date();
	SimpleDateFormat simple= new SimpleDateFormat("dd-MM-YYYY");
	String todaydate = simple.format(dateObj);

	Calendar cal= simple.getCalendar();
	cal.add(Calendar.DAY_OF_MONTH, days);
	String closeDate = simple.format(cal.getTime());
	return closeDate;
}
}
