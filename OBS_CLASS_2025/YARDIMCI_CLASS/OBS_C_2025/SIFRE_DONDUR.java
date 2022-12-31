package OBS_C_2025;

import javax.swing.JPasswordField;

public class SIFRE_DONDUR {

	public  String sDONDUR(JPasswordField sifre)
	{
	 	String passText = new String(sifre.getPassword());
  		return passText;
	}
	public byte[] sCOZ(String kELIME)
	{
		String decodedString = kELIME;
		String[] byteValues = decodedString.substring(1, decodedString.length() - 1).split(",");
		byte[] bytes = new byte[byteValues.length];
		for (int i=0, len=bytes.length; i<len; i++) {
		   bytes[i] = Byte.parseByte(byteValues[i].trim());     
		}
		return bytes;
		
	}
}
