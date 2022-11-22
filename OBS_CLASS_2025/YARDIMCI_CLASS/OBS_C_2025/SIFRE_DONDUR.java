package OBS_C_2025;

import java.util.Base64;

import javax.swing.JPasswordField;

public class SIFRE_DONDUR {

	public  String sDONDUR(JPasswordField sifre)
	{
	 	String passText = new String(sifre.getPassword());
  		return passText;
		
	}
}
