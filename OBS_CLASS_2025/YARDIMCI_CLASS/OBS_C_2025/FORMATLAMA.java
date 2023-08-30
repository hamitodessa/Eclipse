package OBS_C_2025;

import java.text.DecimalFormat;

public class FORMATLAMA {

	
	public static String doub_2(double rakkam)
	{
		DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.00");
		String numberAsString = decimalFormat.format(rakkam);
		return numberAsString; 
	}
	public static String doub_3(double rakkam)
	{

		DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.000");
		String numberAsString = decimalFormat.format(rakkam);
		return numberAsString; 
	}
	public static String doub_0(double rakkam)
	{
		DecimalFormat decimalFormat = new DecimalFormat("###,###,##0");
		String numberAsString = decimalFormat.format(rakkam);
		return numberAsString; 
	}
	public static String doub_4(double rakkam)
	{
		DecimalFormat decimalFormat = new DecimalFormat("###,###,##0.0000");
		String numberAsString = decimalFormat.format(rakkam);
		return numberAsString; 
	}
}
