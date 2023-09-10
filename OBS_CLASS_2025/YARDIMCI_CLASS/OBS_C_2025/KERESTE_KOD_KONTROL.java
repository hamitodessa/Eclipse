package OBS_C_2025;

import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.text.MaskFormatter;

public class KERESTE_KOD_KONTROL {
	
	public static boolean kontrol(String kod)
	{
		JFormattedTextField ftext = new JFormattedTextField();
		MaskFormatter mask;
		try {
		    mask = new MaskFormatter("AA-###-####-####");
		    mask.install(ftext);
		} catch (ParseException e) {
		    e.printStackTrace();
		}
		ftext.setText(kod);
		boolean result = false ;
		result = ftext.isEditValid();
		return result;
	}
}
