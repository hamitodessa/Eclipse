package OBS_C_2025;

import java.sql.SQLException;




public class OBS_ORTAK_MYSQL implements IConnection {
	private static boolean result;
	@SuppressWarnings("unused")
	public boolean Server_kontrol_L(String inst, String kull, String sifre) throws ClassNotFoundException
    {
		return false;  
    }
	@SuppressWarnings("unused")
	public boolean Server_kontrol_S(String server, String inst, String kull, String sifre) throws ClassNotFoundException
    {
		return true;  
    }
	public boolean Dosyakontrol_L(String db, String inst, String kull, String sifre) throws ClassNotFoundException, SQLException 
     {
		return false;
		
     }
    public boolean Dosyakontrol_S(String server, String inst, String kull, String sifre, String prog) throws ClassNotFoundException, SQLException
     {
		return false;
       
            
     }
	
}
