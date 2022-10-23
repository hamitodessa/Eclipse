package OBS_PACKAGE;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;


public class OBS_ORTAK_MYSQL implements IConnection {
	private static boolean result;
	@SuppressWarnings("unused")
	public boolean Server_kontrol_L(String inst, String kull, String sifre) throws ClassNotFoundException
    {
		
		try
        {
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser(kull);
		dataSource.setPassword(sifre);
		dataSource.setServerName("localhost");
		Connection conn = (Connection) dataSource.getConnection();
		//Statement stmt = conn.createStatement();
		//ResultSet rs = stmt.executeQuery("SELECT ID FROM USERS");
		//rs.close();
		//stmt.close();
		//conn.close();
      
            return true;
        } 
        catch (SQLException e)
        {  
        	
        return false;  
        }  
    }
	@SuppressWarnings("unused")
	public boolean Server_kontrol_S(String server, String inst, String kull, String sifre) throws ClassNotFoundException
    {
		try
        {
		MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser(kull);
		dataSource.setPassword(sifre);
		dataSource.setServerName(server);
		Connection conn = (Connection) dataSource.getConnection();
		//Statement stmt = conn.createStatement();
		//ResultSet rs = stmt.executeQuery("SELECT ID FROM USERS");
		//rs.close();
		//stmt.close();
		//conn.close();
      
            return true;
        } 
        catch (SQLException e)
        {  
        	
        return false;  
        }  
    }
	public boolean dosyakontrol_L(String db, String inst, String kull, String sifre) throws ClassNotFoundException, SQLException 
     {
		Class.forName("com.mysql.jdbc.Driver");
		Connection conn = null;  
		String cumle = "";
         cumle ="jdbc:mysql://localhost;instanceName=" + inst + ";user=" + kull + ";password=" + sifre + "";
         conn = (Connection) DriverManager.getConnection(cumle);
         PreparedStatement stmt = (PreparedStatement) conn.prepareStatement("SELECT * FROM sys.databases where name = '" + db + "'");
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
    public boolean dosyakontrol_S(String server, String inst, String kull, String sifre, String prog) throws ClassNotFoundException, SQLException
     {
       	MysqlDataSource dataSource = new MysqlDataSource();
		dataSource.setUser(kull);
		dataSource.setPassword(sifre);
		dataSource.setServerName(server);
		Connection conn = (Connection) dataSource.getConnection();
		Statement stmt = (Statement) conn.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT SCHEMA_NAME FROM INFORMATION_SCHEMA.SCHEMATA WHERE SCHEMA_NAME ='obssistem' " );
		
		//stmt.close();
		//conn.close();
      
 	
       if (!rs.isBeforeFirst() ) {  
		    result = false;
		  
		}
		else
		{
			result = true;
			

		}
   	return result ;
            
     }
}
