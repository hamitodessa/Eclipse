package OBS_C_2025;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.Date;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.swing.JOptionPane;

public class SQL_BACKUP {
	static  Connection con ;
	public static  Connection ccon ;
	static boolean result = false;
	private GLOBAL gLB = new GLOBAL();

	@SuppressWarnings("static-access")
	public void server_kayit_sil(String eismi) throws SQLException, ClassNotFoundException
	{
		if (con != null && con.isClosed() == false) con.close();
		Class.forName("org.sqlite.JDBC");
		PreparedStatement stmt = null;
		con =  gLB.myBackupConnection();
		String sql = "DELETE FROM SERVER  WHERE EMIR_ISMI =  ? ";
		stmt = con.prepareStatement(sql);
		stmt.setString(1,eismi);
		stmt.executeUpdate();
		stmt.close();
		con.close();
	}
	@SuppressWarnings("static-access")
	public void server_ismi_kayit(String eismi,String hangisql, String ins, boolean wi, boolean ser, String kull, String sif,String port) throws ClassNotFoundException, SQLException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException
	{
		if (con != null && con.isClosed() == false) con.close();
		Class.forName("org.sqlite.JDBC");
		PreparedStatement stmt = null;
		con = gLB.myBackupConnection();
		String sql = "INSERT INTO SERVER (EMIR_ISMI,HANGI_SQL,INSTANCE,WIN,SERV,KULLANICI,SIFRE,PORT) ";
		sql += "VALUES (?,?,?,?,?,?,?,?)";
		{
			stmt = con.prepareStatement(sql);
			stmt.setString(1, eismi);
			stmt.setString(2,  hangisql);
			stmt.setString(3, ins);
			stmt.setBoolean(4, wi);
			stmt.setBoolean(5, ser);
			stmt.setString(6, kull);
			stmt.setBytes(7, ENCRYPT_DECRYPT_STRING.eNCRYPT_manual(sif));
			stmt.setString(8, port);
		}
		stmt.execute();
		stmt.close();
		con.close();
	}
	@SuppressWarnings("static-access")
	public ResultSet serBILGI(String emir) throws ClassNotFoundException, SQLException
	{
		if (ccon != null && ccon.isClosed() == false) con.close();
		Class.forName("org.sqlite.JDBC");
		ResultSet	rss = null;
		PreparedStatement stmt = null;
		ccon = gLB.myBackupConnection();
		String sql = "SELECT  * FROM SERVER  WHERE EMIR_ISMI = '" + emir + "'";
		stmt = ccon.prepareStatement(sql);
		rss = stmt.executeQuery();
	
		return rss;
	}
	@SuppressWarnings("static-access")
	public ResultSet emirBILGI(String emir) throws ClassNotFoundException, SQLException
	{
		if (con != null && con.isClosed() == false) con.close();
		Class.forName("org.sqlite.JDBC");
		ResultSet	rss = null;
		PreparedStatement stmt = null;
		con = gLB.myBackupConnection();
		String sql = "SELECT * FROM EMIRLER WHERE  EMIR_ISMI ='" + emir + "'";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
		
	}
	@SuppressWarnings("static-access")
	public String  ftp_NERESI(String emir) throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		ResultSet	rss = null;
		PreparedStatement stmt = null;
		Connection conn = gLB.myBackupConnection();
		String sql = "SELECT HOST FROM FTP WHERE  EMIR_ISMI='" + emir + "'";
		stmt = conn.prepareStatement(sql);
		rss = stmt.executeQuery();
		rss.next();
		String neresi = rss.getString("HOST");
		conn.close();
			return neresi;
	
	}
	@SuppressWarnings("static-access")
	public ResultSet emirLER() throws ClassNotFoundException, SQLException
	{
		//if (con != null && con.isClosed() == false) con.close();
		Class.forName("org.sqlite.JDBC");
		ResultSet	rss = null;
		PreparedStatement stmt = null;
		Connection ccon = gLB.myBackupConnection();
		String sql = "SELECT * FROM EMIRLER ";
		stmt = ccon.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}
	@SuppressWarnings("static-access")
	public ResultSet surBILGI(String emir) throws ClassNotFoundException, SQLException
	{
		if (con != null && con.isClosed() == false) con.close();
		Class.forName("org.sqlite.JDBC");
		ResultSet	rss = null;
		PreparedStatement stmt = null;
		con = gLB.myBackupConnection();
		String sql = "SELECT  * FROM FTP  WHERE EMIR_ISMI = '" + emir + "'";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
	
		return rss;
	}
	@SuppressWarnings("static-access")
	public ResultSet bilgilendirmeBILGI(String emir) throws ClassNotFoundException, SQLException
	{
		if (con != null && con.isClosed() == false) con.close();
		Class.forName("org.sqlite.JDBC");
		ResultSet	rss = null;
		PreparedStatement stmt = null;
		con = gLB.myBackupConnection();
		String sql = "SELECT  * FROM BILGILENDIRME  WHERE EMIR_ISMI = '" + emir + "'";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}
	@SuppressWarnings("static-access")
	public ResultSet yedeklemeBILGI(String emir) throws ClassNotFoundException, SQLException
	{
		if (con != null && con.isClosed() == false) con.close();
		Class.forName("org.sqlite.JDBC");
		ResultSet	rss = null;
		PreparedStatement stmt = null;
		con = gLB.myBackupConnection();
		String sql = "SELECT  * FROM YEDEKLEME  WHERE EMIR_ISMI = '" + emir + "'";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}
	@SuppressWarnings("static-access")
	public ResultSet dbLISTE(String emir) throws ClassNotFoundException, SQLException
	{
		if (ccon != null && ccon.isClosed() == false) ccon.close();
		Class.forName("org.sqlite.JDBC");
		ResultSet	rss = null;
		PreparedStatement stmt = null;
		ccon = gLB.myBackupConnection();
		String sql = "SELECT * FROM DB_ISIM WHERE  EMIR_ISMI ='" + emir + "'";
		stmt = ccon.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
		
	}
	@SuppressWarnings("static-access")
	public int dbSAYISI(String emir) throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		ResultSet	rss = null;
		PreparedStatement stmt = null;
		Connection conn = gLB.myBackupConnection();
		String sql = "SELECT COUNT(DB_ADI) as ADET FROM DB_ISIM WHERE  EMIR_ISMI ='" + emir + "'";
		stmt = conn.prepareStatement(sql);
		rss = stmt.executeQuery();
		rss.next();
		int adet = rss.getInt("ADET");
		conn.close();
			return adet;
	}
	public  ResultSet msSQLDB(String inst , String kull,String sifre) throws ClassNotFoundException, SQLException
	{
		if (con != null && con.isClosed() == false) con.close();
		String cumle = "";
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		cumle = "jdbc:sqlserver://localhost;instanceName=" + inst + ";";
		Connection conn = DriverManager.getConnection(cumle,kull,sifre);
		ResultSet	rss = null;
		String sql ="SELECT NAME FROM master.dbo.sysdatabases ORDER BY NAME";
		PreparedStatement stmt = conn.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	 
		
	}
	public ResultSet mySQLDB(String port , String kull,String sifre) throws ClassNotFoundException, SQLException
	{
		if (con != null && con.isClosed() == false) con.close();
		String cumle = "";
		Class.forName("com.mysql.cj.jdbc.Driver");
		cumle = "jdbc:mysql://localhost:" + port ; //pointing to no database.
		Connection conn = DriverManager.getConnection(cumle,kull,sifre);
		ResultSet	rss = null;
		Statement  stmt = conn.createStatement();
		rss  = stmt.executeQuery("SHOW DATABASES;");
		return rss;	 
		
	}
	@SuppressWarnings("static-access")
	public void genel_kayit_sil(String eismi) throws SQLException, ClassNotFoundException
	{
		if (con != null && con.isClosed() == false) con.close();
		Class.forName("org.sqlite.JDBC");
		PreparedStatement stmt = null;
		con =  gLB.myBackupConnection();
		String sql = "DELETE FROM EMIRLER  WHERE EMIR_ISMI = ? ";
		stmt = con.prepareStatement(sql);
		stmt.setString(1,eismi);
		stmt.executeUpdate();
		stmt.close();
		con.close();
	}
	@SuppressWarnings("static-access")
	public void yedekleme_kayit_sil(String eismi) throws SQLException, ClassNotFoundException
	{
		if (con != null && con.isClosed() == false) con.close();
		Class.forName("org.sqlite.JDBC");
		PreparedStatement stmt = null;
		con =  gLB.myBackupConnection();
		String sql = "DELETE FROM YEDEKLEME  WHERE EMIR_ISMI = ? ";
		stmt = con.prepareStatement(sql);
		stmt.setString(1,eismi);
		stmt.executeUpdate();
		stmt.close();
		con.close();
	}
	@SuppressWarnings("static-access")
	public void genel_kayit(String eismi , boolean drm, String konu, String inst , boolean  sqlyed ) throws ClassNotFoundException, SQLException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException
	{
		if (con != null && con.isClosed() == false) con.close();
		Class.forName("org.sqlite.JDBC");
		PreparedStatement stmt = null;
		con = gLB.myBackupConnection();
		String sql = "INSERT INTO EMIRLER (EMIR_ISMI,DURUM,EMIR_ACIKLAMA,INSTANCE,SQL_YEDEK) ";
		sql += "VALUES (?,?,?,?,?)";
		{
			stmt = con.prepareStatement(sql);
			stmt.setString(1, eismi);
			stmt.setBoolean(2,  drm);
			stmt.setString(3, konu);
			stmt.setString(4, inst);
			stmt.setBoolean(5, sqlyed);
		}
		stmt.executeUpdate();
		stmt.close();
		con.close();
	}
	@SuppressWarnings("static-access")
	public void yedekleme_ismi_kayit(String  eismi , String  saa , boolean  pt ,boolean  sa ,boolean  ca 
            ,boolean  pe ,boolean  cu ,boolean  ct ,boolean  pz ,Date  bas , Date  bit ) throws ClassNotFoundException, SQLException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException
	{
		if (con != null && con.isClosed() == false) con.close();
		Class.forName("org.sqlite.JDBC");
		PreparedStatement stmt = null;
		con = gLB.myBackupConnection();
		String sql = "INSERT INTO YEDEKLEME  (EMIR_ISMI,SAAT,P_TESI,SALI,CARS,PERS,CUMA,C_TESI,PAZAR,BASLAMA,BITIS) ";
		sql += "VALUES (?,?,?,?,?,?,?,?,?,?,?)";
		{
			stmt = con.prepareStatement(sql);
			stmt.setString(1, eismi);
			stmt.setString(2,  saa);
			stmt.setBoolean(3,pt);
			stmt.setBoolean(4,sa);
			stmt.setBoolean(5, ca);
			stmt.setBoolean(6,pe);
			stmt.setBoolean(7,cu);
			stmt.setBoolean(8,ct);
			stmt.setBoolean(9,pz);
			stmt.setDate(10,   new java.sql.Date(bas.getTime()));
			stmt.setDate(11,  new java.sql.Date(bit.getTime()));
			
		}
		stmt.executeUpdate();
		stmt.close();
		con.close();
	}
	@SuppressWarnings("static-access")
	public void genel_kayit_durum(String eismi ,boolean drm, Date yuk ) throws ClassNotFoundException, SQLException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException
	{
		//if (con != null && con.isClosed() == false) con.close();
		Class.forName("org.sqlite.JDBC");
		PreparedStatement stmt = null;
		Connection ccon = gLB.myBackupConnection();
		String sql = "UPDATE EMIRLER SET SON_DURUM = ? , SON_YUKLEME = ? WHERE EMIR_ISMI = '" + eismi + "'";
		stmt = ccon.prepareStatement(sql);
		stmt.setBoolean(1,drm);
		java.sql.Date sDate = new java.sql.Date(yuk.getTime());
		stmt.setDate(2,  sDate);
		stmt.executeUpdate();
		stmt.close();
		ccon.close();
	}
	@SuppressWarnings("static-access")
	public void db_adi_kayit_sil(String eismi) throws SQLException, ClassNotFoundException
	{
		if (con != null && con.isClosed() == false) con.close();
		Class.forName("org.sqlite.JDBC");
		PreparedStatement stmt = null;
		con =  gLB.myBackupConnection();
		String sql = "DELETE FROM DB_ISIM WHERE EMIR_ISMI ='" + eismi + "'";
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		stmt.close();
		con.close();
	}
	@SuppressWarnings("static-access")
	public void db_ismi_kayit(String eismi ,  String dad ) throws ClassNotFoundException, SQLException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException
	{
		if (con != null && con.isClosed() == false) con.close();
		Class.forName("org.sqlite.JDBC");
		PreparedStatement stmt = null;
		con = gLB.myBackupConnection();
		String sql = "INSERT INTO DB_ISIM (EMIR_ISMI,DB_ADI) ";
		sql += "VALUES (?,?)";
		{
			stmt = con.prepareStatement(sql);
			stmt.setString(1, eismi);
			stmt.setString(2, dad);
		}
		stmt.executeUpdate();
		stmt.close();
		con.close();
	}
	@SuppressWarnings("static-access")
	public void ftp_kayit_sil(String eismi) throws SQLException, ClassNotFoundException
	{
		if (con != null && con.isClosed() == false) con.close();
		Class.forName("org.sqlite.JDBC");
		PreparedStatement stmt = null;
		con =  gLB.myBackupConnection();
		String sql = "DELETE FROM FTP  WHERE EMIR_ISMI ='" + eismi + "'";
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		stmt.close();
		con.close();
	}
	@SuppressWarnings("static-access")
	public void diger_dosya_adi_kayit_sil(String eismi) throws SQLException, ClassNotFoundException
	{
		if (con != null && con.isClosed() == false) con.close();
		Class.forName("org.sqlite.JDBC");
		PreparedStatement stmt = null;
		con =  gLB.myBackupConnection();
		String sql = "DELETE FROM DIGER_DOSYA_ISIM WHERE EMIR_ISMI ='" + eismi + "'";
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		stmt.close();
		con.close();
	}
	@SuppressWarnings("static-access")
	public void ftp_ismi_kayit(String eismi , String hst, String kll , String sif , String sur  
            , String prt , int  zmn , String esy ,String neresi, String surucu ) throws ClassNotFoundException, SQLException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException
	{
		if (con != null && con.isClosed() == false) con.close();
		Class.forName("org.sqlite.JDBC");
		PreparedStatement stmt = null;
		con = gLB.myBackupConnection();
		String sql = "INSERT INTO FTP (EMIR_ISMI,HOST,KULLANICI,SIFRE,SURUCU,PORT,ZMN_ASIMI,ESKI_YEDEK,NERESI,SURUCU_YER) ";
		sql += "VALUES (?,?,?,?,?,?,?,?,?,?)";
		{
			stmt = con.prepareStatement(sql);
			stmt.setString(1, eismi);
			stmt.setString(2, hst);
			stmt.setString(3, kll);
			stmt.setBytes(4,  ENCRYPT_DECRYPT_STRING.eNCRYPT_manual(sif));
			stmt.setString(5, sur);
			stmt.setString(6, prt);
			stmt.setInt(7, zmn);
			stmt.setString(8, esy);
			stmt.setString(9, neresi);
			stmt.setString(10, surucu);
		}
		stmt.executeUpdate();
		stmt.close();
		con.close();
	}
	@SuppressWarnings("static-access")
	public void bilgilendirme_kayit_sil(String eismi) throws SQLException, ClassNotFoundException
	{
		if (con != null && con.isClosed() == false) con.close();
		Class.forName("org.sqlite.JDBC");
		PreparedStatement stmt = null;
		con =  gLB.myBackupConnection();
		String sql = "DELETE FROM BILGILENDIRME  WHERE EMIR_ISMI = '" + eismi + "'";
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		stmt.close();
		con.close();
	}
	@SuppressWarnings("static-access")
	public void bilgilendirme_ismi_kayit(String eismi, boolean drm , boolean  gon, boolean hta , String gismi 
            , String ghes , String  alic , String  konu , String  smtp , String smtppo 
            , String kull, String sif , boolean ssl , boolean tsl  ) throws ClassNotFoundException, SQLException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException
	{
		if (con != null && con.isClosed() == false) con.close();
		Class.forName("org.sqlite.JDBC");
		PreparedStatement stmt = null;
		con = gLB.myBackupConnection();
		String sql = "INSERT INTO BILGILENDIRME (EMIR_ISMI,DURUM,GONDERILDIGINDE,HATA_DURUMUNDA,GON_ISIM,GON_HESAP,"
				+ "ALICI,KONU,SMTP,SMTP_PORT,KULLANICI,SIFRE,SSL,TSL) ";
		sql += "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		{
			stmt = con.prepareStatement(sql);
			stmt.setString(1, eismi);
			stmt.setBoolean(2, drm);
			stmt.setBoolean(3, gon);
			stmt.setBoolean(4,  hta);
			stmt.setString(5, gismi);
			stmt.setString(6, ghes);
			stmt.setString(7, alic);
			stmt.setString(8, konu);
			stmt.setString(9, smtp);
			stmt.setString(10, smtppo);
			stmt.setString(11,kull );
			stmt.setBytes(12,  ENCRYPT_DECRYPT_STRING.eNCRYPT_manual(sif));
			stmt.setBoolean(13,ssl);
			stmt.setBoolean(14,tsl);
		}
		stmt.executeUpdate();
		stmt.close();
		con.close();
	}
	@SuppressWarnings("static-access")
	public void durum_kayit_durum(String eismi ,boolean drm ) throws ClassNotFoundException, SQLException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException
	{
		if (con != null && con.isClosed() == false) con.close();
		Class.forName("org.sqlite.JDBC");
		PreparedStatement stmt = null;
		con = gLB.myBackupConnection();
		String sql = "UPDATE EMIRLER SET DURUM = ?  WHERE EMIR_ISMI =  '" + eismi + "'";
		stmt = con.prepareStatement(sql);
		stmt.setBoolean(1,drm);
		stmt.executeUpdate();
		stmt.close();
		con.close();
	}
	@SuppressWarnings("static-access")
	public void dump_sil(String eismi) throws SQLException, ClassNotFoundException
	{
		if (con != null && con.isClosed() == false) con.close();
		Class.forName("org.sqlite.JDBC");
		PreparedStatement stmt = null;
		con =  gLB.myBackupConnection();
		String sql = "DELETE FROM DUMP_YERI  WHERE EMIR_ISMI =  ? ";
		stmt = con.prepareStatement(sql);
		stmt.setString(1,eismi);
		stmt.executeUpdate();
		stmt.close();
		con.close();
	}
	@SuppressWarnings("static-access")
	public void dump_kayit(String eismi , String dump ) throws ClassNotFoundException, SQLException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException
	{
		if (con != null && con.isClosed() == false) con.close();
		Class.forName("org.sqlite.JDBC");
		PreparedStatement stmt = null;
		con = gLB.myBackupConnection();
		String sql = "INSERT INTO DUMP_YERI  (EMIR_ISMI,DUMP_PATH) ";
		sql += "VALUES (?,?)";
		{
			stmt = con.prepareStatement(sql);
			stmt.setString(1, eismi);
			stmt.setString(2, dump);
		}
		stmt.executeUpdate();
		stmt.close();
		con.close();
	}
	public void msBackup(String port , String inst , String kull , String sifre, String dbismi, String dbyer) throws SQLException, ClassNotFoundException
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
		String sql =  "BACKUP DATABASE [" + dbismi + "] TO  DISK = N'C:\\OBS_SISTEM\\BACKUP\\" + dbyer + ".bak' "
            +    " WITH NOFORMAT , NOINIT , NAME = N'" + dbismi + "' , SKIP , NOREWIND " 
              +   " ,NOUNLOAD , STATS = 10 " ;
		PreparedStatement stmt = conn.prepareStatement(sql);
		 stmt.executeUpdate();
		stmt.close();
		conn.close();
	}
	public void Logla(String emirADI , String mesaj) throws ClassNotFoundException, SQLException {
		try
		{
			Class.forName("org.sqlite.JDBC");   //
			Connection sQLITEconn = DriverManager.getConnection("jdbc:sqlite:" + GLOBAL.BACKUP_SURUCU + GLOBAL.BACKUP_LOG_DOSYA   ) ;
			PreparedStatement stmt = null;
			String sql =  "INSERT INTO LOG (EMIR_ISMI,TARIH ,MESAJ) " +
									"VALUES (?,?,?)";
			stmt = sQLITEconn.prepareStatement(sql);
			stmt.setString(1,emirADI);
			stmt.setDate(2,(java.sql.Date) new java.sql.Date(Calendar.getInstance().getTime().getTime()) );
			stmt.setString(3, mesaj);
				stmt.executeUpdate();
			stmt.close();
			sQLITEconn.close();
		}
		catch (Exception e){ 
			JOptionPane.showMessageDialog(null, e.getMessage(), "OBS SISTEM", JOptionPane.PLAIN_MESSAGE);
		}
	}
}
