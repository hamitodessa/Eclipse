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

public class GUNLUK_MYSQL implements IGUNLUK{

	static Connection con = null;
	static Statement stmt = null;
	
	public void baglan() throws SQLException
	{
		String cumle = "jdbc:sqlserver://" + BAGLAN.gunDizin.cONN_STR + ";";
	    con = DriverManager.getConnection(cumle,BAGLAN.gunDizin.kULLANICI,BAGLAN.gunDizin.sIFRESI);
	}
	@Override
	public void gUN_SIFIR_L(String kod, String dizin_yeri, String dizin, String fir_adi, String ins,  String kull, String sifre,String port) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
 		con = null;  
 		String cumle = "";
 		cumle = "jdbc:mysql://localhost:" + port ;  // SERVER BAGLANDI
		con = DriverManager.getConnection(cumle,kull,sifre);
		String VERITABANI = "ok_gun" + kod;
		stmt = null;
		String sql =null;
		sql = "CREATE DATABASE " + VERITABANI ;
		stmt = con.createStatement();  
		stmt.execute(sql);
		cumle = "jdbc:mysql://localhost/" +VERITABANI ;
		con = DriverManager.getConnection(cumle,kull,sifre);  // DATABASE BAGLANDI
		create_table(fir_adi);
		//
		sql = "CREATE DATABASE " + VERITABANI + "_log" ;
		stmt = con.createStatement();  
		stmt.execute(sql);
		cumle = "jdbc:mysql://localhost/" +VERITABANI + "_log" ;
		con = DriverManager.getConnection(cumle,kull,sifre);
		create_table_log();
		//  VERITABANI DOSYASI ILK ACILIS
		ILOGER_KAYIT  vTLOG =  new DOSYA_MYSQL();
		vTLOG.Logla("Dosya Olusturuldu" ,"", BAGLAN_LOG.gunLogDizin);
		vTLOG.Logla("Firma Adi:" + fir_adi ,"", BAGLAN_LOG.gunLogDizin);
		//SQLITE LOG DOSYASI OLUSTUR
		if (GLOBAL.dos_kontrol(GLOBAL.LOG_SURUCU + VERITABANI + "_mYSQL"+ ".DB") == false)
		{
			String dsy = GLOBAL.LOG_SURUCU + VERITABANI + "_mYSQL"+ ".DB" ;
			GLOBAL.create_table_log(dsy,fir_adi,BAGLAN_LOG.gunLogDizin);
		}
		//  TEXT DOSYASI ILK ACILIS
		ILOGER_KAYIT  tEXLOG = new TXT_LOG();
		 tEXLOG.Logla("Dosya Olusturuldu" ,"", BAGLAN_LOG.gunLogDizin);
		 tEXLOG.Logla("Firma Adi:" + fir_adi ,"", BAGLAN_LOG.gunLogDizin);
		//
		stmt.close();
		con.close();
	}

	@Override
	public void gUN_SIFIR_S(String server, String ins, String kull, String sifre, String kod, String dizin_yeri,
			String dizin, String fir_adi) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
 		con = null;  
 		 String VERITABANI = "ok_gun" + kod;
 		String cumle = "";
		stmt = null;
		String sql =null;
		cumle = "jdbc:mysql://" + server ;
		con = DriverManager.getConnection(cumle,kull,sifre);
		sql = "CREATE DATABASE " + VERITABANI ;
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		cumle = "jdbc:mysql://" + server + "/" + VERITABANI ;
		con = DriverManager.getConnection(cumle,kull,sifre);
		create_table(fir_adi);
		//
		sql = "CREATE DATABASE " + VERITABANI + "_log" ;
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		cumle = "jdbc:mysql://" + server + "/" + VERITABANI + "_log" ;
		con = DriverManager.getConnection(cumle,kull,sifre);
		create_table_log();
		//  VERITABANI DOSYASI ILK ACILIS
		ILOGER_KAYIT  vTLOG =  new DOSYA_MYSQL();
		vTLOG.Logla("Dosya Olusturuldu" ,"", BAGLAN_LOG.gunLogDizin);
		vTLOG.Logla("Firma Adi:" + fir_adi ,"", BAGLAN_LOG.gunLogDizin);
		//SQLITE LOG DOSYASI OLUSTUR
		if (GLOBAL.dos_kontrol(  GLOBAL.LOG_SURUCU + GLOBAL.char_degis( BAGLAN_LOG.gunLogDizin.mODUL)) == false)
		{
			String dsy =  GLOBAL.LOG_SURUCU + GLOBAL.char_degis(BAGLAN_LOG.gunLogDizin.mODUL) ;
			GLOBAL.create_table_log(dsy,fir_adi,BAGLAN_LOG.gunLogDizin);
		}
		//  TEXT DOSYASI ILK ACILIS
		ILOGER_KAYIT  tEXLOG = new TXT_LOG();
		 tEXLOG.Logla("Dosya Olusturuldu" ,"", BAGLAN_LOG.gunLogDizin);
		 tEXLOG.Logla("Firma Adi:" + fir_adi ,"", BAGLAN_LOG.gunLogDizin);
		//
		stmt.close();
		con.close();
	}

	@Override
	public void create_table(String fir_adi) throws SQLException {
		String sql = null;
        sql = "CREATE TABLE GUNLUK ([GID] [int] IDENTITY(1,1) NOT NULL , BASL_TARIH DATE , BASL_SAAT nvarchar(5), BIT_TARIH DATE , BIT_SAAT nvarchar(5),TEKRARLA bit,ISIM nvarchar(20),GOREV nvarchar(20),MESAJ nvarchar(100) ,[USER] nvarchar(15) NULL)" ;  
        stmt = con.createStatement();  
        stmt.executeUpdate(sql);
        sql= "CREATE TABLE OZEL(OZID int identity(1,1) CONSTRAINT PKeyOZID PRIMARY KEY,YONETICI nvarchar(25), YON_SIFRE nvarchar(15) , FIRMA_ADI nvarchar(50))";
        stmt = con.createStatement();  
        stmt.executeUpdate(sql);
        sql = "CREATE TABLE YETKILER(YETID int identity(1,1) CONSTRAINT PKeyYETID PRIMARY KEY,KULLANICI nvarchar(25), HESAP nvarchar(12), TAM_YETKI bit, GORUNTU bit )";
        stmt = con.createStatement();  
        stmt.executeUpdate(sql);
        // ***************OZEL NO YAZ ************
        sql = "INSERT INTO  OZEL(YONETICI,YON_SIFRE,FIRMA_ADI) VALUES ('" + GLOBAL.KULL_ADI  + "','12345' , '" + fir_adi + "')";
        stmt = con.createStatement();  
        stmt.executeUpdate(sql);
	}

	@Override
	public String gun_firma_adi() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
	 	con = null;
		ResultSet	rss = null;
		String cumle = "jdbc:mysql://" + BAGLAN.gunDizin.cONN_STR + ";";
        con = DriverManager.getConnection(cumle, BAGLAN.gunDizin.kULLANICI, BAGLAN.gunDizin.sIFRESI);
   	    PreparedStatement stmt = con.prepareStatement("SELECT *  FROM OZEL ");
		rss = stmt.executeQuery();
		rss.next();
		int count=0;
		count = rss.getRow();
		String result;
		if (count  != 0) 
		{
			result = rss.getString("FIRMA_ADI");
		}
		else
		{
			result = "";
		}
		return result;	
	}
	@Override
	public void create_table_log() throws SQLException {
		String sql = "" ;
		sql = "CREATE TABLE  `loglama` ("
				+ "  `TARIH` DATETIME NOT NULL,"
				+ "  `MESAJ` VARCHAR(100) NULL,"
				+ "  `EVRAK` VARCHAR(15) NULL,"
				+ "  `USER_NAME` VARCHAR(15) NULL,"
				+ "  INDEX `IX_LOGLAMA` (`TARIH` ASC, `USER_NAME` ASC) VISIBLE);";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
	}
	@Override
	public void gorev_kayit(Gunluk_Bilgi gbilgi) 
	{
		// TODO Auto-generated method stub
		
	}
	@Override
	public void gunluk_kayit(Gunluk_Bilgi gbilgi) 
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void gorev_sil(int id) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public int gid_ogren(Gunluk_Bilgi gbilgi)
	{
		return 0;
		
	}
	@Override
	public ResultSet gorev_oku(Gunluk_Bilgi gbilgi) {
		return null;
		// TODO Auto-generated method stub
		
	}
	@Override
	public ResultSet isim_oku() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet gorev_oku_tarih(Gunluk_Bilgi gbilgi) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public int gorev_bul(Gunluk_Bilgi gbilgi) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

}
