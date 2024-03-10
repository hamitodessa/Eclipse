package OBS_C_2025;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@SuppressWarnings("static-access")
public class GOREV_GLOBAL {
	public Connection S_CONN;
	public Connection MY_CONN; //= new MySqlConnection();
	GLOBAL glb = new GLOBAL();
	private Connection con ;

	public List<g_bilgiler> gorev_bilgi_oku() throws ClassNotFoundException, SQLException 
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		ResultSet	rss = null;
		con = glb.myGorevConnection();
		String sql = "SELECT * FROM BILGILER ";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		List<g_bilgiler> bilgi = new ArrayList<g_bilgiler>();
		rss.next();
		int count=0;
		count = rss.getRow();
		if (count == 0 ) {  
			g_bilgiler list = new g_bilgiler("", new Date(), "",false );
			bilgi.add(list);
		} 
		else {
			g_bilgiler list = new g_bilgiler("", new Date(), rss.getString("OBS_KULLANICI"),rss.getInt("Durum") == 0 ? false:true);
			bilgi.add(list);
		}
		stmt.close();
		con.close();
		return bilgi;
	}
	public List<g_bilgiler> gorev_kur_oku() throws ClassNotFoundException, SQLException 
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		ResultSet	rss = null;
		con = glb.myGorevConnection();
		String sql = "SELECT * FROM KUR_CINSI ";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		List<g_bilgiler> bilgi = new ArrayList<g_bilgiler>();
		while(rss.next())
		{
			g_bilgiler list = new g_bilgiler(rss.getString("Kur"), new Date(), "",false);
			bilgi.add(list);
		}
		stmt.close();
		con.close();
		return bilgi;
	}
	public List<g_bilgiler> gorev_zaman_oku() throws ClassNotFoundException, SQLException, ParseException 
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		ResultSet	rss = null;
		con = glb.myGorevConnection();
		String sql = "SELECT * FROM GOREV_ZAMANI ";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		List<g_bilgiler> bilgi = new ArrayList<g_bilgiler>();
		rss.next();
		int count=0;
		count = rss.getRow();
		if(count==0){  
			g_bilgiler list = new g_bilgiler("", new Date(), "",false);
			bilgi.add(list);
		} 
		else {
			Date bas =	new SimpleDateFormat("HH:mm").parse(rss.getString("Zaman"));
			g_bilgiler list = new g_bilgiler("", bas, "",false);
			bilgi.add(list);
		}
		stmt.close();
		con.close();
		return bilgi;
	}
	public void bilgi_kayit(String kull,boolean drm) throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		con = glb.myGorevConnection();
		String sql = "";
		sql = "DELETE FROM BILGILER ";
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		sql = "INSERT INTO BILGILER (OBS_KULLANICI,Durum) "
				+ "VALUES (?,?)";
		stmt = con.prepareStatement(sql);
		stmt.setString(1, kull);
		stmt.setInt(2, drm == false ? 0:1);
		stmt.executeUpdate();
		stmt.close();
		con.close();
		con = null;
	}
	public void kur_sil()throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		con = glb.myGorevConnection();
		String sql = "";
		sql = "DELETE FROM KUR_CINSI ";
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		stmt.close();
		con.close();
		con = null;
	}
	public void kur_kayit(String kur)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		con = glb.myGorevConnection();
		String sql = "";
		sql = "INSERT INTO KUR_CINSI (Kur) "
				+ "VALUES (?)";
		stmt = con.prepareStatement(sql);
		stmt.setString(1, kur);
		stmt.executeUpdate();
		stmt.close();
		con.close();
		con = null;
	}
	public void pid_kayit(int pid)throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		con = glb.myGorevConnection();
		String sql = "";
		sql = "INSERT INTO PID (PID_NO) "
				+ "VALUES (?)";
		stmt = con.prepareStatement(sql);
		stmt.setInt(1, pid);
		stmt.executeUpdate();
		stmt.close();
		con.close();
		con = null;
	}
	public void pid_sil()throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		con = glb.myGorevConnection();
		String sql = "";
		sql = "DELETE FROM PID ";
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		stmt.close();
		con.close();
		con = null;
	}
	public int pid_oku() throws ClassNotFoundException, SQLException 
	{
		Class.forName("org.sqlite.JDBC");
		if (con != null && ! con.isClosed()) con.close();
		PreparedStatement stmt = null;
		ResultSet	rss = null;
		con = glb.myGorevConnection();
		String sql = "SELECT PID_NO FROM PID ";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		rss.next();
		int pidno = rss.getInt("PID_NO");
		stmt.close();
		con.close();
		return pidno;
	}
	public void zaman_kaydet(Date zmn) throws SQLException, ClassNotFoundException
	{
		Class.forName("org.sqlite.JDBC");
		PreparedStatement stmt = null;
		if (con != null && ! con.isClosed()) con.close();
		con = glb.myGorevConnection();
		stmt = con.prepareStatement("DELETE FROM GOREV_ZAMANI ");
		stmt.executeUpdate();
		String sql ="INSERT INTO  GOREV_ZAMANI  (Zaman) "
				+ "VALUES (?)";
		stmt = con.prepareStatement(sql);
		String str = TARIH_CEVIR.milismmss(zmn.getTime());
		stmt.setString(1, str);
		stmt.executeUpdate();
		stmt.close();
		con.close();
		con = null;
	}
}
