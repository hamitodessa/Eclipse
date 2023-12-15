package OBS_C_2025;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class OBS_ORTAK_MYSQL implements IConnection {
	private static boolean result;
	@SuppressWarnings("unused")
	public boolean Server_kontrol_L(Server_Bilgi sbilgi) throws ClassNotFoundException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;  
		String url = "jdbc:mysql://localhost:" + sbilgi.getPort() ; //pointing to no database.
		try 
		{
			conn = DriverManager.getConnection(url, sbilgi.getKull(), sbilgi.getSifre());
			result = true;
		} catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(),  "Server Baglanti", JOptionPane.ERROR_MESSAGE);     
			result =false;
		}
		return result;
	}
	@SuppressWarnings("unused")
	public boolean Server_kontrol_S(Server_Bilgi sbilgi) throws ClassNotFoundException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;  
		String url = "jdbc:mysql://" + sbilgi.getServer() ; //pointing to no database.
		try 
		{
			conn = DriverManager.getConnection(url, sbilgi.getKull(), sbilgi.getSifre());
			result = true;
		} catch (SQLException e)
		{
			JOptionPane.showMessageDialog(null, e.getMessage(),  "Server Baglanti", JOptionPane.ERROR_MESSAGE);     
			result =false;
		}
		return result;
	}
	public boolean Dosyakontrol_L(Server_Bilgi  sbilgi) throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;  
		Statement stmt = null;
		ResultSet resultset = null;
		String url = "jdbc:mysql://localhost:" + sbilgi.getPort() ; //pointing to no database.
		try 
		{
			conn = DriverManager.getConnection(url, sbilgi.getKull(), sbilgi.getSifre());
			stmt = conn.createStatement();
			resultset = stmt.executeQuery("SHOW DATABASES;");
			result =false;
			while (resultset.next()) 
			{
				if(resultset.getString("Database").equals(sbilgi.getDb().toLowerCase()))
				{
					result = true;
				}
			}
		} catch (SQLException e)
		{
			result =false;
		}
		return result;
	}
	public boolean Dosyakontrol_S(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;  
		Statement stmt = null;
		ResultSet resultset = null;
		String url = "jdbc:mysql://" + sbilgi.getServer() ; //pointing to no database.
		try 
		{
			conn = DriverManager.getConnection(url, sbilgi.getKull(), sbilgi.getSifre());
			stmt = conn.createStatement();
			resultset = stmt.executeQuery("SHOW DATABASES;");
			result =false;
			while (resultset.next()) 
			{
				if(resultset.getString("Database").equals(sbilgi.getDb().toLowerCase()))
				{
					result = true;
				}
			}
		} catch (SQLException e)
		{
			result =false;
		}
		return result;
	}
	@Override
	public void job_sil_L(String jobName,Server_Bilgi sbilgi) {
		//DROP EVENT [IF EXISTS] jobName
		
	}
	@Override
	public void job_sil_S(String jobName, Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		//DROP EVENT [IF EXISTS] jobName
		
	}
	@Override
	public void job_baslat_L(String jobName, Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
//		CREATE EVENT Satirlar_Yenile
//		  ON SCHEDULE EVERY '1' DAY
//		STARTS CONCAT(DATE(NOW()+INTERVAL 1 DAY ), ' 00:00:00')
//		    DO
//		      OPTIMIZE TABLE Satirlar;
//		      OPTIMIZE TABLE izahat;
//		      OPTIMIZE TABLE hesap;
		
	}
	@Override
	public void job_baslat_S(String jobName, Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void job_olustur_L(String jobName, String dosya,String indexISIM, Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void job_olustur_S(String jobName, String dosya,String indexISIM, Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	//OPTIMIZE TABLE Satirlar
}
