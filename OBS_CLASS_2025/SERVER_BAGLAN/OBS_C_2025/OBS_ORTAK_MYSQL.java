package OBS_C_2025;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class OBS_ORTAK_MYSQL implements IConnection {
	private static boolean result;
	@SuppressWarnings("unused")
	public boolean Server_kontrol_L(String inst, String kull, String sifre, String port) throws ClassNotFoundException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;  
		String url = "jdbc:mysql://localhost:" + port ; //pointing to no database.
		try 
		{
			conn = DriverManager.getConnection(url, kull, sifre);
			result = true;
		} catch (SQLException e)
		{
			result =false;
		}
		return result;
	}
	@SuppressWarnings("unused")
	public boolean Server_kontrol_S(String server, String inst, String kull, String sifre, String port) throws ClassNotFoundException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;  
		String url = "jdbc:mysql://" + server ; //pointing to no database.
		try 
		{
			conn = DriverManager.getConnection(url, kull, sifre);
			result = true;
		} catch (SQLException e)
		{
			result =false;
		}
		return result;
	}
	public boolean Dosyakontrol_L(String db, String inst, String kull, String sifre, String port) throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;  
		Statement stmt = null;
		ResultSet resultset = null;
		String url = "jdbc:mysql://localhost:" + port ; //pointing to no database.
		try 
		{
			conn = DriverManager.getConnection(url, kull, sifre);
			stmt = conn.createStatement();
			resultset = stmt.executeQuery("SHOW DATABASES;");
			result =false;
			while (resultset.next()) 
			{
				if(resultset.getString("Database").equals(db.toLowerCase()))
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
	public boolean Dosyakontrol_S(String server, String inst, String kull, String sifre, String prog, String port) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		Connection conn = null;  
		Statement stmt = null;
		ResultSet resultset = null;
		String url = "jdbc:mysql://" + server ; //pointing to no database.
		try 
		{
			conn = DriverManager.getConnection(url, kull, sifre);
			stmt = conn.createStatement();
			resultset = stmt.executeQuery("SHOW DATABASES;");
			result =false;
			while (resultset.next()) 
			{
				if(resultset.getString("Database").equals(prog.toLowerCase()))
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
}
