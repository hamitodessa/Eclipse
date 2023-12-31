package OBS_C_2025;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;


@SuppressWarnings("static-access")
public class GOREV_GLOBAL {
	public Connection S_CONN;
	public Connection MY_CONN; //= new MySqlConnection();
	GLOBAL glb = new GLOBAL();
	private Connection con ;
	
	public ResultSet gorev_bilgi_oku() throws ClassNotFoundException, SQLException 
	{
	
	
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		ResultSet	rss = null;
		con = glb.myGorevConnection();
		String sql = "SELECT * FROM BILGILER ";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}
	public void bilgi_kayit(String kull) throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		con = glb.myGorevConnection();
		String sql = "";
		sql = "INSERT INTO BILGILER (OBS_KULLANICI) "
				+ "VALUES (?)";
		stmt = con.prepareStatement(sql);
		stmt.setString(1, kull);
		
		stmt.executeUpdate();
		stmt.close();
		con.close();
		con = null;
	}

}
