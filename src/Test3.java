import java.util.Calendar;

public class Test3 {

	public static void main(String[] args) {

		Calendar cal=Calendar.getInstance();
		java.util.Date date=cal.getTime();
		System.out.println(date);
		cal.add(cal.MINUTE, 60);
		date=cal.getTime();
		System.out.println(date);
		
	}

}
