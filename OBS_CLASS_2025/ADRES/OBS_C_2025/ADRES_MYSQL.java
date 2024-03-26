package OBS_C_2025;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import LOGER_KAYIT.DOSYA_MYSQL;
import LOGER_KAYIT.ILOGER_KAYIT;
import LOGER_KAYIT.TXT_LOG;
@SuppressWarnings("static-access")
public class ADRES_MYSQL implements IADRES {

	static Connection con = null;
	static Statement stmt = null;

	public void baglan() throws SQLException
	{
		String cumle = "jdbc:mysql://" + BAGLAN.adrDizin.cONN_STR ;
		DriverManager.setLoginTimeout(0);
		con = DriverManager.getConnection(cumle,BAGLAN.adrDizin.kULLANICI,BAGLAN.adrDizin.sIFRESI);
	}
	@Override
	public void aDR_SIF_L(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = null;  
		String cumle = "";
		cumle = "jdbc:mysql://localhost:" + sbilgi.getPort() ;
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		String VERITABANI = "ok_adr" + sbilgi.getKod();
		stmt = null;
		String sql =null;
		sql = "CREATE DATABASE " + VERITABANI ;
		stmt = con.createStatement();  
		stmt.execute(sql);
		cumle = "jdbc:mysql://localhost/" +VERITABANI ;
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		create_table(sbilgi.getFir_adi());
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
		vTLOG.Logla(lBILGI, BAGLAN_LOG.adrLogDizin);
		lBILGI.setmESAJ("Firma Adi:" + sbilgi.getFir_adi());
		vTLOG.Logla(lBILGI, BAGLAN_LOG.adrLogDizin);

		//SQLITE LOG DOSYASI OLUSTUR
		if (GLOBAL.dos_kontrol(GLOBAL.LOG_SURUCU + VERITABANI + "_mYSQL" + ".DB") == false)
		{
			String dsy =GLOBAL.LOG_SURUCU + VERITABANI + "_mYSQL"+ ".DB" ;
			GLOBAL.create_table_log(dsy ,sbilgi.getFir_adi(),BAGLAN_LOG.adrLogDizin);
		}
		//  TEXT DOSYASI ILK ACILIS
		ILOGER_KAYIT  tEXLOG = new TXT_LOG();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		tEXLOG.Logla(lBILGI, BAGLAN_LOG.adrLogDizin);
		lBILGI.setmESAJ("Firma Adi:" + sbilgi.getFir_adi());
		tEXLOG.Logla(lBILGI, BAGLAN_LOG.adrLogDizin);
		//
		stmt.close();
		con.close();
	}

	@Override
	public void aDR_SIFIR_S(Server_Bilgi sbilgi)
			throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = null;  
		String VERITABANI = "ok_adr" + sbilgi.getKod();
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
		create_table(sbilgi.getFir_adi());
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
		vTLOG.Logla(lBILGI, BAGLAN_LOG.adrLogDizin);
		lBILGI.setmESAJ("Firma Adi:" + sbilgi.getFir_adi());
		vTLOG.Logla(lBILGI, BAGLAN_LOG.adrLogDizin);

		//SQLITE LOG DOSYASI OLUSTUR
		if (GLOBAL.dos_kontrol( GLOBAL.LOG_SURUCU + GLOBAL.char_degis(  BAGLAN_LOG.adrLogDizin.mODUL) ) == false)
		{
			String dsy =  GLOBAL.LOG_SURUCU +GLOBAL.char_degis( BAGLAN_LOG.adrLogDizin.mODUL) ;
			@SuppressWarnings("unused")
			Connection sQLITEconn = DriverManager.getConnection("jdbc:sqlite:" + dsy  ) ;
			GLOBAL.create_table_log(dsy ,sbilgi.getFir_adi(),BAGLAN_LOG.adrLogDizin);
		}
		//  TEXT DOSYASI ILK ACILIS
		ILOGER_KAYIT  tEXLOG = new TXT_LOG();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		tEXLOG.Logla(lBILGI, BAGLAN_LOG.adrLogDizin);
		lBILGI.setmESAJ("Firma Adi:" + sbilgi.getFir_adi());
		tEXLOG.Logla(lBILGI, BAGLAN_LOG.adrLogDizin);
		//
		stmt.close();
		con.close();
	}

	@Override
	public void create_table(String fir_adi) throws SQLException {
		String sql = null;
		sql = "CREATE TABLE `Adres`( "
				+ " `M_Kodu`  VARCHAR (12) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NOT NULL, "
				+ " `Adi`  VARCHAR (50) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci NULL,"
				+ " `Adres_1`  VARCHAR (50) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ " `Adres_2`  VARCHAR (50) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ " `Semt`  VARCHAR (25) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ " `Sehir`  VARCHAR (25) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ " `Posta_Kodu`  VARCHAR (10) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ " `Vergi_Dairesi`  VARCHAR (25) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ " `Vergi_No`  VARCHAR (15) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ " `Fax`  VARCHAR (25) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ " `Tel_1`  VARCHAR (25) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ " `Tel_2`  VARCHAR (25) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ " `Tel_3`  VARCHAR (25) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ " `Ozel`  VARCHAR (30) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ " `Yetkili`  VARCHAR (30) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ " `E_Mail`  VARCHAR (50) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ " `Not_1`  VARCHAR (30) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ " `Not_2`  VARCHAR (30) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ " `Not_3`  VARCHAR (30) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ " `Aciklama`  VARCHAR (50) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ " `Sms_Gonder` TINYINT NULL,"
				+ " `Mail_Gonder` TINYINT NULL,"
				+ " `Ozel_Kod_1`  VARCHAR (15) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ " `Ozel_Kod_2`  VARCHAR (15) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ " `Web`  VARCHAR (50) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ " `USER`  VARCHAR (15) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci  NULL,"
				+ " `Resim` MEDIUMBLOB NULL,"
				+ "  PRIMARY KEY (`M_Kodu`),"
				+ "  UNIQUE INDEX `M_Kodu_UNIQUE` (`M_Kodu` ASC) VISIBLE,"
				+ "  INDEX `IX_Adres` (`M_Kodu` ASC , `Adi` ASC) VISIBLE);";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE `OZEL` ("
				+ "  `OZID` INTEGER AUTO_INCREMENT PRIMARY KEY,"
				+ "  `YONETICI` VARCHAR(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci NULL,"
				+ "  `YON_SIFRE` VARCHAR(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci NULL,"
				+ "  `FIRMA_ADI` VARCHAR(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci NULL);";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE `YETKILER`( "
				+ " `YETID` INTEGER AUTO_INCREMENT PRIMARY KEY,"
				+ "`KULLANICI` VARCHAR(25) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci NULL,"
				+ "`KARTON` VARCHAR(5) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci NULL,"
				+ "`TAM_YETKI` TINYINT NULL,"
				+ "`GORUNTU` TINYINT NULL);";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		// ***************OZEL NO YAZ ************
		sql = "INSERT INTO  `OZEL` (`YONETICI`,`YON_SIFRE`,`FIRMA_ADI`) VALUES ('" + GLOBAL.KULL_ADI  + "','12345' , '" + fir_adi + "')";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
	}

	@Override
	public String adr_firma_adi() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String cumle = "jdbc:mysql://" +  BAGLAN.adrDizin.cONN_STR ;
		con = DriverManager.getConnection(cumle, BAGLAN.adrDizin.kULLANICI, BAGLAN.adrDizin.sIFRESI);
		PreparedStatement stmt = con.prepareStatement("SELECT *  FROM OZEL ");
		rss = stmt.executeQuery();
		rss.next();
		int count=0;
		count = rss.getRow();
		String result;
		result = count  != 0 ? rss.getString("FIRMA_ADI") : "" ;
		return result;	
	}

	@Override
	public void adr_firma_adi_kayit(String fadi) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql = "UPDATE OZEL SET FIRMA_ADI = N'" + fadi + "'";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}

	@Override
	public ResultSet adres(String sira, String arama) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql =" SELECT M_Kodu,Adi,Adres_1,Adres_2,Semt,Sehir,Vergi_Dairesi ," +
				" Vergi_No, Fax,Tel_1,Tel_2,Tel_3,Ozel,Yetkili,E_Mail,Not_1,Not_2,Not_3 ,Aciklama,Sms_Gonder,Mail_Gonder,Ozel_Kod_1,Ozel_Kod_2" +
				" ,Web ,Posta_Kodu ,Resim,ID" +
				" FROM Adres " +
				arama +
				" ORDER by " + sira;
		kONTROL();
		Statement stmt = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		return rss ;
	}
	@Override
	public String kod_ismi(String kodu) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement("SELECT Adi  FROM Adres WHERE M_Kodu =N'" + kodu + "'");
		rss = stmt.executeQuery();
		rss.next();
		int count=0;
		count = rss.getRow();
		String result="";
		if (count  != 0)
			result = rss.getString("Adi");
		else
			result = "";
		return result;	
	}
	@Override
	public void adres_kayit(ADRESS_DEGISKENLER aDEGIS) throws ClassNotFoundException, SQLException, IOException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql  = "INSERT INTO Adres (M_Kodu,Adi,Adres_1,Adres_2,Semt,Sehir,Posta_Kodu,Vergi_Dairesi,Vergi_No,Fax,Tel_1" +
				" ,Tel_2,Tel_3,Ozel,Yetkili,E_Mail,Not_1,Not_2,Not_3,Aciklama,Sms_Gonder,Mail_Gonder,Ozel_Kod_1,Ozel_Kod_2,Web,USER,Resim) " +
				" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
		PreparedStatement stmt = null;
		kONTROL();
		stmt = con.prepareStatement(sql);
		stmt.setString(1, aDEGIS.kodu);
		stmt.setString(2, aDEGIS.adi);
		stmt.setString(3,  aDEGIS.adr1);
		stmt.setString(4,  aDEGIS.adr2);
		stmt.setString(5,  aDEGIS.semt);
		stmt.setString(6,  aDEGIS.sehir);
		stmt.setString(7,  aDEGIS.pkodu);
		stmt.setString(8,  aDEGIS.vd);
		stmt.setString(9,  aDEGIS.vn);
		stmt.setString(10,  aDEGIS.fax);
		stmt.setString(11,  aDEGIS.tel1);
		stmt.setString(12,  aDEGIS.tel2);
		stmt.setString(13,  aDEGIS.tel3);
		stmt.setString(14,  aDEGIS.ozel);
		stmt.setString(15,  aDEGIS.yet);
		stmt.setString(16,  aDEGIS.e_ma);
		stmt.setString(17,  aDEGIS.n1);
		stmt.setString(18,  aDEGIS.n2);
		stmt.setString(19,  aDEGIS.n3);
		stmt.setString(20,  aDEGIS.acik);
		stmt.setBoolean(21,  aDEGIS.sms);
		stmt.setBoolean(22,  aDEGIS.mailg);
		stmt.setString(23,  aDEGIS.ok1);
		stmt.setString(24,  aDEGIS.ok2);
		stmt.setString(25,  aDEGIS.web);
		stmt.setString(26,  aDEGIS.usr);
		if (   aDEGIS.resim != null)
		{
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			for (int readNum; (readNum =  aDEGIS.resim.read(buf)) != -1;)
				bos.write(buf, 0, readNum);
			byte[] bytes = bos.toByteArray();
			stmt.setBytes(27,bytes);
		}
		else
			stmt.setBytes(27,null);
		stmt.executeUpdate();
		stmt.close();
	}
	public void sil(String id) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql = " DELETE  FROM Adres WHERE ID = '" + id + "'"  ;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
	public ResultSet adr_hpl() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql = "SELECT M_Kodu,Adi  FROM Adres  ORDER BY M_Kodu";
		kONTROL();
		PreparedStatement  stmt = con.prepareStatement (sql);
		rss = stmt.executeQuery();
		return rss;
	}
	public ResultSet sms_adr_hpl(String nerden) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql = "SELECT " + nerden + " ,Adi ,'' AS GRUP ,'' AS DURUM ,M_Kodu ," + 
				" '' as GON_ZAMANI,[USER] FROM Adres   WHERE Mail_Gonder = 'TRUE' ORDER BY M_Kodu ";
		kONTROL();
		PreparedStatement  stmt = con.prepareStatement (sql);
		rss = stmt.executeQuery();
		return rss;	 
	}
	public ResultSet gdy_oku(String kod) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql = " SELECT M_Kodu,Adi,Adres_1,Adres_2,Semt,Sehir,Vergi_Dairesi ," +
				" Vergi_No, Fax,Tel_1,Tel_2,Ozel,Yetkili,E_Mail,Not_1,Not_2,Not_3 " +
				" FROM Adres " +
				" WHERE M_Kodu = N'" + kod + "'";
		kONTROL();
		PreparedStatement  stmt = con.prepareStatement (sql);
		rss = stmt.executeQuery(sql);
		return rss;
	}
	public String[] adres_oku (String kodu) throws ClassNotFoundException, SQLException
	{
		String[] bilgi = new String[6];
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql = " SELECT M_Kodu,Adi,Adres_1,Adres_2,Semt,Sehir,Vergi_Dairesi ," +
				" Vergi_No, Fax,Tel_1,Tel_2,Ozel,Yetkili,E_Mail,Not_1,Not_2,Not_3 " +
				" FROM Adres " +
				" WHERE M_Kodu = N'" + kodu + "'" ;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		if (!rss.isBeforeFirst() ) {  
		}
		else
		{
			rss.next();
			bilgi[0] = rss.getString("Adres_1");
			bilgi[1] = rss.getString("Adres_2");
			bilgi[2] = rss.getString("Semt");
			bilgi[3] = rss.getString("Sehir");
			bilgi[4] = rss.getString("Vergi_Dairesi");
			bilgi[5] = rss.getString("Vergi_No");
		}
		return bilgi;
	}
	@Override
	public void create_table_log() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql = "" ;
		sql = "CREATE TABLE  `loglama` ("
				+ "  `TARIH` DATETIME NOT NULL,"
				+ "  `MESAJ` VARCHAR(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci NULL,"
				+ "  `EVRAK` VARCHAR(15) NULL,"
				+ "  `USER_NAME` VARCHAR(15) CHARACTER SET utf8mb4 COLLATE utf8mb4_turkish_ci NULL,"
				+ "  INDEX `IX_LOGLAMA` (`TARIH` ASC, `USER_NAME` ASC) VISIBLE);";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
	}
	@Override
	public ResultSet adr_etiket(String siralama) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql = "SELECT CAST(0 AS UNSIGNED),Adi , Adres_1 ,Adres_2 ,Tel_1,Semt ,Sehir  FROM Adres  ORDER BY " + siralama +"";
		kONTROL();
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		rss = stmt.executeQuery(sql);
		return rss;
	}
	@Override
	public ResultSet adr_etiket_arama(String arama) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql = "SELECT Adi , Adres_1 ,Adres_2 , Tel_1,Semt ,Sehir  FROM Adres "
				+ " WHERE Adi Like N'%" + arama + "%'";
		kONTROL();
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		rss = stmt.executeQuery(sql);
		return rss;
	}
	private void kONTROL() throws SQLException, ClassNotFoundException
	{
		if(con.isClosed())    
			baglan();
	}
	@Override
	public ResultSet kod_kontrol(String arama) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql = " SELECT M_Kodu , Adi "
				 	+ " FROM Adres "
				 	+ " WHERE Adi Like N'" + arama + "%'"
				 	+ " ORDER BY M_Kodu DESC ";
		kONTROL();
		Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
		rss = stmt.executeQuery(sql);
		return rss;
	}
	@Override
	public ResultSet adr_etiket_arama_kod(String arama) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet	rss = null;
		String sql = "SELECT Adi , Adres_1 ,Adres_2 , Tel_1,Semt ,Sehir  FROM Adres "
				+ " WHERE M_Kodu Like N'" + arama + "%'";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}
}
