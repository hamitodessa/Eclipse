package OBS_C_2025;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import LOGER_KAYIT.DOSYA_MSSQL;
import LOGER_KAYIT.ILOGER_KAYIT;
import LOGER_KAYIT.TXT_LOG;

@SuppressWarnings("static-access")
public class CARI_HESAP_MSSQL implements ICARI_HESAP {

	public static Connection con = null;
	public static Connection akt_con = null;
	static Connection SQLitecon = null;
	static Statement stmt = null;
	private GLOBAL gLB = new GLOBAL();

	public void baglan() throws SQLException
	{
		String cumle = "jdbc:sqlserver://" + BAGLAN.cariDizin.cONN_STR + ";";
		DriverManager.setLoginTimeout(0);
		con = DriverManager.getConnection(cumle,BAGLAN.cariDizin.kULLANICI,BAGLAN.cariDizin.sIFRESI);
	}
	public void akt_baglan(String kod, String port) throws SQLException
	{
		String cnnstr = "" ;
		if ( ! port.toString().equals(""))
			port =  ":" + port ;
		if (new String( BAGLAN.cariDizin.yER.toString()).equals("L") == true)
			cnnstr = "localhost" + port +";instanceName=" + BAGLAN.cariDizin.iNSTANCE + " ; database=OK_Car" + kod ;
		else
			cnnstr = BAGLAN.cariDizin.sERVER + ";instanceName=" +BAGLAN.cariDizin.iNSTANCE + " ; database=OK_Car" + kod ;
		String cumle = "jdbc:sqlserver://" + cnnstr + ";";
		akt_con = DriverManager.getConnection(cumle,BAGLAN.cariDizin.kULLANICI,BAGLAN.cariDizin.sIFRESI);
	}
	public void cari_sifirdan_L(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		con = null;  
		String cumle = "";
		if ( ! sbilgi.getPort().toString().equals("") )
			sbilgi.setPort(  ":" + sbilgi.getPort() );
		cumle = "jdbc:sqlserver://localhost" + sbilgi.getPort() + ";instanceName=" + sbilgi.getIns() + ";";
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());   // SERVER BAGLANDI
		String VERITABANI = "OK_Car" +  sbilgi.getKod();
		stmt = null;
		String sql =null;
		if (sbilgi.getDizin_yeri() == "default")
			sql = "CREATE DATABASE [" + VERITABANI + "]";
		else
			sql = "CREATE DATABASE [" + VERITABANI + "]  ON PRIMARY " + " ( NAME = N'" + VERITABANI + "', FILENAME = N'" + sbilgi.getDizin() 	+ "\\" + VERITABANI + ".mdf ' ) " ;
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		cumle = "jdbc:sqlserver://localhost" + sbilgi.getPort() + ";instanceName=" + sbilgi.getIns() + ";database=" + VERITABANI + ";";  // DATABASE BAGLANDI
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		create_table(sbilgi.getFir_adi());
		//
		if (sbilgi.getDizin_yeri() == "default")
			sql = "CREATE DATABASE [" + VERITABANI + "_LOG" + "]";
		else
			sql = "CREATE DATABASE [" + VERITABANI + "_LOG" + "]  ON PRIMARY " + " ( NAME = N'" + VERITABANI + "_LOG" + "', FILENAME = N'" + sbilgi.getDizin() 	+ "\\" + VERITABANI + "_LOG" + ".mdf' ) ";
		cumle = "jdbc:sqlserver://localhost" + sbilgi.getPort() + ";instanceName=" + sbilgi.getIns() + ";";  // SERVER BAGLANDI
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		cumle = "jdbc:sqlserver://localhost" + sbilgi.getPort() + ";instanceName=" + sbilgi.getIns() + ";database=" + VERITABANI + "_LOG" + ";";  // DATABASE BAGLANDI
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		create_table_log();
		//VERITABANI DOSYASI ILK ACILIS*****************************************************
		ILOGER_KAYIT  vTLOG =  new DOSYA_MSSQL();
		lOG_BILGI lBILGI = new lOG_BILGI();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		lBILGI.seteVRAK("");
		vTLOG.Logla(lBILGI, BAGLAN_LOG.cariLogDizin);
		lBILGI.setmESAJ("Firma Adi:" + sbilgi.getFir_adi());
		vTLOG.Logla(lBILGI, BAGLAN_LOG.cariLogDizin);
		//SQLITE LOG DOSYASI OLUSTUR*********************************************************
		if (GLOBAL.dos_kontrol(GLOBAL.LOG_SURUCU + VERITABANI + "_mSSQL"+  ".DB") == false)
		{
			String dsy = GLOBAL.LOG_SURUCU + VERITABANI + "_mSSQL"+ ".DB" ;
			GLOBAL.create_table_log(dsy,sbilgi.getFir_adi(),BAGLAN_LOG.cariLogDizin);
		}
		//ACCDB LOG DOSYASI OLUSTUR*********************************************************
		if (GLOBAL.dos_kontrol(GLOBAL.LOG_SURUCU + VERITABANI + "_mSSQL"+  ".accdb") == false)
		{
			String dsy = GLOBAL.LOG_SURUCU + VERITABANI + "_mSSQL"+ ".accdb" ;
			GLOBAL.create_table_log_accdb(dsy,sbilgi.getFir_adi(),BAGLAN_LOG.cariLogDizin);
		}
		//  TEXT DOSYASI ILK ACILIS**********************************************************
		ILOGER_KAYIT  tEXLOG = new TXT_LOG();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		lBILGI.seteVRAK("");
		tEXLOG.Logla(lBILGI, BAGLAN_LOG.cariLogDizin);
		lBILGI.setmESAJ("Firma Adi:" + sbilgi.getFir_adi());
		tEXLOG.Logla(lBILGI, BAGLAN_LOG.cariLogDizin);
		//
		stmt.close();
		con.close();
	}
	public void cARI_SIFIR_S(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		con = null;  
		String VERITABANI = "OK_Car" +  sbilgi.getKod();
		String cumle = "";
		stmt = null;
		String sql =null;
		cumle = "jdbc:sqlserver://" + sbilgi.getServer() + ";instanceName=" + sbilgi.getIns() + ";";
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		sql = "CREATE DATABASE [" + VERITABANI + "]";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		cumle = "jdbc:sqlserver://" + sbilgi.getServer() + ";instanceName=" + sbilgi.getIns() + ";database=" + VERITABANI + ";";
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		create_table(sbilgi.getFir_adi());
		//
		sql = "CREATE DATABASE [" + VERITABANI + "_LOG" + "]";
		cumle = "jdbc:sqlserver://" + sbilgi.getServer() + ";instanceName=" + sbilgi.getIns() + ";";
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		cumle = "jdbc:sqlserver://" + sbilgi.getServer() + ";instanceName=" + sbilgi.getIns() + ";database=" + VERITABANI + "_LOG" + ";";
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		create_table_log();
		//  VERITABANI DOSYASI ILK ACILIS
		ILOGER_KAYIT  vTLOG =  new DOSYA_MSSQL();
		lOG_BILGI lBILGI = new lOG_BILGI();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		lBILGI.seteVRAK("");
		vTLOG.Logla(lBILGI, BAGLAN_LOG.cariLogDizin);
		lBILGI.setmESAJ("Firma Adi:" + sbilgi.getFir_adi() );
		vTLOG.Logla(lBILGI, BAGLAN_LOG.cariLogDizin);
		//SQLITE LOG DOSYASI OLUSTUR
		if (GLOBAL.dos_kontrol(  GLOBAL.LOG_SURUCU + GLOBAL.char_degis( BAGLAN_LOG.cariLogDizin.mODUL)) == false)
		{
			String dsy =  GLOBAL.LOG_SURUCU + GLOBAL.char_degis(BAGLAN_LOG.cariLogDizin.mODUL) ;
			GLOBAL.create_table_log(dsy,sbilgi.getFir_adi(),BAGLAN_LOG.cariLogDizin);
		}
		//ACCDB LOG DOSYASI OLUSTUR*********************************************************
		if (GLOBAL.dos_kontrol(GLOBAL.LOG_SURUCU + GLOBAL.char_degis( BAGLAN_LOG.cariLogDizin.mODULADI_ACCDB)) == false)
		{
			String dsy =  GLOBAL.LOG_SURUCU + GLOBAL.char_degis(BAGLAN_LOG.cariLogDizin.mODULADI_ACCDB) ;
			GLOBAL.create_table_log(dsy,sbilgi.getFir_adi(),BAGLAN_LOG.cariLogDizin);
		}
		//TEXT DOSYASI ILK ACILIS
		ILOGER_KAYIT  tEXLOG = new TXT_LOG();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		lBILGI.seteVRAK("");
		tEXLOG.Logla(lBILGI, BAGLAN_LOG.cariLogDizin);
		lBILGI.setmESAJ("Firma Adi:" + sbilgi.getFir_adi() );
		tEXLOG.Logla(lBILGI, BAGLAN_LOG.cariLogDizin);
		//
		stmt.close();
		con.close();
	}
	public void create_table(String fir_adi) throws SQLException {
		String sql = null;
		sql = "CREATE TABLE [dbo].[HESAP]("
				+ " [HESAP] [nvarchar](12) NOT NULL,"
				+ " [UNVAN] [nvarchar](50) NULL, "
				+ " [KARTON] [nvarchar](5) NULL,"
				+ " [HESAP_CINSI] [nvarchar](3) NULL,"
				+ " [USER] [nvarchar](15) NULL,"
				+ " CONSTRAINT [IX_HESAP] PRIMARY KEY CLUSTERED ([HESAP] ASC)WITH (PAD_INDEX = OFF,"
				+ " STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]"
				+ ") ON [PRIMARY]";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE [dbo].[HESAP_DETAY]( "
				+ " [D_HESAP] [nvarchar](12) NOT NULL,"
				+ " [YETKILI] [nvarchar](30) NULL,"
				+ " [TC_KIMLIK] [nvarchar](15) NULL,"
				+ " [ADRES_1] [nvarchar](35) NULL,"
				+ " [ADRES_2] [nvarchar](35) NULL,"
				+ " [SEMT] [nvarchar](15) NULL,"
				+ " [SEHIR] [nvarchar](15) NULL,"
				+ " [VERGI_DAIRESI] [nvarchar](25) NULL,"
				+ " [VERGI_NO] [nvarchar](15) NULL,"
				+ " [FAX] [nvarchar](25) NULL,"
				+ " [TEL_1] [nvarchar](25) NULL,"
				+ " [TEL_2] [nvarchar](25) NULL,"
				+ " [TEL_3] [nvarchar](25) NULL,"
				+ " [OZEL_KOD_1] [nvarchar](15) NULL,"
				+ " [OZEL_KOD_2] [nvarchar](15) NULL,"
				+ " [OZEL_KOD_3] [nvarchar](15) NULL,"
				+ " [ACIKLAMA] [nvarchar](30) NULL,"
				+ " [WEB] [nvarchar](50) NULL,"
				+ " [E_MAIL] [nvarchar](30) NULL,"
				+ " [SMS_GONDER] [bit] NULL,"
				+ " [RESIM] [image] NULL,"
				+ " CONSTRAINT [D_HESAP] PRIMARY KEY CLUSTERED(	[D_HESAP] ASC)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF,"
				+ "  ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY] ) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE [dbo].[SATIRLAR]( "
				+ " [SID] [int] IDENTITY(1,1) NOT NULL,"
				+ " [HESAP] [nvarchar](12) NOT NULL,"
				+ " [TARIH] [datetime] NULL,"
				+ " [H] [nvarchar](1) NULL,"
				+ " [EVRAK] [int] NOT NULL,"
				+ " [CINS] [nvarchar](2) NULL,"
				+ " [KUR] [float] NULL,"
				+ " [BORC] [float] NULL,"
				+ " [ALACAK] [float] NULL,"
				+ " [KOD] [nvarchar](5) NULL,"
				+ " [USER] [nvarchar](15) NULL,"
				+ " CONSTRAINT [IX_SID] PRIMARY KEY CLUSTERED(	[SID] ASC)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, "
				+ " IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]) ON [PRIMARY]";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE NONCLUSTERED INDEX [IX_SATIRLAR] "
				+"	ON [dbo].[SATIRLAR] ([HESAP],[TARIH])"
				+"	INCLUDE ([EVRAK],[KUR],[BORC],[ALACAK],[KOD],[USER])";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE [dbo].[IZAHAT](	"
				+ " [EVRAK] [int] NOT NULL,	"
				+ " [IZAHAT] [nvarchar](100) NULL,"
				+ " CONSTRAINT [IX_EVRAK] PRIMARY KEY CLUSTERED ([EVRAK] ASC)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF,"
				+ " IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]) ON [PRIMARY]";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE FULLTEXT CATALOG [IZAHAT] WITH ACCENT_SENSITIVITY = ON AS DEFAULT";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE EVRAK_NO(EID int identity(1,1) CONSTRAINT PKeyEID PRIMARY KEY,EVRAK integer )";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE [dbo].[OZEL]("
				+ "[OZID] [int] IDENTITY(1,1) NOT NULL,"
				+ "[YONETICI] [nvarchar](25) NULL,"
				+ "[YON_SIFRE] [nvarchar](15) NULL,"
				+ "[FIRMA_ADI] [nvarchar](50) NULL,"
				+ " CONSTRAINT [PKeyOZID] PRIMARY KEY CLUSTERED (	[OZID] ASC)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF,"
				+ "IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]) ON [PRIMARY]";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE [dbo].[YETKILER]( "
				+ "[YETID] [int] IDENTITY(1,1) NOT NULL,"
				+ "[KULLANICI] [nvarchar](25) NULL,"
				+ "[KARTON] [nvarchar](5) NULL,"
				+ "[TAM_YETKI] [bit] NULL,"
				+ "[GORUNTU] [bit] NULL,"
				+ " CONSTRAINT [PK_YETID] PRIMARY KEY CLUSTERED "
				+ " ([YETID] ASC)WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON,"
				+ " ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]) ON [PRIMARY]";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql= "CREATE TABLE [dbo].[ANA_GRUP_DEGISKEN]( "
				+ "  [ANA_GRUP] [nvarchar](25) NOT NULL,"
				+ "  [USER] [nvarchar](15) NOT NULL,"
				+ "  CONSTRAINT [IX_ANA_GRUP] PRIMARY KEY CLUSTERED " 
				+ "  ([ANA_GRUP] ASC) WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, " 
				+ "  ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]) ON [PRIMARY]";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE [dbo].[ALT_GRUP_DEGISKEN]( "
				+ "[ANA_GRUP] [int] NOT NULL, "
				+ "[ALT_GRUP] [nvarchar](25) NOT NULL, "
				+ "[USER] [nvarchar](15) NOT NULL) ON [PRIMARY]";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		// STORED PROCEDURE
		sql =  "CREATE PROCEDURE [dbo].[EKSTRE] " + 
				" @t1 nvarchar (10) ,  @t2 nvarchar (10),  @kodu nvarchar(12) " + 
				" AS " + 
				" SELECT TARIH,SATIRLAR.EVRAK ,ISNULL( IZAHAT.IZAHAT,'') AS IZAHAT,KOD,KUR,BORC,ALACAK, " + 
				" CAST(SUM(ALACAK-BORC)  OVER(ORDER BY TARIH  ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW) AS DECIMAL (30,2)) As BAKIYE ,[USER] " + 
				" FROM SATIRLAR   LEFT JOIN IZAHAT   " + 
				" ON SATIRLAR.EVRAK = IZAHAT.EVRAK WHERE  HESAP =  @kodu" + 
				" AND TARIH >= @t1 AND  TARIH <= @t2 + ' 23:59:59.998'	" + 
				" ORDER BY TARIH  ; " ;
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);		
		sql =  "CREATE PROCEDURE [dbo].[GUNLUK_ISLEM] " + 
				" @t1 nvarchar (10) ,  @t2 nvarchar (10) " + 
				" AS " + 
				"  SELECT SATIRLAR.HESAP,TARIH, SATIRLAR.EVRAK ,ISNULL( IZAHAT.IZAHAT,'') AS IZAHAT,KOD,BORC,ALACAK ,SATIRLAR.[USER] " + 
				"  FROM SATIRLAR  ,IZAHAT   ,HESAP  " + 
				"  WHERE SATIRLAR.EVRAK = IZAHAT.EVRAK   AND SATIRLAR.HESAP = HESAP.HESAP " + 
				"  AND TARIH BETWEEN  @t1 AND  @t2 + ' 23:59:59.998'" + 
				"  ORDER BY TARIH ,SATIRLAR.EVRAK  ; " ;
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);	
		//TAHSIL FISI
		sql = "CREATE TABLE TAH_EVRAK(CINS nvarchar(3),NO integer )";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE [dbo].[TAH_AYARLAR]( " +
				" [LOGO] [image] NULL," +
				" [FIR_ISMI] [nvarchar](50) NULL, " +
				" [ADR_1] [nvarchar](50) NULL," +
				" [ADR_2] [nvarchar](50) NULL," +
				" [VD_VN] [nvarchar](60) NULL," +
				" [MAIL] [nvarchar](60) NULL," +
				" [DIGER] [nvarchar](50) NULL, " + 
				" [KASE] [image] NULL" +
				" ) ";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE [dbo].[TAH_DETAY](" +
				" [EVRAK] [nvarchar](15) NOT NULL," +
				" [TARIH] [datetime] NULL," +
				" [C_HES] [nvarchar](12) NULL," +
				" [A_HES] [nvarchar](12) NULL," +
				" [CINS] [int] NOT NULL," +
				" [TUTAR] [float] NULL," +
				" [TUR] [int] NOT NULL," +
				" [ACIKLAMA] [nvarchar](50) NULL," +
				" [DVZ_CINS] [nvarchar](3) NULL," +
				" [POS_BANKA] [nvarchar](40) NULL" +
				" ) ";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE TAH_CEK ( " + 
				" EVRAK nvarchar(15)," + 
				" CINS int, " + 
				" BANKA nvarchar(40)," + 
				" SUBE nvarchar(40) ," + 
				" SERI nvarchar(20), " + 
				" HESAP nvarchar(20)," + 
				" BORCLU nvarchar(40)," + 
				" TARIH datetime," + 
				" TUTAR float) "  ;
		stmt = con.createStatement();  
		stmt.executeUpdate(sql); 
		sql = "INSERT INTO TAH_EVRAK(CINS,NO) VALUES ('GIR','0')";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "INSERT INTO TAH_EVRAK(CINS,NO) VALUES ('CIK','0')";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		// ***************EVRAK NO YAZ ************
		sql = "INSERT INTO  EVRAK_NO(EVRAK) VALUES ('0')";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		// ***************OZEL NO YAZ ************
		sql = "INSERT INTO  OZEL(YONETICI,YON_SIFRE,FIRMA_ADI) VALUES ('" + GLOBAL.KULL_ADI  + "','12345' , '" + fir_adi + "')";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
	}
	@Override
	public String cari_firma_adi() throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String cumle = "jdbc:sqlserver://" +  BAGLAN.cariDizin.cONN_STR + ";";
		con = DriverManager.getConnection(cumle, BAGLAN.cariDizin.kULLANICI, BAGLAN.cariDizin.sIFRESI);
		PreparedStatement stmt = con.prepareStatement("SELECT *  FROM OZEL ");
		rss = stmt.executeQuery();
		rss.next();
		int count=0;
		count = rss.getRow();
		String result;
		result = count != 0 ? rss.getString("FIRMA_ADI") : "" ;
		return result;	
	}
	public ResultSet ekstre(String hesap , String t1 ,String t2) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String tARIH = "" ;
		if(! t1.equals("1900.01.01") || ! t2.equals("2100.12.31"))
			tARIH = "  AND TARIH BETWEEN  '" + t1 + "' AND '" + t2 + " 23:59:59.998'" ;
		String sql = " SELECT TARIH,SATIRLAR.EVRAK ,IZAHAT,KOD,KUR,BORC,ALACAK, "  + 
				"  CAST(SUM(ALACAK-BORC) OVER(ORDER BY TARIH  ROWS BETWEEN UNBOUNDED PRECEDING And CURRENT ROW)  AS DECIMAL(30,2))  AS BAKIYE ,[USER] "  + 
				"  FROM SATIRLAR  LEFT JOIN IZAHAT   " + 
				"  ON SATIRLAR.EVRAK = IZAHAT.EVRAK WHERE  HESAP =N'" + hesap + "'" + 
				tARIH + 
				"  ORDER BY TARIH   ";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	 
	}
	public ResultSet hesap_adi_oku(String hesap) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement("SELECT HESAP, HESAP_CINSI,  KARTON, UNVAN FROM HESAP  " + 
				" WHERE HESAP = N'" + hesap + "'");
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet hp_pln () throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM HESAP    ORDER BY HESAP ");
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet ekstre_mizan (String kod,String ilktarih,String sontarih,String ilkhcins,String sonhcins,String ilkkar,String sonkar) throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = " SELECT SATIRLAR.HESAP, HESAP.UNVAN, HESAP.HESAP_CINSI, SUM(SATIRLAR.BORC) AS ISLEM, SUM(SATIRLAR.ALACAK) AS ISLEM2, SUM(SATIRLAR.ALACAK - SATIRLAR.BORC) AS BAKIYE" +
				" FROM SATIRLAR  LEFT JOIN" +
				" HESAP ON SATIRLAR.HESAP = HESAP.HESAP " +
				" WHERE SATIRLAR.HESAP =N'" + kod + "'   " + 
				" AND SATIRLAR.TARIH >= '" + ilktarih + "' AND SATIRLAR.TARIH < '" + sontarih + "' " + 
				" AND HESAP.HESAP_CINSI  BETWEEN N'" + ilkhcins + "'  AND  " +
				" N'" + sonhcins + "' AND HESAP.KARTON BETWEEN N'" + ilkkar + "' AND N'" + sonkar + "'" +
				" GROUP BY SATIRLAR.HESAP, HESAP.UNVAN, HESAP.HESAP_CINSI " +
				" ORDER BY SATIRLAR.HESAP " ;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet kasa_mizan(String kod,String ilktarih,String sontarih) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(" SELECT SATIRLAR.HESAP, HESAP.UNVAN, HESAP.HESAP_CINSI, SUM(SATIRLAR.BORC) AS islem, SUM(SATIRLAR.ALACAK) AS islem2, SUM(SATIRLAR.ALACAK - SATIRLAR.BORC) AS bakiye" +
				" FROM SATIRLAR  LEFT JOIN" +
				" HESAP  ON SATIRLAR.HESAP = HESAP.HESAP " +
				" WHERE SATIRLAR.HESAP =N'" + kod + "' " + 
				" AND SATIRLAR.TARIH >= '" + ilktarih + "' AND SATIRLAR.TARIH < '" + sontarih + " 23:59:59.998'" +
				" GROUP BY SATIRLAR.HESAP, HESAP.UNVAN, HESAP.HESAP_CINSI " +
				" ORDER BY SATIRLAR.HESAP ");
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet ekstre_sqlite() throws ClassNotFoundException, SQLException
	{
		Class.forName("org.sqlite.JDBC");
		SQLitecon = null;
		ResultSet	rss = null;
		PreparedStatement stmt = null;
		SQLitecon = gLB.myEkstreConnection();
		String sql = "SELECT TARIH,EVRAK ,IZAHAT,KOD,KUR,BORC,ALACAK,BAKIYE FROM EKSTRE";
		stmt = SQLitecon.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}
	public ResultSet mizan(String h1 , String h2,String t1,String t2,String c1,String c2,String k1, String k2, String o1 , String o2) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String tARIH = "" ;
		if(! t1.equals("1900.01.01") || ! t2.equals("2100.12.31"))
			tARIH = " AND TARIH BETWEEN '" + t1 + "' AND '"  + t2  + " 23:59:59.998'" ;
		String sql = "SELECT SATIRLAR.HESAP,HESAP.UNVAN,HESAP.HESAP_CINSI AS H_CINSI," + 
				" ROUND(SUM(SATIRLAR.BORC),2) AS BORC, ROUND(SUM(SATIRLAR.ALACAK),2) AS ALACAK, " + 
				" ROUND(SUM(SATIRLAR.ALACAK),2) - ROUND(SUM(SATIRLAR.BORC),2) AS BAKIYE" +
				" FROM SATIRLAR  ,HESAP " +
				" WHERE SATIRLAR.HESAP = HESAP.HESAP " +
				" AND SATIRLAR.HESAP BETWEEN N'" + h1 + "' AND N'" + h2 + "'" +
				tARIH + 
				" AND HESAP.HESAP_CINSI BETWEEN N'" + c1 + "' AND '" + c2 + "'" +
				" AND HESAP.KARTON BETWEEN N'" + k1 + "' AND N'" + k2 + "' " +
				" GROUP BY SATIRLAR.HESAP, HESAP.UNVAN, HESAP.HESAP_CINSI " + o1 + " " + o2 + "" ;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet fiskon(int evrakno) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = "SELECT HESAP,TARIH,H,SATIRLAR.EVRAK,CINS, KUR,BORC,ALACAK,  ISNULL( IZAHAT,'') AS IZAHAT  ,KOD ,[USER] " +
				" FROM SATIRLAR   LEFT JOIN IZAHAT    ON  SATIRLAR.EVRAK = IZAHAT.EVRAK  " +
				" WHERE SATIRLAR.EVRAK = '" + evrakno + "'" +
				" ORDER BY H DESC ";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet cari_sonfisno() throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = "SELECT MAX(EVRAK) AS MAX_NO  FROM SATIRLAR";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public int cari_fisno_al() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		int E_NUMBER ;
		String sql = "SELECT  EVRAK FROM EVRAK_NO WITH (HOLDLOCK, ROWLOCK) ";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		rss.next();
		E_NUMBER = rss.getInt("EVRAK");
		E_NUMBER = E_NUMBER + 1 ;
		//******** KAYIT
		sql = "UPDATE EVRAK_NO SET EVRAK =" + E_NUMBER + "  WHERE EID = 1";
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		stmt.close();
		//**************
		return E_NUMBER;	
	}
	public void evrak_yoket(int num) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql = "DELETE SATIRLAR  WHERE  EVRAK = " + num;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		sql = "DELETE IZAHAT  WHERE  EVRAK = " + num;
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
	public boolean cari_fino_bak(int fisno) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql =  "SELECT * FROM SATIRLAR  WHERE  EVRAK = " + fisno + "  ORDER BY H desc ";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		rss.next();
		int count = 0;
		count = rss.getRow();
		//boolean result;
		//result = count  != 0 ? true : false;
		return count  != 0;

	}
	public void cari_dekont_kaydet(dEKONT_BILGI dBilgi) throws SQLException, ClassNotFoundException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql  = "INSERT INTO SATIRLAR (HESAP,TARIH,H,EVRAK,CINS,KUR,BORC,ALACAK,KOD,[USER]) " +
				" VALUES (?,?,?,?,?,?,?,?,?,?)" ;
		PreparedStatement stmt = null;
		kONTROL();
		stmt = con.prepareStatement(sql);
		stmt.setString(1, dBilgi.getbHES());
		stmt.setString(2, dBilgi.gettAR());
		stmt.setString(3, "B");
		stmt.setInt(4, dBilgi.geteVRAK());
		stmt.setString(5, dBilgi.getbCINS());
		stmt.setDouble(6, dBilgi.getbKUR());
		stmt.setDouble(7, (double) Math.round(dBilgi.getbORC() * 100) / 100);
		stmt.setDouble(8, 0);
		stmt.setString(9, dBilgi.getkOD());
		stmt.setString(10, dBilgi.getuSER());
		stmt.addBatch();
		//**********************************
		stmt.setString(1, dBilgi.getaHES());
		stmt.setString(2, dBilgi.gettAR());
		stmt.setString(3, "A");
		stmt.setInt(4, dBilgi.geteVRAK());
		stmt.setString(5, dBilgi.getaCINS());
		stmt.setDouble(6, dBilgi.getaKUR());
		stmt.setDouble(7, 0);
		stmt.setDouble(8, (double) Math.round(dBilgi.getaLACAK() * 100) / 100);
		stmt.setString(9, dBilgi.getkOD());
		stmt.setString(10, dBilgi.getuSER());
		stmt.addBatch();
		stmt.executeBatch();
		//************** IZAHAT  *********
		PreparedStatement stmt3 = null;
		sql = "INSERT INTO IZAHAT (EVRAK,IZAHAT) VALUES ( ?,? )" ;
		stmt3 = con.prepareStatement(sql);
		stmt3.setInt(1, dBilgi.geteVRAK());
		stmt3.setString(2, dBilgi.getiZAHAT());
		stmt3.executeUpdate();
		stmt.close();
		stmt3.close();
	}
	public ResultSet hsp_pln(String arama) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet rss = null;
		String sql = "SELECT  [HESAP],[UNVAN],[HESAP_CINSI],[KARTON],[YETKILI],[ADRES_1],[ADRES_2],[SEMT],[SEHIR],[VERGI_DAIRESI]," + 
				"[VERGI_NO],[FAX],[TEL_1],[TEL_2],[TEL_3],[OZEL_KOD_1],[OZEL_KOD_2],[OZEL_KOD_3],[ACIKLAMA],[TC_KIMLIK],[WEB]," + 
				"[E_MAIL],[SMS_GONDER],[RESIM] ,[USER] " + 
				" FROM [HESAP]   LEFT OUTER JOIN [HESAP_DETAY] WITH (INDEX (D_HESAP)) ON " + 
				"HESAP.HESAP = HESAP_DETAY.D_HESAP "+ arama + " ORDER BY HESAP ";
		kONTROL();
		Statement stmt = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		return rss;	
	}
	public void hsp_sil(String hesap) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql = "DELETE FROM HESAP WHERE HESAP =N'" + hesap + "'" ;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		sql =  "DELETE FROM HESAP_DETAY WHERE D_HESAP =N'" + hesap + "'" ;
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		stmt.close();
	}
	public String kod_ismi(String kodu) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet rss = null;
		String sql = "SELECT UNVAN  FROM HESAP WHERE HESAP =N'" + kodu + "'";
		kONTROL();
		Statement stmt = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		rss.next();
		int count=0;
		count = rss.getRow();
		String result;
		result = count  != 0 ? result = rss.getString("UNVAN") : "" ;
		return result;	

	}
	public void hpln_kayit(String kodu,String adi,String karton,String hcins,String usr) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql  = "INSERT INTO HESAP (HESAP,UNVAN,KARTON,HESAP_CINSI,[USER]) " +
				" VALUES (?,?,?,?,?)" ;
		PreparedStatement stmt = null;
		kONTROL();
		stmt = con.prepareStatement(sql);
		stmt.setString(1, kodu);
		stmt.setString(2, adi);
		stmt.setString(3, karton);
		stmt.setString(4, hcins);
		stmt.setString(5, usr);
		stmt.executeUpdate();
		stmt.close();
	}
	public void hpln_detay_kayit(String kodu ,String yet ,String ad1 ,String ad2 ,String semt,String seh  , String vd , String vn 
			, String t1 ,String t2 ,String t3 ,String fx ,String o1 ,String o2 ,String o3 , String web 
			,String mai ,String kim  ,String acik ,boolean sms , InputStream  resim ) throws ClassNotFoundException, SQLException, IOException

	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql  = "INSERT INTO HESAP_DETAY (D_HESAP,YETKILI,ADRES_1,ADRES_2,SEMT,SEHIR,VERGI_DAIRESI,VERGI_NO,TEL_1,TEL_2, " + 
				" TEL_3,FAX,OZEL_KOD_1,OZEL_KOD_2,OZEL_KOD_3,WEB,E_MAIL,TC_KIMLIK,ACIKLAMA,SMS_GONDER,RESIM)" +
				" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
		PreparedStatement stmt = null;
		kONTROL();
		stmt = con.prepareStatement(sql);
		stmt.setString(1, kodu);
		stmt.setString(2,yet);
		stmt.setString(3, ad1);
		stmt.setString(4, ad2);
		stmt.setString(5, semt);
		stmt.setString(6, seh);
		stmt.setString(7, vd);
		stmt.setString(8, vn);
		stmt.setString(9, t1);
		stmt.setString(10, t2);
		stmt.setString(11, t3);
		stmt.setString(12, fx);
		stmt.setString(13, o1);
		stmt.setString(14, o2);
		stmt.setString(15, o3);
		stmt.setString(16, web);
		stmt.setString(17, mai);
		stmt.setString(18, kim);
		stmt.setString(19, acik);
		stmt.setBoolean(20, sms);

		if (  resim != null)
		{
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			for (int readNum; (readNum = resim.read(buf)) != -1;) {
				bos.write(buf, 0, readNum);
			}
			byte[] bytes = bos.toByteArray();
			stmt.setBytes(21,bytes);
		}
		else
			stmt.setBytes(21,null);
		stmt.executeUpdate();
		stmt.close();
	}
	public ResultSet dvz_cevirme(String kcins,String hesap,String t1,String t2,String kur,String islem,String hKUR) throws ClassNotFoundException, SQLException
	{
		String str1, str2 ;
		str1 = "" ;
		str2 = "" ;
		if (BAGLAN.kurDizin.dIZIN_CINS.equals("L"))
			str1 = "OK_Kur" + BAGLAN.kurDizin.kOD + ".dbo.kurlar  " ;
		else
		{
			if ( BAGLAN.cariDizin.sERVER.equals(BAGLAN.kurDizin.sERVER))
			{
				str1 = "OK_Kur" + BAGLAN.kurDizin.kOD + ".dbo.kurlar  " ;
				str2 = "";
			}
			else
			{
				str1 = "OPENROWSET('SQLOLEDB','" + BAGLAN.kurDizin.sERVER + "\\" + BAGLAN.kurDizin.iNSTANCE + "';'" + BAGLAN.kurDizin.kULLANICI +  "';'" + BAGLAN.kurDizin.sIFRESI + 
						"','SELECT * FROM [OK_Kur" + BAGLAN.kurDizin.kOD + "].[dbo].[kurlar]  ') ";
			}
		}
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = "" ;
		String tARIH = "" ;
		if(! t1.equals("1900.01.01") || ! t2.equals("2100.12.31"))
			tARIH = " AND SATIRLAR.TARIH  BETWEEN  '" + t1 + "'  AND '" + t2 + " 23:59:59.998'  " ;
		if (hKUR.equals("Kayitli"))
		{
			sql = "SELECT SATIRLAR.TARIH, SATIRLAR.EVRAK ,I.IZAHAT , " +
					" SATIRLAR.KUR as CEV_KUR , " +
					" ((IIF(SATIRLAR.ALACAK - SATIRLAR.BORC = 0, 1, SATIRLAR.ALACAK - SATIRLAR.BORC)) " + islem + " IIF(SATIRLAR.KUR =0, 1, SATIRLAR.KUR)) as DOVIZ_TUTAR , " +
					" SUM((NULLIF(  SATIRLAR.ALACAK - SATIRLAR.BORC,1) ) " + islem + " IIF(SATIRLAR.KUR =0, 1, SATIRLAR.KUR)) OVER(ORDER BY SATIRLAR.TARIH " + 
					" ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW) as DOVIZ_BAKIYE , " +
					" CAST(SUM(SATIRLAR.ALACAK-SATIRLAR.BORC) OVER(ORDER BY SATIRLAR.TARIH  ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW) AS DECIMAL (30,2)) as BAKIYE ,  " +
					" SATIRLAR.KUR, BORC, ALACAK ,SATIRLAR.[USER] " +
					" FROM SATIRLAR  LEFT OUTER JOIN IZAHAT as I   on SATIRLAR.EVRAK = I.EVRAK " +
					" WHERE HESAP  = N'" + hesap + "' " + 
					tARIH +
					" ORDER BY SATIRLAR.TARIH ";
		}
		else 
		{
			if(! t1.equals("1900.01.01") || ! t2.equals("2100.12.31"))
				tARIH = " AND s.TARIH  BETWEEN  '" + t1 + "'  AND '" + t2 + " 23:59:59.998' " ;
			sql = str2 + "SELECT s.TARIH, s.EVRAK ,I.IZAHAT , " +
					" ISNULL(IIF(k." + kcins + " = 0,1,k." + kcins + " ), 1) as CEV_KUR , " +
					" ((s.ALACAK - s.BORC ) " + islem + " ISNULL(NULLIF(k." + kcins + ",0), 1)) as DOVIZ_TUTAR , " +
					" SUM(((s.ALACAK - s.BORC ) * s.KUR) " + islem + " ISNULL(NULLIF(k." + kcins + ",0), 1)) OVER(ORDER BY s.TARIH " + 
					" ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW) as DOVIZ_BAKIYE , " +
					" CAST(SUM(s.ALACAK-s.BORC) OVER(ORDER BY s.TARIH  ROWS BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW) AS DECIMAL (30,2)) as BAKIYE ,  " +
					" s.KUR, BORC, ALACAK ,s.[USER] " +
					" FROM (SATIRLAR as s    LEFT OUTER JOIN IZAHAT as I   on s.EVRAK = I.EVRAK) " +
					" LEFT OUTER JOIN " + str1 + " as k ON Convert(VARCHAR(25), s.TARIH, 121) = k.Tarih  " +
					" WHERE HESAP  = N'" + hesap + "' " + 
					tARIH + 
					" AND (k.kur IS NULL OR k.KUR ='" + kur + "') " +
					" ORDER BY TARIH ";
		}
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet karton_dold(String karton) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = "SELECT HESAP, UNVAN,HESAP_CINSI AS CINS " +
				" FROM HESAP   " +
				" WHERE KARTON = N'" + karton + "'" + 
				" ORDER BY HESAP ";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet ekstre_arama(String hes , String acik , String gun ,String ay,String yil ,String kod,String kullanici ) throws ClassNotFoundException, SQLException
	{
		StringBuilder stb = new StringBuilder();
		stb.append(" SELECT SATIRLAR.HESAP, HESAP.UNVAN, SATIRLAR.TARIH , SATIRLAR.EVRAK, IZAHAT, KOD, KUR, SATIRLAR.BORC, SATIRLAR.ALACAK ,SATIRLAR.[USER] " ); 
		stb.append(" FROM   SATIRLAR   , HESAP   , IZAHAT  ") ; 
		stb.append(" WHERE SATIRLAR.HESAP = HESAP.HESAP AND SATIRLAR.EVRAK = IZAHAT.EVRAK ") ;
		if ( ! hes.equals("%") && ! hes.equals("%&"))
			stb.append(" AND SATIRLAR.HESAP LIKE N'" + hes + "'") ;
		stb.append(" AND IZAHAT.IZAHAT  LIKE N'" + acik + "'") ;
		if ( ! yil.equals(""))
			stb.append(" AND DATEPART(YEAR,TARIH) LIKE '" + yil + "'") ;
		if ( ! ay.equals(""))
			stb.append(" AND DATEPART(MONTH,TARIH) LIKE '" + ay + "'") ;
		if ( ! gun.equals(""))
			stb.append(" AND DATEPART(DAY,TARIH) LIKE '" + gun + "'") ;
		if ( ! kod.equals("%") &&  ! kod.equals("%%"))
			stb.append(" AND KOD LIKE '" + kod + "'");
		if ( ! kullanici.equals("%") &&  ! kullanici.equals("%%"))
			stb.append(" AND SATIRLAR.[USER] LIKE '" + kullanici + "'");
		stb.append(" ORDER BY SATIRLAR.TARIH ,SATIRLAR.EVRAK ") ;
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = stb.toString() ;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet kasa_kontrol(String hesap,String t1) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = " SELECT SATIRLAR.EVRAK ,IZAHAT,KOD,BORC,ALACAK ,[USER] " +
				" FROM SATIRLAR   ,IZAHAT   " +
				" WHERE SATIRLAR.EVRAK = IZAHAT.EVRAK and  HESAP =N'" + hesap + "'" +
				" AND CONVERT(VARCHAR(25), TARIH, 121) LIKE  '" + t1 + "%'" +
				" ORDER BY SATIRLAR.EVRAK  ";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet dek_mizan(String kod) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = " SELECT SATIRLAR.HESAP,HESAP.UNVAN ,HESAP_CINSI,sum(BORC) as borc ,sum(ALACAK) as alacak ,sum( ALACAK -BORC ) as bakiye " +
				" FROM SATIRLAR  , HESAP   " +
				" WHERE SATIRLAR.HESAP = HESAP.HESAP " +
				" AND  SATIRLAR.HESAP= N'" + kod + "'" +
				" GROUP BY SATIRLAR.HESAP ,HESAP.UNVAN,HESAP_CINSI " +
				" ORDER BY SATIRLAR.HESAP ";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public int coklu_cari_fisno_al (int adet) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		int E_NUMBER ;
		String sql = "SELECT  EVRAK FROM EVRAK_NO WITH (HOLDLOCK, ROWLOCK) ";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		rss.next();
		E_NUMBER = rss.getInt("EVRAK");
		E_NUMBER = E_NUMBER + 1 ;
		//******** KAYIT
		int sayi = E_NUMBER + (adet - 1) ;
		sql = "UPDATE EVRAK_NO SET EVRAK =" + sayi + "  WHERE EID = 1";
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		stmt.close();
		//**************
		return E_NUMBER;	
	}
	public ResultSet gunisl(String t1,String t2) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = "SELECT SATIRLAR.HESAP,TARIH, SATIRLAR.EVRAK ,IZAHAT,KOD,BORC,ALACAK ,SATIRLAR.[USER] " +
				" FROM SATIRLAR  ,IZAHAT   ,HESAP  " +
				" WHERE SATIRLAR.EVRAK = IZAHAT.EVRAK " +
				" AND SATIRLAR.HESAP = HESAP.HESAP " +
				" AND TARIH BETWEEN  '" + t1 + "' AND '" + t2 + " 23:59:59.998'" +
				" ORDER BY TARIH ,SATIRLAR.EVRAK ";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public int hesap_plani_kayit_adedi () throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = "SELECT COUNT( HESAP) AS SAYI FROM HESAP ";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		rss.next();
		return rss.getInt("SAYI");
	}
	public void hpln_ilk_detay_kayit(String kodu) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql  = "INSERT INTO HESAP_DETAY (D_HESAP,YETKILI,ADRES_1,ADRES_2,SEMT,SEHIR,VERGI_DAIRESI,VERGI_NO,TEL_1,TEL_2, " + 
				" TEL_3,FAX,OZEL_KOD_1,OZEL_KOD_2,OZEL_KOD_3,WEB,E_MAIL,TC_KIMLIK,ACIKLAMA,SMS_GONDER,RESIM)" +
				" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
		PreparedStatement stmt = null;
		kONTROL();
		stmt = con.prepareStatement(sql);
		stmt.setString(1, kodu);
		stmt.setString(2,"");
		stmt.setString(3, "");
		stmt.setString(4,"");
		stmt.setString(5, "");
		stmt.setString(6,"");
		stmt.setString(7, "");
		stmt.setString(8, "");
		stmt.setString(9,"");
		stmt.setString(10, "");
		stmt.setString(11,"");
		stmt.setString(12,"");
		stmt.setString(13, "");
		stmt.setString(14,"");
		stmt.setString(15,"");
		stmt.setString(16,"");
		stmt.setString(17,"");
		stmt.setString(18, "");
		stmt.setString(19, "");
		stmt.setBoolean(20,false);
		stmt.setBytes(21,null);
		stmt.executeUpdate();
		stmt.close();
	}
	public void cari_kod_degis_hesap(String t1,String t2) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql = "UPDATE HESAP SET HESAP = N'" + t2 + "'  WHERE HESAP = N'" + t1 + "'";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		stmt.clearParameters();
		sql = "UPDATE HESAP_DETAY  SET D_HESAP = N'" + t2 + "'  WHERE D_HESAP = N'" + t1 + "'";
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
	public void cari_kod_degis_satirlar(String t1,String t2) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql = "UPDATE SATIRLAR SET HESAP = N'" + t2 + "'  WHERE HESAP = N'" + t1 + "'";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
	public int yilsonu_hesap_plani_kayit_adedi () throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = "SELECT COUNT( HESAP) AS SAYI FROM HESAP ";
		PreparedStatement stmt = akt_con.prepareStatement(sql);
		rss = stmt.executeQuery();
		rss.next();
		return rss.getInt("SAYI");
	}
	public void yilsonu_hpln_kayit(String kodu,String adi,String karton,String hcins,String usr) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql  = "INSERT INTO HESAP (HESAP,UNVAN,KARTON,HESAP_CINSI,[USER]) " +
				" VALUES (?,?,?,?,?)" ;
		PreparedStatement stmt = null;
		stmt = akt_con.prepareStatement(sql);
		stmt.setString(1, kodu);
		stmt.setString(2, adi);
		stmt.setString(3, karton);
		stmt.setString(4, hcins);
		stmt.setString(5, usr);
		stmt.executeUpdate();
		stmt.close();
	}
	public void yilsonu_hpln_detay_kayit(String kodu ,String yet ,String ad1 ,String ad2 ,String semt,String seh  , String vd , String vn 
			, String t1 ,String t2 ,String t3 ,String fx ,String o1 ,String o2 ,String o3 , String web 
			,String mai ,String kim  ,String acik   ) throws ClassNotFoundException, SQLException, IOException

	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql  = "INSERT INTO HESAP_DETAY (D_HESAP,YETKILI,ADRES_1,ADRES_2,SEMT,SEHIR,VERGI_DAIRESI,VERGI_NO,TEL_1,TEL_2, " + 
				" TEL_3,FAX,OZEL_KOD_1,OZEL_KOD_2,OZEL_KOD_3,WEB,E_MAIL,TC_KIMLIK,ACIKLAMA,SMS_GONDER,RESIM)" +
				" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
		PreparedStatement stmt = null;
		stmt = akt_con.prepareStatement(sql);
		stmt.setString(1, kodu);
		stmt.setString(2,yet);
		stmt.setString(3, ad1);
		stmt.setString(4, ad2);
		stmt.setString(5, semt);
		stmt.setString(6, seh);
		stmt.setString(7, vd);
		stmt.setString(8, vn);
		stmt.setString(9, t1);
		stmt.setString(10, t2);
		stmt.setString(11, t3);
		stmt.setString(12, fx);
		stmt.setString(13, o1);
		stmt.setString(14, o2);
		stmt.setString(15, o3);
		stmt.setString(16, web);
		stmt.setString(17, mai);
		stmt.setString(18, kim);
		stmt.setString(19, acik);
		stmt.setBoolean(20, true);
		stmt.setBytes(21,null);
		stmt.executeUpdate();
		stmt.close();
	}
	public ResultSet mizan_aktar (String hesap) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql =" SELECT HESAP,SUM(BORC) AS BORC, SUM(ALACAK) AS ALACAK " +
				" FROM SATIRLAR   " +
				" WHERE HESAP= N'" + hesap + "'" +
				" GROUP BY HESAP  ORDER BY HESAP ";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public int yilsonu_cari_fisno_al() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		int E_NUMBER ;
		String sql = "SELECT  EVRAK FROM EVRAK_NO WITH (HOLDLOCK, ROWLOCK) ";
		PreparedStatement stmt = akt_con.prepareStatement(sql);
		rss = stmt.executeQuery();
		rss.next();
		E_NUMBER = rss.getInt("EVRAK");
		E_NUMBER = E_NUMBER + 1 ;
		//******** KAYIT
		sql = "UPDATE EVRAK_NO SET EVRAK =" + E_NUMBER + "  WHERE EID = 1";
		stmt = akt_con.prepareStatement(sql);
		stmt.executeUpdate();
		stmt.close();
		//**************
		return E_NUMBER;	
	}
	public void yilsonu_cari_dekont_kaydet(String bhes,String tar,int evrak,String bcins,Double bkur,Double borc ,
			String alhes,String acins,Double alkur,Double alacak,String izahat,String kod,String user) throws SQLException, ClassNotFoundException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql  = "INSERT INTO SATIRLAR (HESAP,TARIH,H,EVRAK,CINS,KUR,BORC,ALACAK,KOD,[USER]) " +
				" VALUES (?,?,?,?,?,?,?,?,?,?)" ;
		PreparedStatement stmt = null;
		stmt = akt_con.prepareStatement(sql);
		stmt.setString(1, bhes);
		stmt.setString(2, tar);
		stmt.setString(3, "B");
		stmt.setInt(4, evrak);
		stmt.setString(5, bcins);
		stmt.setDouble(6, bkur);
		stmt.setDouble(7,  Math.abs(borc ));
		stmt.setDouble(8, 0);
		stmt.setString(9, kod);
		stmt.setString(10, user);
		stmt.executeUpdate();
		//**********************************
		PreparedStatement stmt2 = null;
		sql  = "INSERT INTO SATIRLAR (HESAP,TARIH,H,EVRAK,CINS,KUR,BORC,ALACAK,KOD,[USER]) " +
				" VALUES (?,?,?,?,?,?,?,?,?,?)" ; 
		stmt2 = akt_con.prepareStatement(sql);
		stmt2.setString(1, alhes);
		stmt2.setString(2, tar);
		stmt2.setString(3, "A");
		stmt2.setInt(4, evrak);
		stmt2.setString(5, acins);
		stmt2.setDouble(6, alkur);
		stmt2.setDouble(7, 0);
		stmt2.setDouble(8,  Math.abs(alacak ));
		stmt2.setString(9, kod);
		stmt2.setString(10, user);
		stmt2.executeUpdate();
		//************** IZAHAT  *********
		PreparedStatement stmt3 = null;
		sql = "INSERT INTO IZAHAT (EVRAK,IZAHAT) VALUES ( ?,? )" ;
		stmt3 = akt_con.prepareStatement(sql);
		stmt3.setInt(1, evrak);
		stmt3.setString(2, izahat);
		stmt3.executeUpdate();
		stmt.close();
		stmt2.close();
		stmt3.close();
	}
	public int aktar_hesap_plani_kayit_adedi () throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = "SELECT COUNT( HESAP) AS SAYI FROM HESAP ";
		PreparedStatement stmt = akt_con.prepareStatement(sql);
		rss = stmt.executeQuery();
		rss.next();
		return rss.getInt("SAYI");
	} 
	public void cari_firma_adi_kayit(String fadi) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql  = "UPDATE OZEL SET FIRMA_ADI = N'" + fadi + "'" ;
		PreparedStatement stmt = null;
		kONTROL();
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		stmt.close();
	}
	public ResultSet sms_cari_pln(String nerden) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = "SELECT " + nerden + " ,UNVAN ,'' AS GRUP ,'' AS DURUM ,HESAP.HESAP ,'' as GON_ZAMANI," + 
				"[USER] FROM HESAP  LEFT OUTER JOIN HESAP_DETAY on HESAP.HESAP = HESAP_DETAY.D_HESAP " + 
				" WHERE SMS_GONDER = 'TRUE' ORDER BY HESAP ";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet ozel_mizan(String h1 , String h2,String t1,String t2,String c1,String c2,String k1, String k2, String o1 , String o2) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = "SELECT s.HESAP, (SELECT UNVAN FROM HESAP h WHERE s.HESAP    = h.HESAP "
				+ "	AND h.HESAP_CINSI BETWEEN N'"+ c1 + "' AND '"+ c2 +"' AND h.KARTON BETWEEN N'"+ k1 +"' AND N'" + k2 + "' ) as UNVAN ,"
				+ " (SELECT HESAP_CINSI FROM HESAP h   WHERE s.HESAP    = h.HESAP "
				+ "	AND h.HESAP_CINSI BETWEEN N'"+ c1 + "' AND '"+ c2 +"' AND h.KARTON BETWEEN N'"+ k1 +"' AND N'" + k2 +"' ) as HESAP_CINSI ,"
				+ " ISNULL(ROUND ((SELECT ROUND(SUM(SATIRLAR.ALACAK),2)  - ROUND(SUM(SATIRLAR.BORC),2)   FROM SATIRLAR   "
				+ " WHERE  SATIRLAR.HESAP = s.HESAP  and TARIH <  '"+ t1 +"'  ),2) ,0) as ONCEKI_BAKIYE  ,"
				+ " ISNULL( (SELECT SUM(SATIRLAR.BORC)  FROM   SATIRLAR     WHERE   SATIRLAR.HESAP    = s.HESAP     "
				+ " AND TARIH BETWEEN  '"+ t1 +"' AND  '"+ t2 +" 23:59:59.998'  ) ,0) as BORC ,"
				+ " ISNULL( (SELECT SUM(SATIRLAR.ALACAK)  FROM SATIRLAR      WHERE  SATIRLAR.HESAP    = s.HESAP"
				+ " AND TARIH BETWEEN '"+ t1+ "' AND '"+ t2 +" 23:59:59.998'  ) ,0) as ALACAK,"
				+ " ROUND(ISNULL( (SELECT SUM(SATIRLAR.ALACAK)  FROM SATIRLAR WHERE  SATIRLAR.HESAP = s.HESAP AND TARIH BETWEEN "
				+ " '"+ t1 +"' AND  '"+ t2 +"'  ) ,0) -	"
				+ " ISNULL( (SELECT SUM(SATIRLAR.BORC)  FROM SATIRLAR WHERE  SATIRLAR.HESAP = s.HESAP  "
				+ " AND TARIH BETWEEN 	  '"+ t1 +"' AND  '"+t2 + " 23:59:59.998'  ) ,0),2)  as BAK_KVARTAL ,"
				+ " ROUND(ISNULL( (SELECT SUM(SATIRLAR.ALACAK) - SUM(SATIRLAR.BORC) FROM SATIRLAR "
				+ " WHERE SATIRLAR.HESAP = s.HESAP and TARIH <  '"+ t2 + "  23:59:59.998' ) ,0),2) as BAKIYE"
				+ "	FROM SATIRLAR s "  
				+ " WHERE s.HESAP > N'"+ h1 +"' AND  s.HESAP < N'"+ h2+"'  "
				+ " GROUP BY s.HESAP " + o1 + " " + o2 + "" ;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet evrak_ogren(String text) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = "SELECT EVRAK FROM IZAHAT WHERE  IZAHAT LIKE N'%" + text + "%' ";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet eksik_kur_okuma(String hesap,String t1,String t2,String kur) throws ClassNotFoundException, SQLException
	{
		String str1 ;
		str1 = "" ;
		//str2 = "" ;
		if (BAGLAN.kurDizin.dIZIN_CINS.equals("L"))
			str1 = "OK_Kur" + BAGLAN.kurDizin.kOD + ".dbo.kurlar  " ;
		else
		{
			if ( BAGLAN.cariDizin.sERVER.equals(BAGLAN.kurDizin.sERVER))
				str1 = "OK_Kur" + BAGLAN.kurDizin.kOD + ".dbo.kurlar  " ;
				//str2 = "";
//			else
//			{
//				str2 = "EXEC sp_configure 'show advanced options', 1 " +
//						" RECONFIGURE GO " +
//						" EXEC sp_configure 'ad hoc distributed queries', 1 " +
//						" RECONFIGURE GO ";
//				str1 = "OPENROWSET('SQLOLEDB','" + BAGLAN.kurDizin.sERVER + "\\" + BAGLAN.kurDizin.iNSTANCE + "';'" + BAGLAN.kurDizin.kULLANICI +  "';'" + BAGLAN.kurDizin.sIFRESI +  "','SELECT * FROM [OK_Kur" + BAGLAN.kurDizin.kOD + "].[dbo].[kurlar]  ') ";
//			}
		}
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql =   " SELECT DISTINCT   convert(varchar(10), SATIRLAR.TARIH, 102) as Tarih  " +
				" FROM SATIRLAR   LEFT OUTER JOIN " + str1 + " AS k WITH (INDEX (IX_KUR))  " +
				" ON k.Tarih = CONVERT(VARCHAR(25), SATIRLAR.TARIH, 121) AND k.Kur = '" + kur + "' " +
				" WHERE HESAP ='" + hesap + "' AND  k.kur IS NULL " +
				" AND SATIRLAR.TARIH BETWEEN  '" + t1 + "'  AND '" + t2 + " 23:59:59.998'" +
				" ORDER BY   convert(varchar(10), SATIRLAR.TARIH, 102) ";
		
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet sql_sorgu(String sql)  throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public String[] cari_adres_oku (String kodu) throws ClassNotFoundException, SQLException
	{
		String[] bilgi = new String[6];
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = "SELECT ADRES_1,ADRES_2,SEMT,SEHIR,VERGI_DAIRESI ," + 
				" VERGI_NO" + 
				" FROM HESAP_DETAY " + 
				" WHERE D_HESAP = N'" + kodu + "'" ;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		if (!rss.isBeforeFirst() ) {  
		}
		else
		{
			rss.next();
			bilgi[0] = rss.getString("ADRES_1");
			bilgi[1] = rss.getString("ADRES_2");
			bilgi[2] = rss.getString("SEMT");
			bilgi[3] = rss.getString("SEHIR");
			bilgi[4] = rss.getString("VERGI_DAIRESI");
			bilgi[5] = rss.getString("VERGI_NO");
		}
		return bilgi;
	}
	public ResultSet hesap_adi_auto(String hesap) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement("SELECT   HESAP, HESAP_CINSI,  KARTON, UNVAN FROM HESAP   " + 
				" WHERE HESAP  Like N'" + hesap + "%'  ORDER BY HESAP");
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet gunisl_proc(String t1 ,String t2) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		kONTROL();
		CallableStatement cstmt = con.prepareCall("{call GUNLUK_ISLEM(?,?)}");
		cstmt.setString(1,t1); 
		cstmt.setString(2, t2); 
		cstmt.execute();
		rss = cstmt.getResultSet();
		return rss ; 
	}
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
		sql = "CREATE NONCLUSTERED INDEX [IX_LOGLAMA] ON [dbo].[LOGLAMA](	[TARIH] ASC,	[EVRAK] ASC , [USER_NAME] ASC "
				+ " )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
	}
	public ResultSet karton_mizan(String h1, String h2, String t1, String t2, String c1, String c2, String k1,
			String k2, String o1, String o2) throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String tARIH = "" ;
		if(! t1.equals("1900.01.01") || ! t2.equals("2100.12.31"))
			tARIH = " AND TARIH BETWEEN '" + t1 + "' AND '"  + t2  + " 23:59:59.998'" ;
		String sql = "SELECT HESAP.KARTON,SATIRLAR.HESAP,HESAP.UNVAN,HESAP.HESAP_CINSI AS H_CINSI," + 
				" ROUND(SUM(SATIRLAR.BORC),2) AS BORC, ROUND(SUM(SATIRLAR.ALACAK),2) AS ALACAK, " + 
				" ROUND(SUM(SATIRLAR.ALACAK),2) - ROUND(SUM(SATIRLAR.BORC),2) AS BAKIYE" +
				" FROM SATIRLAR  ,HESAP " +
				" WHERE SATIRLAR.HESAP = HESAP.HESAP " +
				" AND SATIRLAR.HESAP BETWEEN N'" + h1 + "' AND N'" + h2 + "'" +
				tARIH + 
				" AND HESAP.HESAP_CINSI BETWEEN N'" + c1 + "' AND '" + c2 + "'" +
				" AND HESAP.KARTON BETWEEN N'" + k1 + "' AND N'" + k2 + "' " +
				" GROUP BY HESAP.KARTON,SATIRLAR.HESAP, HESAP.UNVAN, HESAP.HESAP_CINSI " + o1 + " " + o2 + " " ;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	@Override
	public ResultSet ekstre_proc(String hesap, String t1, String t2) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		kONTROL();
		CallableStatement cstmt = con.prepareCall("{call EKSTRE(?,?,?)}");
		cstmt.setString(1,t1); 
		cstmt.setString(2, t2); 
		cstmt.setString(3, hesap);
		cstmt.execute();
		rss = cstmt.getResultSet();
		return rss ; 
		//String SPsql = "EXEC EKSTRE ?,?,?";   // for stored proc taking 2 parameters
		//PreparedStatement ps = con.prepareStatement(SPsql);
		//ps.setEscapeProcessing(true);
		//ps.setQueryTimeout(<timeout value>);
		//ps.setString(1,t1);
		//ps.setString(2, t2);
		//ps.setString(3, hesap);
		//rss= ps.executeQuery();
	}
	private void kONTROL() throws SQLException
	{
		if(! con.isValid(0))
			baglan();
	}
	@Override
	public ResultSet yilsonu_hp_pln() throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement("SELECT   CAST(0 as bit) ,[HESAP]"
				+ "      ,[UNVAN]"
				+ "      ,[KARTON]"
				+ "      ,[HESAP_CINSI]"
				+ "      ,[USER] FROM HESAP    ORDER BY HESAP ");
		rss = stmt.executeQuery();
		return rss;	
	}
	@Override
	public int cari_tah_fisno_al(String tur) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		int E_NUMBER ;
		String sql = "SELECT  NO  FROM TAH_EVRAK  WITH (HOLDLOCK, ROWLOCK)  WHERE CINS = '" + tur + "'";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		rss.next();
		E_NUMBER = rss.getInt("NO");
		E_NUMBER = E_NUMBER + 1 ;
		//******** KAYIT
		sql = "UPDATE TAH_EVRAK SET NO =" + E_NUMBER + "  WHERE CINS = '" + tur + "'";
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		stmt.close();
		//**************
		return E_NUMBER;	
	}
	@Override
	public void tah_ayar_kayit(String adi, String adr1, String adr2, String vdvn, String amail, String diger,
			InputStream resim , InputStream kase) throws ClassNotFoundException, SQLException, IOException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql  = "INSERT INTO TAH_AYARLAR (FIR_ISMI,ADR_1,ADR_2,VD_VN,MAIL,DIGER,LOGO,KASE)" +
				" VALUES (?,?,?,?,?,?,?,?)" ;
		PreparedStatement stmt = null;
		kONTROL();
		stmt = con.prepareStatement(sql);
		stmt.setString(1, adi);
		stmt.setString(2, adr1);
		stmt.setString(3, adr2);
		stmt.setString(4, vdvn);
		stmt.setString(5, amail);
		stmt.setString(6, diger);
		if (  resim != null)
		{
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			for (int readNum; (readNum = resim.read(buf)) != -1;) {
				bos.write(buf, 0, readNum);
			}
			byte[] bytes = bos.toByteArray();
			stmt.setBytes(7,bytes);
		}
		else
			stmt.setBytes(7,null);
		if (  kase != null)
		{
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			byte[] buf = new byte[1024];
			for (int readNum; (readNum = kase.read(buf)) != -1;) {
				bos.write(buf, 0, readNum);
			}
			byte[] bytes = bos.toByteArray();
			stmt.setBytes(8,bytes);
		}
		else
			stmt.setBytes(8,null);
		stmt.executeUpdate();
		stmt.close();
	}
	@Override
	public void tah_ayar_sil() throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql = "DELETE FROM TAH_AYARLAR " ;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		stmt.close();
	}
	@Override
	public ResultSet tah_ayar_oku() throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM TAH_AYARLAR ");
		rss = stmt.executeQuery();
		return rss;	
	}
	@Override
	public void tah_kayit(int cins, int tur, String evrak, String tarih, String ckodu, String akodu,
			String aciklama, double tutar ,String dvzcins,String posbanka) throws ClassNotFoundException, SQLException, IOException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql = "DELETE FROM TAH_DETAY WHERE evrak = '"+ evrak + "' AND CINS = '" + cins + "' " ;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		sql  = "INSERT INTO TAH_DETAY (EVRAK,TARIH,C_HES,A_HES,CINS,TUTAR,TUR,ACIKLAMA,DVZ_CINS,POS_BANKA)" +
				" VALUES (?,?,?,?,?,?,?,?,?,?)" ;
		stmt = null;
		kONTROL();
		stmt = con.prepareStatement(sql);
		stmt.setString(1, evrak);
		stmt.setString(2, tarih);
		stmt.setString(3, ckodu);
		stmt.setString(4, akodu);
		stmt.setInt(5, cins);
		stmt.setDouble(6, tutar );
		stmt.setInt(7, tur);
		stmt.setString(8, aciklama);
		stmt.setString(9, dvzcins);
		stmt.setString(10, posbanka);
		stmt.executeUpdate();
		stmt.close();
	}
	@Override
	public ResultSet tah_oku(String no, int cins) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM TAH_DETAY  WHERE EVRAK = '"+ no +"' AND CINS = '" + cins + "'");
		rss = stmt.executeQuery();
		return rss;	
	}
	@Override
	public void tah_sil(String no, int cins) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql = "DELETE FROM TAH_DETAY WHERE evrak = '"+ no + "' AND CINS = '" + cins + "' " ;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		stmt.close();
	}
	@Override
	public void tah_cek_kayit(String evr, int cins, String bnk, String sb, String sr, String hsp, String brcl,
			String tar, double tut) throws ClassNotFoundException, SQLException, IOException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql  = "INSERT INTO TAH_CEK (EVRAK,CINS,BANKA,SUBE,SERI,HESAP,BORCLU,TARIH,TUTAR)" +
				" VALUES (?,?,?,?,?,?,?,?,?)" ;
		stmt = null;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.setString(1, evr);
		stmt.setInt(2, cins);
		stmt.setString(3, bnk);
		stmt.setString(4, sb);
		stmt.setString(5, sr );
		stmt.setString(6, hsp );
		stmt.setString(7, brcl );
		stmt.setString(8, tar );
		stmt.setDouble(9, tut);
		stmt.executeUpdate();
		stmt.close();
	}
	@Override
	public ResultSet tah_cek_doldur(String no, int cins) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		kONTROL();
		String sql = "SELECT * FROM TAH_CEK WHERE EVRAK = '"+ no +"' AND CINS = '" + cins + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	@Override
	public void tah_cek_sil(String no, int cins) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql = "DELETE FROM TAH_CEK WHERE EVRAK = '"+ no + "' AND CINS = '" + cins + "' " ;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		stmt.close();
	}
	@Override
	public void tah_cek_kayit_aktar(String no, int cins) throws ClassNotFoundException, SQLException {
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		kONTROL();
		PreparedStatement stmt = con.prepareStatement("SELECT * FROM TAH_AYARLAR ");
		ResultSet rsAyar = stmt.executeQuery();
		String sql = "SELECT * FROM TAH_CEK WHERE EVRAK = '"+ no +"' AND CINS = '" + cins + "'";
		stmt = con.prepareStatement(sql);
		ResultSet rsCekler = stmt.executeQuery();
		rsAyar.next();
		String sqll = "INSERT INTO CEK (LOGO,FIR_ISMI,ADR_1,ADR_2,VD_VN,MAIL,DIGER,KASE ," +
				 " BANKA,SUBE,SERI,HESAP,BORCLU,TARIH,TUTAR	) ";
		sqll += "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
		GLOBAL.CekPrintcon = GLOBAL.myCekPrintConnection();
		PreparedStatement stmtcek = GLOBAL.CekPrintcon.prepareStatement(sqll);
		while(rsCekler.next())
		{
			byte[] img = rsAyar.getBytes("LOGO");
			stmtcek.setBytes(1, img);
			stmtcek.setString(2, rsAyar.getString("FIR_ISMI"));
			stmtcek.setString(3, rsAyar.getString("ADR_1"));
			stmtcek.setString(4, rsAyar.getString("ADR_2"));
			stmtcek.setString(5, rsAyar.getString("VD_VN"));
			stmtcek.setString(6, rsAyar.getString("MAIL"));
			stmtcek.setString(7, rsAyar.getString("DIGER"));
			stmtcek.setBytes(8, rsAyar.getBytes("KASE"));
			stmtcek.setString(9, rsCekler.getString("BANKA"));
			stmtcek.setString(10, rsCekler.getString("SUBE"));
			stmtcek.setString(11, rsCekler.getString("SERI"));
			stmtcek.setString(12, rsCekler.getString("HESAP"));
			stmtcek.setString(13, rsCekler.getString("BORCLU"));
			stmtcek.setString(14, TARIH_CEVIR.tarih_ters(rsCekler.getDate("TARIH").toString()));
			stmtcek.setDouble(15, rsCekler.getDouble("TUTAR"));
			stmtcek.executeUpdate();
		}
		stmtcek.close();
	}
	@Override
	public ResultSet tah_listele(int cins, int tur, String ilktarih, String sontarih, String ilkevr, String sonevr,String ilkck,String sonck,String pos)
			throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String cinString = "" , turString="" ,posString = "" ;
		if(cins !=0)
			cinString = " CINS = '" + (cins - 1) + "' AND";
		if(tur != 0)
			turString = " TUR = '" + (tur-1) + "' AND";
		if(! pos.equals("Hepsi"))
			posString = " POS_BANKA = '" + pos + "' AND";
		String sql = " SELECT EVRAK,TARIH ,C_HES as CARI_HESAP,A_HES as ADRES_HESAP ,CASE CINS  WHEN '0' THEN 'Tahsilat'  WHEN '1' THEN 'Tediye' END as CINS ," +
				 " CASE TUR  WHEN '0' THEN 'Nakit'  WHEN '1' THEN 'Cek' WHEN '2' THEN 'Kredi Kartı' END as TUR,POS_BANKA, " +
				 " [DVZ_CINS], [TUTAR]  " +
				" FROM TAH_DETAY " +
				" WHERE  " + cinString  + turString  + posString +
				" TARIH >= '" + ilktarih + "' AND TARIH < '" + sontarih + "' " + 
				" AND EVRAK >= '" + ilkevr + "' AND EVRAK < '" + sonevr + "' " + 
				" AND C_HES >= '" + ilkck + "' AND C_HES < '" + sonck + "' " + 
				" ORDER BY TARIH ,EVRAK" ;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	@Override
	public void yilsonu_tahsilat_bilgi_kayit() throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql  = "INSERT INTO TAH_AYARLAR (FIR_ISMI,ADR_1,ADR_2,VD_VN,MAIL,DIGER,LOGO,KASE)" +
				" VALUES (?,?,?,?,?,?,?,?)" ;
		PreparedStatement stmt = null;
		kONTROL();
		ResultSet ayar = tah_ayar_oku();
		ayar.next();
		stmt = akt_con.prepareStatement(sql);
		stmt.setString(1, ayar.getString("FIR_ISMI"));
		stmt.setString(2, ayar.getString("ADR_1"));
		stmt.setString(3, ayar.getString("ADR_2"));
		stmt.setString(4, ayar.getString("VD_VN"));
		stmt.setString(5, ayar.getString("MAIL"));
		stmt.setString(6, ayar.getString("DIGER"));
		stmt.setBytes(7,ayar.getBytes("LOGO"));
		stmt.setBytes(8,ayar.getBytes("KASE"));
		stmt.executeUpdate();
		stmt.close();
	}
	@Override
	public ResultSet pos_banka_oku() throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement("SELECT DISTINCT POS_BANKA FROM TAH_DETAY  WHERE TUR = '2'");
		rss = stmt.executeQuery();
		return rss;	
	}
	@Override
	public int cari_tahsonfisno(int cins) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = "SELECT MAX(CONVERT(int,EVRAK)) AS MAX_NO  FROM TAH_DETAY WHERE CINS ='" + cins + "'";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		rss.next();
		return rss.getInt("MAX_NO");
	}
	@Override
	public ResultSet banka_sube(String nerden) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = " SELECT  DISTINCT " + nerden +
				" FROM TAH_CEK " +
				" ORDER BY " + nerden;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}
	@Override
	public void cari_kod_degis_tahsilat(String t1, String t2) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql = "UPDATE TAH_DETAY SET C_HES = N'" + t2 + "'  WHERE C_HES = N'" + t1 + "'";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
}


