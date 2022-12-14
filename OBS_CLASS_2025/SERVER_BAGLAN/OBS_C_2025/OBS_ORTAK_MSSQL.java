package OBS_C_2025;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class OBS_ORTAK_MSSQL implements IConnection {

	private  boolean result;
	public boolean Server_kontrol_L(String inst, String kull, String sifre, String port) throws ClassNotFoundException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = null; 
		if ( ! port.toString().equals("") )
		{
			port =  ":" + port ;
		}
		try
		{
			String cumle = "";
			cumle = "jdbc:sqlserver://localhost" + port  +";instanceName=" + inst + ";";
			conn = DriverManager.getConnection(cumle,kull,sifre);
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
	public boolean Server_kontrol_S(String server,  String inst,String kull, String sifre, String port) throws ClassNotFoundException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = null;  
		try
		{
			String cumle = "";
			cumle = "jdbc:sqlserver://" + server + ";instanceName=" + inst + ";";
			conn = DriverManager.getConnection(cumle,kull,sifre);
			conn.close();
			result =  true;
		} 
		catch (SQLException e)
		{  
			JOptionPane.showMessageDialog(null, e.getMessage(),  "Server Baglanti", JOptionPane.ERROR_MESSAGE);     
			result =  false;  
		}  
		return result;
	}
	public boolean Dosyakontrol_L(String db, String inst, String kull, String sifre, String port) throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		Connection conn = null;  
		String cumle = "";
		if ( ! port.toString().equals("") )
		{
			port =  ":" + port ;
		}
		cumle ="jdbc:sqlserver://localhost" + port + ";instanceName=" + inst + ";";
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
	public boolean Dosyakontrol_S(String server, String inst, String kull, String sifre, String prog, String port) throws ClassNotFoundException, SQLException
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
}
