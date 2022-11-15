package OBS_C_2025;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class OBS_ORTAK_MSSQL implements IConnection {

	private  boolean result;
	
	public boolean Server_kontrol_L(String inst, String kull, String sifre) throws ClassNotFoundException
    {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = null;  
        try
        {
        	
            String cumle = "";
            cumle = "jdbc:sqlserver://localhost;instanceName=" + inst + ";";
            conn = DriverManager.getConnection(cumle,kull,sifre);
    		conn.close();
            return true;
        } 
        catch (SQLException e)
        {  
        return false;  
        }  
    }
	public boolean Server_kontrol_S(String server,  String inst,String kull, String sifre) throws ClassNotFoundException
    {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = null;  
        try
        {
            String cumle = "";
            cumle = "jdbc:sqlserver://" + server + ";instanceName=" + inst + ";";
            conn = DriverManager.getConnection(cumle,kull,sifre);
            conn.close();
            return true;
        } 
        catch (SQLException e)
        {  
        return false;  
        }  
    }
	public boolean Dosyakontrol_L(String db, String inst, String kull, String sifre) throws ClassNotFoundException, SQLException 
     {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = null;  
		String cumle = "";
        cumle ="jdbc:sqlserver://localhost;instanceName=" + inst + ";";
        
        conn = DriverManager.getConnection(cumle,kull,sifre);
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM sys.databases where name = '" + db + "'");
		ResultSet rs = stmt.executeQuery();
		if (!rs.isBeforeFirst() ) {  
		result = false;
		}
		else
		{
			result = true;
		}
		stmt.close();
		conn.close();
		return result;
     }
    public boolean Dosyakontrol_S(String server, String inst, String kull, String sifre, String prog) throws ClassNotFoundException, SQLException
     {
    	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
 		Connection conn = null;  
 		String cumle = "";
        cumle =  "jdbc:sqlserver://" + server + ";instanceName=" + inst + ";";
        conn = DriverManager.getConnection(cumle,kull,sifre);
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM sys.databases where name = '" + prog + "'");
 		ResultSet rs = stmt.executeQuery();
 		if (!rs.isBeforeFirst() ) {  
 		    result = false;
 		}
 		else
 		{
 			result = true;
 		}
 		stmt.close();
 		conn.close();
 		return result;
     }
	@Override
	public ResultSet instance(String server, String user, String pwd) throws SQLException, ClassNotFoundException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
 		Connection conn = null;  
 		String cumle = "";
         cumle =  "jdbc:sqlserver://" + server + ";encrypt=true;integratedSecurity=true;";
        conn = DriverManager.getConnection(cumle,user,pwd);
        PreparedStatement stmt = conn.prepareStatement("SELECT SERVERPROPERTY ('instancename')");
 		ResultSet rs = stmt.executeQuery();
 		
 		stmt.close();
 		conn.close();
		return rs;
	}
}
