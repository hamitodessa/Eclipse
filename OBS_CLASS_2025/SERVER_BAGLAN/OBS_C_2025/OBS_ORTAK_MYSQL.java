package OBS_C_2025;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;




public class OBS_ORTAK_MYSQL implements IConnection {
	private static boolean result;
	
	public boolean Server_kontrol_L(String inst, String kull, String sifre) throws ClassNotFoundException
    {
		
		  String url = "jdbc:mysql://localhost:3306"; //pointing to no database.
//		    String username = "root";
//		    String password = "197227oOk";
		    boolean durum = false;
		    System.out.println("Connecting to server...");

		    try (Connection connection = DriverManager.getConnection(url, kull, sifre)) {
		        System.out.println("Server connected!");
		        Statement stmt = null;
		        ResultSet resultset = null;
		        	durum = true;
	
		      
		    } catch (SQLException e) {
		    	durum =false;
		        throw new IllegalStateException("Cannot connect the server!", e);
		    
		    }
			return durum;
		
		
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
