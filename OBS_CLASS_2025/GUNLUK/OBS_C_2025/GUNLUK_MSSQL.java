package OBS_C_2025;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import LOGER_KAYIT.DOSYA_MSSQL;
import LOGER_KAYIT.ILOGER_KAYIT;
import LOGER_KAYIT.TXT_LOG;

public class GUNLUK_MSSQL implements IGUNLUK {

	static Connection con = null;
	static Statement stmt = null;
	
	@Override
	public void baglan() throws SQLException {
		String cumle = "jdbc:sqlserver://" + BAGLAN.gunDizin.cONN_STR + ";";
	    con = DriverManager.getConnection(cumle,BAGLAN.gunDizin.kULLANICI,BAGLAN.gunDizin.sIFRESI);
		
	}
	@Override
	public void gUN_SIFIR_L(String kod, String dizin_yeri, String dizin, String fir_adi, String ins, String kull, String sifre,String port) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		con = null;  
		String cumle = "";
		if ( ! port.toString().equals("") )
		{
			port =  ":" + port ;
		}
		cumle = "jdbc:sqlserver://localhost"+ port +";instanceName=" + ins + ";";
		con = DriverManager.getConnection(cumle,kull,sifre);
		String VERITABANI = "OK_Gun" + kod;
		stmt = null;
		String sql =null;
		if (dizin_yeri == "default")
			sql = "CREATE DATABASE [" + VERITABANI + "]";
		else
			sql = "CREATE DATABASE [" + VERITABANI + "]  ON PRIMARY " + " ( NAME = N'" + VERITABANI + "', FILENAME = N'" + dizin 	+ "\\" + VERITABANI + ".mdf  ) " + " LOG ON " + " ( NAME = N'" + VERITABANI + "_log', FILENAME = N'" + dizin + "\\" + VERITABANI + "_log.ldf' ) ";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		cumle = "jdbc:sqlserver://localhost" + port + ";instanceName=" + ins + ";database=" + VERITABANI + ";";
		con = DriverManager.getConnection(cumle,kull,sifre);
		create_table(fir_adi);
		//
		if (dizin_yeri == "default")
			sql = "CREATE DATABASE [" + VERITABANI + "_LOG" + "]";
		else
			sql = "CREATE DATABASE [" + VERITABANI + "_LOG" + "]  ON PRIMARY " + " ( NAME = N'" + VERITABANI + "_LOG" + "', FILENAME = N'" + dizin 	+ "\\" + VERITABANI + ".mdf  ) " + " LOG ON " + " ( NAME = N'" + VERITABANI + "_LOG" + "_log', FILENAME = N'" + dizin + "\\" + VERITABANI + "_LOG" + "_log.ldf' ) ";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		cumle = "jdbc:sqlserver://localhost" + port + ";instanceName=" + ins + ";database=" + VERITABANI + "_LOG" + ";";
		con = DriverManager.getConnection(cumle,kull,sifre);
		create_table_log();
		//  VERITABANI DOSYASI ILK ACILIS
		ILOGER_KAYIT  vTLOG =  new DOSYA_MSSQL();
		vTLOG.Logla("Dosya Olusturuldu" ,"", BAGLAN_LOG.gunLogDizin);
		vTLOG.Logla("Firma Adi:" + fir_adi ,"", BAGLAN_LOG.gunLogDizin);
		//SQLITE LOG DOSYASI OLUSTUR
		if (GLOBAL.dos_kontrol(GLOBAL.LOG_SURUCU + VERITABANI + "_mSSQL"+  ".DB") == false)
		{
			String dsy = GLOBAL.LOG_SURUCU + VERITABANI + "_mSSQL"+ ".DB" ;
			Connection sQLITEconn = DriverManager.getConnection("jdbc:sqlite:" +  dsy) ;
			GLOBAL.create_table_log(dsy,fir_adi,BAGLAN_LOG.gunLogDizin);
			sQLITEconn.close();
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
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		con = null;  
		String VERITABANI = "OK_Gun" + kod;
		String cumle = "";
		stmt = null;
		String sql =null;
		cumle = "jdbc:sqlserver://" + server + ";instanceName=" + ins + ";";
		con = DriverManager.getConnection(cumle,kull,sifre);
		sql = "CREATE DATABASE [" + VERITABANI + "]";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		cumle = "jdbc:sqlserver://" + server + ";instanceName=" + ins + ";database=" + VERITABANI + ";";
		con = DriverManager.getConnection(cumle,kull,sifre);
		create_table(fir_adi);
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		cumle = "jdbc:sqlserver://" + server + ";instanceName=" + ins + ";database=" + VERITABANI + "_LOG" + ";";
		con = DriverManager.getConnection(cumle,kull,sifre);
		create_table_log();
		//  VERITABANI DOSYASI ILK ACILIS
		ILOGER_KAYIT  vTLOG =  new DOSYA_MSSQL();
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
        sql = "CREATE TABLE GUNLUK ([GID] [int] IDENTITY(1,1) NOT NULL , TARIH DATE , SAAT nvarchar(5),ISIM nvarchar(20),GOREV nvarchar(20),MESAJ nvarchar(100) ,[USER] nvarchar(15) NULL)" ;  
        stmt = con.createStatement();  
        stmt.executeUpdate(sql);
        sql = "CREATE NONCLUSTERED INDEX [IDX_GUNLUK] ON [dbo].[GUNLUK](	[TARIH] ASC "
                + " )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)";
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
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
	 	con = null;
		ResultSet	rss = null;
		String cumle = "jdbc:sqlserver://" + BAGLAN.gunDizin.cONN_STR + ";";
        con = DriverManager.getConnection(cumle,BAGLAN.gunDizin.kULLANICI,BAGLAN.gunDizin.sIFRESI);
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
	    sql = "CREATE TABLE [dbo].[LOGLAMA]("
	    		+ "	[TARIH] [datetime] NOT NULL,"
	    		+ "	[MESAJ] [nchar](100) NOT NULL,"
	    		+ "	[EVRAK] [nchar](15) NOT NULL,"
	    		+ "	[USER_NAME] [nchar](15) NULL"
	    		+ ") ON [PRIMARY]";
	    	stmt = con.createStatement();  
	    	stmt.executeUpdate(sql);
	    	 sql = "CREATE NONCLUSTERED INDEX [IDX_LOGLAMA] ON [dbo].[LOGLAMA](	[TARIH] ASC,	[EVRAK] ASC , [USER_NAME] ASC "
	                  + " )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)";
	           stmt = con.createStatement();  
	           stmt.executeUpdate(sql);
	}
	@Override
	public void gorev_kayit(Gunluk_Bilgi gbilgi) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql  = "INSERT INTO GUNLUK (TARIH,SAAT,ISIM,GOREV,MESAJ,[USER]) " +
				" VALUES (?,?,?,?,?,?)" ;
		PreparedStatement stmt = null;
		stmt = con.prepareStatement(sql);
		stmt.setString(1, gbilgi.tarih);
		stmt.setString(2, gbilgi.saat);
		stmt.setString(3, gbilgi.isim);
		stmt.setString(4, gbilgi.gorev);
		stmt.setString(5, gbilgi.mesaj);
		stmt.setString(6, gbilgi.user);
		stmt.executeUpdate();
		stmt.close();
		   
	}
	@Override
	public void gorev_sil(int id) throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql = "DELETE GUNLUK  WHERE  GID = " + id;
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		
	}
	@Override
	public ResultSet gorev_oku(Gunluk_Bilgi gbilgi) throws ClassNotFoundException, SQLException 
	{
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = "SELECT FORMAT (TARIH, 'dd.MM.yyyy') as TARIH, SAAT,ISIM,GOREV,MESAJ  " +
				" FROM GUNLUK  " +
				" WHERE TARIH >=  '" + gbilgi.tarih + "'" +
				" ORDER BY TARIH  ";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	
		// TODO Auto-generated method stub
		
	}
}
