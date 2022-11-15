package OBS_C_2025;

import java.sql.DriverManager;
import java.sql.ResultSet;
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
	@Override
	public ResultSet instance(String server, String user, String pwd) throws SQLException, ClassNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}
}
