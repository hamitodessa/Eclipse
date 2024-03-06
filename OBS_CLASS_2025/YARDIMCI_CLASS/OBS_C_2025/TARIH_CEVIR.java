package OBS_C_2025;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.toedter.calendar.JDateChooser;

public class TARIH_CEVIR {

	public boolean isValidDate(String dateStr) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy.MM.dd HH:mm");
		dateFormat.setLenient(false);
		try {
			dateFormat.parse(dateStr.trim());
		} catch (ParseException pe) {
			return false;
		}
		return true;
	}
	public static  Date tarih(String tar) {

		if(tar.equals("")) return new Date();
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		Date tarih = null;

		try {
			tarih = df.parse(tar);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return tarih;

	}
	public static  String tarihddMMyyyyHHmm(Date tar) {

		SimpleDateFormat df = new SimpleDateFormat("ddMMyyyyHHmm");
		String tarih = df.format(tar);
		return tarih;
	}
	public static  Date tarih_ekstre(String tar) {

		DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
		Date tarih = null;

		try {
			tarih = df.parse(tar);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return tarih;

	}
	public static String tarih_sql(String tar) {
		String gun,ay,yil,tarih = "";

		gun = tar.substring(0, 2);
		ay = tar.substring(3, 5);
		yil = tar.substring(6,10);
		tarih = yil + "." + ay + "."+ gun ;

		return tarih;

	}
	public static String tarih_ters(String tar) {
		String gun,ay,yil,tarih = "";

		gun = tar.substring(8, 10);
		ay = tar.substring(5, 7);
		yil = tar.substring(0,4);
		tarih =  gun + "." + ay + "."+ yil;

		return tarih;

	}
	public static String  tarih_geri(JDateChooser dc) {
		DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
		if (dc.getDate() != null)
			return	df.format(dc.getDate());
		else
			return null;
	}
	public static String  tarih_geri_kasa(JDateChooser dc) {
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		if (dc.getDate() != null)
			return	df.format(dc.getDate());
		else
			return null;
	}
	public static String  tarih_geri_SQL(JDateChooser dc) {
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd");
		if (dc.getDate() != null)
			return	df.format(dc.getDate());
		else
			return null;
	}
	public static String  tarih_dt_ddMMyyyy(JDateChooser dc) {
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		if (dc.getDate() != null)
			return	df.format(dc.getDate());
		else
			return null;
	}
	public static String  gunluk_t_ffmmyyyy(JDateChooser dc) {
		DateFormat df = new SimpleDateFormat("yyyy.MM.dd");
		if (dc.getDate() != null)
			return	df.format(dc.getDate());
		else
			return null;
	}
	public static String chooser_string_eksi1(JDateChooser dc)
	{
		String myDate = tarih_geri_SQL(dc); //"2019/01/01";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date date;
		long millis = 0 ;
		try {
			date = sdf.parse(myDate);
			millis = date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Calendar cal = Calendar.getInstance();
		cal.setTimeInMillis(millis);
		cal.add(Calendar.DATE, -1);
		Date date1 = cal.getTime();             
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy/MM/dd");          
		String inActiveDate = null;
		inActiveDate = format1.format(date1);
		return inActiveDate ;
	}
	public static Long chooser_milis(JDateChooser dc)
	{
		String myDate = tarih_geri_SQL(dc); //"2019/01/01";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
		Date date;
		long millis = 0 ;
		try {
			date = sdf.parse(myDate);
			millis = date.getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return millis ;
	}
	public static String milis_yyyymmss(long lg)
	{
		long currentDateTime = lg ;
		Date currentDate = new Date(currentDateTime);
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		return df.format(currentDate); 
	}
	public static String milismmss(long lg)
	{
		long currentDateTime = lg ;
		Date currentDate = new Date(currentDateTime);
		DateFormat df = new SimpleDateFormat("HH:mm");
		return df.format(currentDate); 
	}
	public static String milis_ddMMyyyy(long lg)
	{
		long currentDateTime = lg ;
		Date currentDate = new Date(currentDateTime);
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy");
		return df.format(currentDate); 
	}
	public static String  tarih_geri_saatli(JDateChooser dc) {
		DateFormat df = new SimpleDateFormat("HH:mm:ss.sss");
		if (dc.getDate() != null) {
			Date currentDate = new Date(System.currentTimeMillis());
			return	tarih_geri(dc)  + " "+ df.format(currentDate);
		}
		else
			return null;
	}
	public static String dateFormater(String dateFromJSON, String expectedFormat, String oldFormat) {
		SimpleDateFormat dateFormat = new SimpleDateFormat(oldFormat);
		Date date = null;
		String convertedDate = null;
		try {
			date = dateFormat.parse(dateFromJSON);
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(expectedFormat);
			convertedDate = simpleDateFormat.format(date);
			SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss.sss");
			String str = sdf.format(new Date());
			convertedDate = convertedDate.substring(0, 10) +  " " + str ;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return convertedDate;
	}
}
