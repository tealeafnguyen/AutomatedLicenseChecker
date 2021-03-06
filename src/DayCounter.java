import java.text.*;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.concurrent.TimeUnit;


public class DayCounter {

	public static void count(ArrayList<String> names, ArrayList<String> dates){ //Change this to accept array lists of the license names and dates
		long  nearingExpiration = 30; // counts within 31 days due to rounding up when calculating difference between days
		Date testDate = null;
		String message = "";

		for(int x = 0; x < dates.size(); x++){
			try{
				testDate = testFun(dates.get(x));
			}
			catch(ParseException e){
				System.out.println("Error parsing document dates");
				System.exit(0);
			}

			Date currDate = printUniversalTime();

			long delta = deltaDay(currDate, testDate);

			if(delta <= nearingExpiration && delta >= -1){ 
				String temp = message + names.get(x) + " expires in " + String.valueOf(delta+1) + " day(s)\n";
				message = temp;
			}
		}
		
		EmailSender es = new EmailSender();
		es.send(message);
	}
	
	public static long deltaDay(Date date1, Date date2){ //add return later
	    long diff = date2.getTime() - date1.getTime();
	    return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
	  }
	
	public static Date testFun(String startDateString)throws ParseException{
	    DateFormat df = new SimpleDateFormat("MM/dd/yyyy"); 
	    Date startDate;
	    try {
	        startDate = df.parse(startDateString);
	        String newDateString = df.format(startDate);
	        //System.out.println(newDateString);
	        return startDate;
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    return null;
	  }
	  
	  public static Date printUniversalTime(){
	    DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();
		//System.out.println(dateFormat.format(date));
		return date;
	  }

}
