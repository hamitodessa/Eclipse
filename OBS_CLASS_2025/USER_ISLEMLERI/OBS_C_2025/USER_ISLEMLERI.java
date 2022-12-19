package OBS_C_2025;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Base64;

@SuppressWarnings("static-access")
public class USER_ISLEMLERI {
	static  Connection con ;

	static boolean result = false;
	private GLOBAL gLB = new GLOBAL();

	public  Boolean user_var(String usr,String pwd) throws ClassNotFoundException, SQLException {
		Class.forName("org.sqlite.JDBC");
		con = null;
		result = false;
		con = gLB.myConnection();
		java.sql.PreparedStatement stmt = con.prepareStatement("SELECT * FROM USERS WHERE USER_NAME=? AND USER_PWD=?");

		stmt.setString(1, usr.toString());
		stmt.setString(2, pwd.toString());
		ResultSet rs = stmt.executeQuery();
		rs.next();
		int count=0;
		count = rs.getRow();
		if (count  != 0) result =true;
		stmt.close();
		con.close();
		return result;
	}
	
	public void user_sil(String usr) throws SQLException, ClassNotFoundException
	{
		Class.forName("org.sqlite.JDBC");
		con.close();
		con = null;
		PreparedStatement stmt = null;
		con =  gLB.myConnection();
		String sql = "DELETE FROM USERS WHERE USER_NAME = ? ";
		stmt = con.prepareStatement(sql);
		stmt.setString(1,usr);
		stmt.executeUpdate();
		stmt.close();
		con.close();
	}
	public void user_details_sil(String user) throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		con.close();
		con = null;
		PreparedStatement stmt = null;
		con = gLB.myConnection();
		String sql = "DELETE  FROM USER_DETAILS WHERE USER_NAME =?";
		stmt = con.prepareStatement(sql);
		stmt.setString(1,user);
		stmt.executeUpdate();
		stmt.close();
		con.close();
	}
	public ResultSet user_details_bak() throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		con.close();
		con = null;
		ResultSet	rss = null;
		PreparedStatement stmt = null;
		con = gLB.myConnection();
		String sql = "SELECT  * FROM USER_DETAILS WHERE USER_PROG_OBS <> 'Fihrist'  ORDER BY USER_NAME ";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}
	public ResultSet user_isim_doldur() throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		con.close();
		con = null;
		ResultSet	rss = null;
		PreparedStatement stmt = null;
		con = gLB.myConnection();
		String sql = "SELECT  USER_NAME FROM USERS ORDER BY USER_NAME";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}
	public Boolean user_bak(String usr) throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		con.close();
		con = null;
		result = false;
		con = gLB.myConnection();
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM USERS WHERE USER_NAME=?");
		stmt.setString(1, usr.toString());
		ResultSet rs = stmt.executeQuery();
		rs.next();
		int count=0;
		count = rs.getRow();
		if (count  != 0) result =true;
		stmt.close();
		con.close();
		return result;
	}
	public ResultSet user_db_izinleri(String username,String prg) throws SQLException, ClassNotFoundException
	{
		Class.forName("org.sqlite.JDBC");
		con.close();
		con = null;
		ResultSet	rss = null;
		PreparedStatement stmt = null;
		con =  gLB.myConnection();
		String sql = "SELECT * FROM USER_DETAILS WHERE USER_NAME=? AND USER_PROG_OBS=? AND IZINLI_MI='E' ORDER BY CALISAN_MI DESC , USER_PROG_KODU";
		stmt = con.prepareStatement(sql);
		stmt.setString(1, username.toString());
		stmt.setString(2, prg.toString());
		rss = stmt.executeQuery();
		return rss;
	}
	public void sifre_degis(String user,String sifre) throws SQLException, ClassNotFoundException
	{
		Class.forName("org.sqlite.JDBC");
		PreparedStatement stmt = null;
		con.close();
		con = null;
		con =  gLB.myConnection();
		String sql ="UPDATE  USERS  SET USER_PWD=?  WHERE USER_NAME=?";
		stmt = con.prepareStatement(sql);
		String encodedString = Base64.getEncoder().encodeToString(sifre.getBytes());
		stmt.setString(1, encodedString);
		stmt.setString(2, user.toString());
		stmt.executeUpdate();
		stmt.close();
		con.close();
	}
	public  ResultSet user_details_izinleri(String kull, String modul, String nerde) throws SQLException, ClassNotFoundException
	{
		Class.forName("org.sqlite.JDBC");
		con.close();
		gLB.myConnection().close();
		con = null;
		ResultSet	rss = null;
		PreparedStatement stmt = null;
		con =  gLB.myConnection();
		String sql = "SELECT * FROM USER_DETAILS WHERE USER_NAME  ='" + kull + "' AND USER_PROG_OBS ='" + modul + "' AND " + nerde + " AND  IZINLI_MI ='E' ORDER BY CALISAN_MI DESC";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}
	public void calisanmi_degis(String user , String program,String yer ) throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		con.close();
		con = null;
		PreparedStatement stmt = null;
		con =  gLB.myConnection();
		String sql = "UPDATE  USER_DETAILS  SET CALISAN_MI=?  WHERE USER_NAME =?  AND USER_PROG_OBS=?";
		stmt = con.prepareStatement(sql);
		stmt.setString(1,"");
		stmt.setString(2,user);
		stmt.setString(3,program);
		stmt.executeUpdate();
		stmt.close();
		con.close();
	}
	public void details_yaz(String upk, String usn, String usserver, String sifre, String instance, String ip, String prog
			,String dizin, String yer, String dcins, String izli, String calmi, String hsql,String cdid,int log,String logla) throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		con.close();
		con = null;
		PreparedStatement stmt = null;
		con = gLB. myConnection();
		if (!cdid.equals(""))
		{ 
			String sql = "DELETE FROM USER_DETAILS WHERE CDID = " + cdid + " ";
			stmt = con.prepareStatement(sql);
			stmt.executeUpdate();
		}
		String sql = "INSERT INTO USER_DETAILS (USER_PROG_KODU,USER_NAME,USER_SERVER,USER_PWD_SERVER,USER_INSTANCE_OBS,USER_IP_OBS," +
				"USER_PROG_OBS,DIZIN,YER,DIZIN_CINS,IZINLI_MI,CALISAN_MI,HANGI_SQL,LOG,LOG_YERI) ";
		sql += "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		{
			stmt = con.prepareStatement(sql);
			stmt.setString(1, upk);
			stmt.setString(2, usn);
			stmt.setString(3, usserver);
			String encodedString = Base64.getEncoder().encodeToString(sifre.getBytes());
			stmt.setString(4, encodedString);
			stmt.setString(5, instance);
			stmt.setString(6, ip);
			stmt.setString(7, prog);
			stmt.setString(8, dizin);
			stmt.setString(9, yer);
			stmt.setString(10,dcins);
			stmt.setString(11,izli);
			stmt.setString(12, calmi);
			stmt.setString(13, hsql);
			stmt.setInt(14, log);
			stmt.setString(15, logla);
		}
		stmt.executeUpdate();
		stmt.close();
		con.close();
	}
	public void user_ekle_degis(String user , String pwd ,String lvl ,String udbi ,String um ,Boolean uyda ,Boolean uydas ) throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		con.close();
		con = null;
		PreparedStatement stmt = null;
		con =  gLB.myConnection();
		String sql = "INSERT INTO USERS (USER_NAME,USER_PWD,USER_LEVEL,USER_DB_IZIN,USER_MAIL,USER_YENI_DOSYA_ACMA,USER_YENI_DOSYA_ACMA_SERVER)" +
				"VALUES (?,?,?,?,?,?,?)";
		{
			stmt = con.prepareStatement(sql);
			stmt.setString(1, user);
			String encodedString = Base64.getEncoder().encodeToString(pwd.getBytes());
			stmt.setString(2, encodedString);
			stmt.setString(3, lvl);
			stmt.setString(4, udbi);
			stmt.setString(5, um);
			stmt.setBoolean(6, uyda);
			stmt.setBoolean(7, uydas);
		}
		stmt.executeUpdate();
		stmt.close();
		con.close();
	}
	public void ip_dos_kont(String ip) throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		con.close();
		con = null;
		ResultSet	rss = null;
		PreparedStatement stmt = null;
		con =  gLB.myConnection();
		String sql ="SELECT  * FROM IP WHERE IP ='" + ip + "'";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		int count=0;
		rss.next();
		count = rss.getRow();
		if (count  == 0) 
		{
			stmt = con.prepareStatement("INSERT INTO IP (IP) VALUES (?)");
			stmt.setString(1,ip);
			stmt.executeUpdate();
		}
		stmt.close();
		con.close();
	}
	public void log_mail_kont_kayit(String ussr, String mail) throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		con.close();
		con = null;
		ResultSet	rss = null;
		PreparedStatement stmt = null;
		con =  gLB.myConnection();
		String sql ="SELECT  * FROM LOG_MAIL WHERE USER_NAME = '" + ussr + "' AND  E_MAIL = '" + mail + "'";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		int count=0;
		rss.next();
		count = rss.getRow();
		if (count  == 0) 
		{
			stmt = con.prepareStatement("INSERT INTO LOG_MAIL (USER_NAME,E_MAIL, AKTIV) VALUES (?,?,?)");
			stmt.setString(1,ussr);
			stmt.setString(2,mail);
			stmt.setInt(3,1);
			stmt.executeUpdate();
			stmt = con.prepareStatement("UPDATE LOG_MAIL SET AKTIV = '0' WHERE USER_NAME =   '" + ussr+ "' AND E_MAIL <> '"+ mail +"'");
			stmt.executeUpdate();
			stmt.close();
		}
		else
		{
			PreparedStatement stmt1 = null;
			stmt1 = con.prepareStatement("UPDATE LOG_MAIL SET AKTIV = '1' WHERE USER_NAME =   '" + ussr+ "' AND E_MAIL = '"+ mail +"'");
			stmt1.executeUpdate();
			stmt1 = con.prepareStatement("UPDATE LOG_MAIL SET AKTIV = '0' WHERE USER_NAME =   '" + ussr+ "' AND E_MAIL <> '"+ mail +"'");
			stmt1.executeUpdate();
			stmt1.close();
		}
		con.close();
	}
	public   ResultSet log_mail_oku (String kull ) throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		con.close();
		con = null;
		ResultSet	rss = null;
		PreparedStatement stmt = null;
		con =  gLB.myConnection();
		String sql = "SELECT DISTINCT E_MAIL FROM LOG_MAIL  WHERE USER_NAME = '" + kull + "' ORDER BY E_MAIL";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}
	public void    log_mail_sil (String kull , String mail ) throws SQLException, ClassNotFoundException 
	{
		Class.forName("org.sqlite.JDBC");
		con.close();
		con = null;
		PreparedStatement stmt = null;
		con =  gLB.myConnection();
		String sql = "DELETE  FROM LOG_MAIL   WHERE USER_NAME = '" + kull + "' AND E_MAIL = '"+ mail +"'";
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
	public    String log_mail_aktiv_oku  (String kull ) throws ClassNotFoundException, SQLException 
	{
		Class.forName("org.sqlite.JDBC");
		con.close();
		con = null;
		ResultSet	rss = null;
		PreparedStatement stmt = null;
		GLOBAL glb = new GLOBAL();
		con =  glb.myConnection();
		String sql = "SELECT DISTINCT E_MAIL FROM LOG_MAIL  WHERE USER_NAME = '" + kull + "'  AND AKTIV =1";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		String aktiv = "" ;
		while (rss.next()) 
		{
			aktiv = rss.getString("E_MAIL");
		}
		return aktiv;
	}
	public   ResultSet  ipp (String kull) throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		con.close();
		con = null;
		ResultSet	rss = null;
		PreparedStatement stmt = null;
		con =  gLB.myConnection();
		String sql = "SELECT DISTINCT IP FROM IP ";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}
	public void cd_sil(int cdid) throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		con.close();
		con = null;
		PreparedStatement stmt = null;
		con =  gLB.myConnection();
		String sql ="DELETE  FROM USER_DETAILS WHERE CDID =" + cdid + "";
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		stmt.close();
	}
	public ResultSet user_ekleme_bak() throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		con.close();
		con = null;
		ResultSet	rss = null;
		PreparedStatement stmt = null;
		con =  gLB.myConnection();
		String sql = "SELECT  * FROM USERS ORDER BY USER_NAME ";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}
	public void mail_bak() throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		con.close();
		con = null;
		ResultSet	rss = null;
		PreparedStatement stmt = null;
		con =  gLB.myConnection();
		String sql = "SELECT  * FROM E_MAIL_BILGILERI WHERE USER_NAME = ? ";
		stmt = con.prepareStatement(sql);
		stmt.setString(1,gLB.KULL_ADI);
		rss = stmt.executeQuery();
		int count=0;
		rss.next();
		count = rss.getRow();
		if (count  == 0)
		{
			MAIL_SETTINGS.HESAP= "";
			MAIL_SETTINGS. HOST ="";
			MAIL_SETTINGS.PORT ="";
			MAIL_SETTINGS.PWD = "";
			MAIL_SETTINGS.SSL = false;
			MAIL_SETTINGS. TSL = false;
			MAIL_SETTINGS.GHESAP ="";
			MAIL_SETTINGS. GADI = "";
		}
		else
		{
			MAIL_SETTINGS.HESAP= rss.getString("HESAP").toString();
			MAIL_SETTINGS. HOST =rss.getString("HOST").toString() ;
			MAIL_SETTINGS.PORT =rss.getString("PORT").toString() ;
			byte[] decodedBytes = Base64.getDecoder().decode(rss.getString("SIFR").toString());
			String decodedString = new String(decodedBytes);
			MAIL_SETTINGS.PWD = decodedString  ;
			MAIL_SETTINGS.SSL = (boolean) (rss.getInt("SSL") == -1 ? false :true );
			MAIL_SETTINGS. TSL = (boolean) (rss.getInt("TSL") == -1 ? false :true );
			MAIL_SETTINGS.GHESAP = rss.getString("GON_MAIL").toString();
			MAIL_SETTINGS. GADI = rss.getString("GON_ISIM").toString();
		}
	}
	public void mail_yaz(String hsp,String host,String port,String sifre,String gmail,String ghesap,int ssl,int tsl) throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		con.close();
		con = null;
		PreparedStatement stmt = null;
		con = gLB.myConnection();
		String sql = "DELETE FROM E_MAIL_BILGILERI WHERE USER_NAME = ? ";
		stmt = con.prepareStatement(sql);
		stmt.setString(1, GLOBAL.KULL_ADI);
		stmt.executeUpdate();
		stmt = null;
		sql = "INSERT INTO E_MAIL_BILGILERI (USER_NAME,HESAP,HOST,PORT,SIFR,GON_MAIL,GON_ISIM,SSL,TSL)" +
				"VALUES (?,?,?,?,?,?,?,?,?)";
		stmt = con.prepareStatement(sql);
		stmt.setString(1,  GLOBAL.KULL_ADI);
		stmt.setString(2, hsp);
		stmt.setString(3, host);
		stmt.setString(4, port);
		String encodedString = Base64.getEncoder().encodeToString(sifre.getBytes());
		stmt.setString(5, encodedString);
		stmt.setString(6, gmail);
		stmt.setString(7, ghesap);
		stmt.setInt(8, ssl);
		stmt.setInt(9, tsl);
		stmt.executeUpdate();
		stmt.close();
		con.close();
	}
	public ResultSet alici_oku(String usr) throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		con.close();
		con = null;
		ResultSet	rss = null;
		PreparedStatement stmt = null;
		con = gLB.myConnection();
		String sql = "SELECT DISTINCT ALICI  " + 
				"FROM   GIDEN_RAPOR WHERE USER_NAME  LIKE '" + usr + "' ORDER BY ALICI ";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}
	public void giden_rapor_yaz(Date tar ,String konu,String rapor,String alici,String gond,String aciklama,String uname) throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		con.close();
		con = null;
		PreparedStatement stmt = null;
		con = gLB.myConnection();
		String sql =  "INSERT INTO GIDEN_RAPOR (USER_NAME,TARIH,KONU,RAPOR,ALICI,ACIKLAMA,GONDEREN) " +
				"VALUES (?,?,?,?,?,?,?)";
		stmt = con.prepareStatement(sql);
		stmt.setString(1,  uname);
		stmt.setDate(2, (java.sql.Date) tar);
		stmt.setString(3, konu);
		stmt.setString(4, rapor);
		stmt.setString(5, alici);
		stmt.setString(6, aciklama);
		stmt.setString(7, gond);
		stmt.executeUpdate();
		stmt.close();
		con.close();
	}
	public void giden_rapor_sil(int id) throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		con.close();
		con = null;
		PreparedStatement stmt = null;
		con = gLB.myConnection();
		String sql = "DELETE  FROM GIDEN_RAPOR WHERE ID  =" + id + "";
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		stmt.close();
		con.close();	       
	}
	public ResultSet giden_rapor(String usr) throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		con.close();
		con = null;
		ResultSet	rss = null;
		PreparedStatement stmt = null;
		con = gLB.myConnection();
		String sql = "SELECT ID,TARIH ,KONU ,RAPOR, ALICI ,GONDEREN, ACIKLAMA ,USER_NAME AS [USER] " + 
				"FROM   GIDEN_RAPOR WHERE USER_NAME  LIKE '" + usr + "' ORDER BY TARIH ";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}
}
