package OBS_C_2025;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import LOGER_KAYIT.DOSYA_MYSQL;
import LOGER_KAYIT.ILOGER_KAYIT;
import LOGER_KAYIT.TXT_LOG;

public class SMS_MYSQL implements ISMS{

	static Connection con = null;
	static Statement stmt = null;

	public void baglan() throws SQLException
	{
		String cumle = "jdbc:mysql://" + BAGLAN.smsDizin.cONN_STR;
		//DriverManager.setLoginTimeout(0);
		con = DriverManager.getConnection(cumle,BAGLAN.smsDizin.kULLANICI,BAGLAN.smsDizin.sIFRESI);
	}
	@Override
	public void sMS_SIFIR_L(Server_Bilgi sbilgi)
			throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = null;  
		String cumle = "";
		cumle = "jdbc:mysql://localhost:" + sbilgi.getPort() ;
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		String VERITABANI = "ok_sms" + sbilgi.getKod();
		stmt = null;
		String sql =null;
		sql = "CREATE DATABASE " + VERITABANI ;
		stmt = con.createStatement();  
		stmt.execute(sql);
		cumle = "jdbc:mysql://localhost/" +VERITABANI ;
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		create_table();
		//
		sql = "CREATE DATABASE " + VERITABANI + "_log" ;
		stmt = con.createStatement();  
		stmt.execute(sql);
		cumle = "jdbc:mysql://localhost/" +VERITABANI + "_log" ;
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		create_table_log();
		//  VERITABANI DOSYASI ILK ACILIS
		ILOGER_KAYIT  vTLOG =  new DOSYA_MYSQL();
		lOG_BILGI lBILGI = new lOG_BILGI();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		lBILGI.seteVRAK("");
		vTLOG.Logla(lBILGI, BAGLAN_LOG.smsLogDizin);
		//SQLITE LOG DOSYASI OLUSTUR
		if (GLOBAL.dos_kontrol(GLOBAL.LOG_SURUCU + VERITABANI + "_mYSQL" + ".DB") == false)
		{
			String dsy = GLOBAL.LOG_SURUCU + VERITABANI + "_mYSQL"+ ".DB" ;
			GLOBAL.create_table_log(dsy ,"",BAGLAN_LOG.smsLogDizin);
		}
		//  TEXT DOSYASI ILK ACILIS
		ILOGER_KAYIT  tEXLOG = new TXT_LOG();
		tEXLOG.Logla(lBILGI, BAGLAN_LOG.smsLogDizin);
		//
		stmt.close();
		con.close();
	}

	@Override
	public void sMS_SIFIR_S(Server_Bilgi sbilgi)
			throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = null;  
		String VERITABANI = "ok_sms" + sbilgi.getKod();
		String cumle = "";
		stmt = null;
		String sql =null;
		cumle = "jdbc:mysql://" + sbilgi.getServer() ;
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		sql = "CREATE DATABASE " + VERITABANI ;
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		cumle = "jdbc:mysql://" + sbilgi.getServer() + "/" + VERITABANI ;
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		create_table();
		//
		sql = "CREATE DATABASE " + VERITABANI + "_log" ;
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		cumle = "jdbc:mysql://" + sbilgi.getServer() + "/" + VERITABANI + "_log" ;
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		create_table_log();
	//  VERITABANI DOSYASI ILK ACILIS
		ILOGER_KAYIT  vTLOG =  new DOSYA_MYSQL();
		lOG_BILGI lBILGI = new lOG_BILGI();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		lBILGI.seteVRAK("");
		vTLOG.Logla(lBILGI, BAGLAN_LOG.smsLogDizin);
		//SQLITE LOG DOSYASI OLUSTUR
		if (GLOBAL.dos_kontrol(  GLOBAL.LOG_SURUCU + GLOBAL.char_degis(BAGLAN_LOG.smsLogDizin.mODUL) ) == false)
		{
			String dsy =  GLOBAL.LOG_SURUCU + GLOBAL.char_degis( BAGLAN_LOG.smsLogDizin.mODUL) ;
			GLOBAL.create_table_log(dsy ,"",BAGLAN_LOG.smsLogDizin);
		}
		//  TEXT DOSYASI ILK ACILIS
		ILOGER_KAYIT  tEXLOG = new TXT_LOG();
		tEXLOG.Logla(lBILGI, BAGLAN_LOG.smsLogDizin);
		//
		stmt.close();
		con.close();
	}
	@Override
	public void create_table() throws SQLException {
		String sql = null;
		sql = "CREATE TABLE `SMS_HESAP`( "
				+ " `TEL_NO` nvarchar (15) NOT NULL  PRIMARY KEY ,"
				+ " `UNVAN`  varchar (50) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ " `GRUP`  varchar (15) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ " `KODU`  varchar (12) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ " `DURUM`  TINYINT NULL,"
				+ " `USER_NAME`  varchar (15) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ "  INDEX `IDX_SMS_HESAP` (`TEL_NO` ASC, `UNVAN` ASC,  `GRUP` ASC) VISIBLE)";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE `SMS_BILGILERI`( "
				+ "`SID` INTEGER AUTO_INCREMENT PRIMARY KEY  NOT NULL,"
				+ " `USER_NAME`  varchar (15) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ " `TARIH` datetime NULL,"
				+ " `MOBILE`  varchar (12) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ " `MESAJ`  varchar (140) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ " `HESAP`  varchar (12) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ " `UNVAN`  varchar (50) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ " INDEX `IDX_SMS_BILGILERI` (`TARIH` ASC,   `MOBILE` ASC,  `HESAP` ASC,  `UNVAN` ASC) VISIBLE)";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE `MAIL_HESAP`( "
				+ "  `MAIL`  varchar (50) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  PRIMARY KEY  NOT NULL ,"
				+ "  `UNVAN` varchar (50) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ "  `GRUP`  varchar (15) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ "  `KODU`  varchar (12) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ "  `DURUM` TINYINT NULL,"
				+ "  `USER_NAME` varchar (15) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ "  INDEX `IDX_MAIL_HESAP` ( `MAIL` ASC,  `UNVAN` ASC, `GRUP` ASC, `KODU` ASC) VISIBLE)";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE `MAIL_BILGILERI`( "
				+ "  `MID` INTEGER AUTO_INCREMENT PRIMARY KEY,"
				+ "  `TARIH` datetime NULL,"
				+ "  `MAIL`  varchar (50) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ "  `KONU`  varchar (50) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ "  `MESAJ`  varchar (300) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ "  `HESAP`  varchar (12) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ "  `UNVAN`  varchar (50) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ "  `GONDEREN`  varchar (50) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ "  `USER_NAME`  varchar (15) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ "  INDEX `IDX_MAIL_HESAP` ( `TARIH` ASC ,`MAIL` ASC, `HESAP` ASC, `UNVAN` ASC, `GONDEREN` ASC, `KONU` ASC) VISIBLE)";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE `GIDEN_RAPOR` ( EID INTEGER AUTO_INCREMENT PRIMARY KEY , "
				+ " `TARIH` DATETIME ,"
				+ " `KONU` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci ,"
				+ " `RAPOR` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci ,"
				+ " `ALICI` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci ,"
				+ " `GONDEREN` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci ,"
				+ " `ACIKLAMA` varchar(300) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci ,"
				+ "  `USER_NAME` varchar(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci ) ";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
	}
	public ResultSet mail_giris_bak() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql = "SELECT *  FROM MAIL_HESAP ORDER BY UNVAN ";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}
	public boolean kod_ismi(String kodu) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement("SELECT MAIL  FROM MAIL_HESAP WHERE MAIL =N'" + kodu + "'");
		rss = stmt.executeQuery();
		rss.next();
		int count=0;
		count = rss.getRow();
		return count  != 0;	
	}
	public void mail_giris_sil(String mail) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql = "DELETE  FROM MAIL_HESAP WHERE MAIL ='" + mail + "'";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
	public void mail_giris_yaz(String mail , String unv ,String grpkod ,String kod,boolean drm , String unm ) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql  = "INSERT INTO MAIL_HESAP (MAIL,UNVAN,GRUP,KODU,DURUM,USER_NAME) " +
				" VALUES (?,?,?,?,?,?)" ;
		PreparedStatement stmt = null;
		kONTROL();
		stmt = con.prepareStatement(sql);
		stmt.setString(1, mail);
		stmt.setString(2,unv);
		stmt.setString(3, grpkod);
		stmt.setString(4, kod);
		stmt.setBoolean(5, drm);
		stmt.setString(6, unm);
		stmt.executeUpdate();
		stmt.close();
	}
	public ResultSet mail_grup_bak() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql = "SELECT DISTINCT GRUP  FROM MAIL_HESAP ORDER BY GRUP ";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}
	public ResultSet mail_alici_doldur() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql = "SELECT MAIL,UNVAN,GRUP,DURUM,KODU,'' AS GON_ZAMANI,USER_NAME AS  USER   FROM MAIL_HESAP ORDER BY UNVAN ";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}
	public ResultSet mail_giden_bak(String user) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql = "SELECT DATE_FORMAT(TARIH, '%d.%m.%Y %H:%m:%s') as TARIH,MAIL,KONU,MESAJ,HESAP,UNVAN,USER_NAME AS  USER   FROM MAIL_BILGILERI ";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}
	public void giden_rapor_yaz(String uname,String tar ,String msj,String mail,String hsp, String unv,String gond,String konu) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql = "INSERT INTO MAIL_BILGILERI (USER_NAME,TARIH,MAIL,KONU,MESAJ,HESAP,UNVAN,GONDEREN) " +
				"VALUES (?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = null;
		kONTROL();
		stmt = con.prepareStatement(sql);
		stmt.setString(1,  uname);
		stmt.setString(2,  tar);
		stmt.setString(3, mail);
		stmt.setString(4, konu);
		stmt.setString(5, msj);
		stmt.setString(6, hsp);
		stmt.setString(7, unv);
		stmt.setString(8, gond);
		stmt.executeUpdate();
	}
	public ResultSet sms_giris_bak() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql =  "SELECT *  FROM SMS_HESAP ORDER BY UNVAN ";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}
	public ResultSet sms_grup_bak() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql = "SELECT DISTINCT GRUP  FROM SMS_HESAP ORDER BY GRUP ";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}
	public ResultSet sms_giden_bak(String user) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql = "SELECT DATE_FORMAT(TARIH, '%d.%m.%Y %H:%m:%s') as TARIH,MOBILE,MESAJ,HESAP,UNVAN,USER_NAME AS  USER   FROM SMS_BILGILERI ";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}
	public void sms_giris_sil(String tel) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql = "DELETE  FROM SMS_HESAP WHERE TEL_NO ='" + tel + "'";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
	public void sms_giris_yaz(String tel , String unv ,String grpkod ,String kod,boolean drm , String unm ) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql  = "INSERT INTO SMS_HESAP (TEL_NO,UNVAN,GRUP,KODU,DURUM,USER_NAME) " +
				" VALUES (?,?,?,?,?,?)" ;
		PreparedStatement stmt = null;
		kONTROL();
		stmt = con.prepareStatement(sql);
		stmt.setString(1, tel);
		stmt.setString(2,unv);
		stmt.setString(3, grpkod);
		stmt.setString(4, kod);
		stmt.setBoolean(5, drm);
		stmt.setString(6, unm);
		stmt.executeUpdate();
		stmt.close();
	}
	public ResultSet sms_alici_doldur() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql = "SELECT TEL_NO,UNVAN,GRUP,DURUM,KODU,'' AS GON_ZAMANI,USER_NAME as  USER   FROM SMS_HESAP ORDER BY UNVAN ";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}
	public void sms_yaz(String uname,String tar ,String msj,String mobile,String hsp, String unv) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql =  "INSERT INTO SMS_BILGILERI (USER_NAME,TARIH,MOBILE,MESAJ,HESAP,UNVAN) " +
				"VALUES (?,?,?,?,?,?)";
		PreparedStatement stmt = null;
		kONTROL();
		stmt = con.prepareStatement(sql);
		stmt.setString(1,  uname);
		stmt.setString(2,  tar);
		stmt.setString(3, mobile);
		stmt.setString(4, msj);
		stmt.setString(5, hsp);
		stmt.setString(6, unv);
		stmt.executeUpdate();
	}
	@Override
	public void create_table_log() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql = "" ;
		sql = "CREATE TABLE  `loglama` ("
				+ "  `TARIH` DATETIME NOT NULL,"
				+ "  `MESAJ` VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ "  `EVRAK` VARCHAR(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ "  `USER_NAME` VARCHAR(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ "  INDEX `IX_LOGLAMA` (`TARIH` ASC, `USER_NAME` ASC) VISIBLE);";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
	}
	private void kONTROL() throws SQLException, ClassNotFoundException
	{
		if(con.isClosed())    
			baglan();
	}
}
