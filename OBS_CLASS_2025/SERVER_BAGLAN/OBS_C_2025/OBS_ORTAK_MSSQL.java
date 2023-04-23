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
		if ( ! sbilgi.port.equals("") )
		{
			sbilgi.port =  ":" + sbilgi.port ;
		}
		try
		{
			String cumle = "";
			cumle = "jdbc:sqlserver://localhost" + sbilgi.port  +";instanceName=" + sbilgi.ins + ";";
			conn = DriverManager.getConnection(cumle,sbilgi.kull,sbilgi.sifre);
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
			cumle = "jdbc:sqlserver://" + sbilgi.server + ";instanceName=" + sbilgi.ins + ";";
			conn = DriverManager.getConnection(cumle,sbilgi.kull,sbilgi.sifre);
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
		if ( ! sbilgi.port.toString().equals("") )
		{
			sbilgi.port =  ":" + sbilgi.port ;
		}
		cumle ="jdbc:sqlserver://localhost" + sbilgi.port + ";instanceName=" + sbilgi.ins + ";";
		conn = DriverManager.getConnection(cumle,sbilgi.kull,sbilgi.sifre);
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM sys.databases where name = '" + sbilgi.db + "'");
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
	public boolean Dosyakontrol_S(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = null;  
		String cumle = "";
		cumle =  "jdbc:sqlserver://" + sbilgi.server + ";instanceName=" + sbilgi.ins + ";";
		conn = DriverManager.getConnection(cumle,sbilgi.kull,sbilgi.sifre);
		PreparedStatement stmt = conn.prepareStatement("SELECT * FROM sys.databases where name = '" + sbilgi.db + "'");
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
}
