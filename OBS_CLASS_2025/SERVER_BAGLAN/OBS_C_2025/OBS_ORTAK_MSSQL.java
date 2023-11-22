package OBS_C_2025;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class OBS_ORTAK_MSSQL implements IConnection {

	private  boolean result;
	public boolean Server_kontrol_L(Server_Bilgi sbilgi) throws ClassNotFoundException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = null; 
		if ( ! sbilgi.getPort().equals("") )
		{
			sbilgi.setPort(":" + sbilgi.getPort());
		}
		try
		{
			String cumle = "";
			cumle = "jdbc:sqlserver://localhost" + sbilgi.getPort()  +";instanceName=" + sbilgi.getIns() + ";";
			conn = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
			conn.close();
			result = true;
		} 
		catch (SQLException e)
		{  
			JOptionPane.showMessageDialog(null, e.getMessage(),  "Server Baglanti", JOptionPane.ERROR_MESSAGE);      
			result = false;  
		}  
		return result;
	}
	public boolean Server_kontrol_S(Server_Bilgi sbilgi) throws ClassNotFoundException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = null;  
		try
		{
			
			String cumle = "";
			cumle = "jdbc:sqlserver://" + sbilgi.getServer() + ";instanceName=" + sbilgi.getIns() + ";";
			conn = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
			conn.close();
			
			result =  true;
		} 
		catch (SQLException e)
		{  
			JOptionPane.showMessageDialog(null, e.getMessage(),  "Server Baglanti_S", JOptionPane.ERROR_MESSAGE);     
			result =  false;  
		}  
		return result;
	}
	public boolean Dosyakontrol_L(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = null;  
		String cumle = "";
		if ( ! sbilgi.getPort().toString().equals("") )
		{
			sbilgi.setPort( ":" + sbilgi.getPort());
		}
		cumle ="jdbc:sqlserver://localhost" + sbilgi.getPort() + ";instanceName=" + sbilgi.getIns() + ";";
		conn = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM sys.databases where name = '" + sbilgi.getDb() + "'");
		ResultSet rs = stmt.executeQuery();
		result = !rs.isBeforeFirst() ? false :true ;
		stmt.close();
		conn.close();
		return result;
	}
	public boolean Dosyakontrol_S(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = null;  
		String cumle = "";
		cumle =  "jdbc:sqlserver://" + sbilgi.getServer() + ";instanceName=" + sbilgi.getIns() + ";";
		 
		conn = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM sys.databases where name = '" + sbilgi.getDb() + "'");
		ResultSet rs = stmt.executeQuery();
		result = !rs.isBeforeFirst() ? false :true ;
		stmt.close();
		conn.close();
		return result;
	}
}
