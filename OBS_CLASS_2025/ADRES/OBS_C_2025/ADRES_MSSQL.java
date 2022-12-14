package OBS_C_2025;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import LOGER_KAYIT.DOSYA_MSSQL;
import LOGER_KAYIT.DOSYA_MYSQL;
import LOGER_KAYIT.ILOGER_KAYIT;
import LOGER_KAYIT.TXT_LOG;
@SuppressWarnings("static-access")
public class ADRES_MSSQL implements IADRES {

	static Connection con = null;
	static Statement stmt = null;

	public void baglan() throws SQLException
	{
		String cumle = "jdbc:sqlserver://" + BAGLAN.adrDizin.cONN_STR + ";";
		con = DriverManager.getConnection(cumle,BAGLAN.adrDizin.kULLANICI,BAGLAN.adrDizin.sIFRESI);
	}
	@Override
	public void aDR_SIF_L(String kod, String dizin_yeri, String dizin, String fir_adi, String ins, String kull,
			String sifre,String port) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		con = null;  
		String cumle = "";
		if ( ! port.toString().equals("") )
		{
			port =  ":" + port ;
		}
		cumle = "jdbc:sqlserver://localhost"+ port +";instanceName=" + ins + ";";
		con = DriverManager.getConnection(cumle,kull,sifre);
		String VERITABANI = "OK_Adr" + kod;
		stmt = null;
		String sql =null;
		if (dizin_yeri == "default")
			sql = "CREATE DATABASE [" + VERITABANI + "]";
		else
			sql = "CREATE DATABASE [" + VERITABANI + "]  ON PRIMARY " + " ( NAME = N'" + VERITABANI + "', FILENAME = N'" + dizin 	+ "\\" + VERITABANI + ".mdf' ) ";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		cumle = "jdbc:sqlserver://localhost " + port + ";instanceName=" + ins + ";database=" + VERITABANI + ";";
		con = DriverManager.getConnection(cumle,kull,sifre);
		create_table(fir_adi);
		//
		if (dizin_yeri == "default")
			sql = "CREATE DATABASE [" + VERITABANI + "_LOG" + "]";
		else
			sql = "CREATE DATABASE [" + VERITABANI + "_LOG" + "]  ON PRIMARY " + " ( NAME = N'" + VERITABANI + "_LOG" + "', FILENAME = N'" + dizin 	+ "\\" + VERITABANI + "_LOG" + ".mdf' ) ";
		cumle = "jdbc:sqlserver://localhost" + port + ";instanceName=" + ins + ";";
		con = DriverManager.getConnection(cumle,kull,sifre);
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		cumle = "jdbc:sqlserver://localhost" + port + ";instanceName=" + ins + ";database=" + VERITABANI + "_LOG" + ";";
		con = DriverManager.getConnection(cumle,kull,sifre);
		create_table_log();
		//  VERITABANI DOSYASI ILK ACILIS
		ILOGER_KAYIT  vTLOG =  new DOSYA_MSSQL();
		vTLOG.Logla("Dosya Olusturuldu" ,"", BAGLAN_LOG.adrLogDizin);
		vTLOG.Logla("Firma Adi:" + fir_adi ,"", BAGLAN_LOG.adrLogDizin);

		//SQLITE LOG DOSYASI OLUSTUR
		if (GLOBAL.dos_kontrol(GLOBAL.LOG_SURUCU + VERITABANI + ".DB") == false)
		{
			String dsy =GLOBAL.LOG_SURUCU + VERITABANI + "_mSSQL"+ ".DB" ;
			@SuppressWarnings("unused")
			Connection sQLITEconn = DriverManager.getConnection("jdbc:sqlite:" +dsy  ) ;
			GLOBAL.create_table_log(dsy,fir_adi,BAGLAN_LOG.adrLogDizin);
		}
		//  TEXT DOSYASI ILK ACILIS
		ILOGER_KAYIT  tEXLOG = new TXT_LOG();
		 tEXLOG.Logla("Dosya Olusturuldu" ,"", BAGLAN_LOG.adrLogDizin);
		 tEXLOG.Logla("Firma Adi:" + fir_adi ,"", BAGLAN_LOG.adrLogDizin);
		//

		stmt.close();
		con.close();

	}
	@Override
	public void aDR_SIFIR_S(String server, String ins, String kull, String sifre, String kod, String fir_adi) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		con = null;  
		String VERITABANI = "OK_Adr" + kod;
		String cumle = "";
		stmt = null;
		String sql =null;
		cumle = "jdbc:sqlserver://" + server + ";instanceName=" + ins + ";";
		con = DriverManager.getConnection(cumle,kull,sifre);
		sql = "CREATE DATABASE [" + VERITABANI + "]";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		cumle = "jdbc:sqlserver://" + server + ";instanceName=" + ins + ";databaseName=" + VERITABANI + ";";
		con = DriverManager.getConnection(cumle,kull,sifre);
		create_table(fir_adi);
		//
		sql = "CREATE DATABASE [" + VERITABANI + "_LOG" + "]";
		cumle = "jdbc:sqlserver://" + server + ";instanceName=" + ins + ";";
		con = DriverManager.getConnection(cumle,kull,sifre);
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		cumle = "jdbc:sqlserver://" + server + ";instanceName=" + ins + ";database=" + VERITABANI + "_LOG" + ";";
		con = DriverManager.getConnection(cumle,kull,sifre);
		create_table_log();
		//  VERITABANI DOSYASI ILK ACILIS
		ILOGER_KAYIT  vTLOG =  new DOSYA_MSSQL();
		vTLOG.Logla("Dosya Olusturuldu" ,"", BAGLAN_LOG.adrLogDizin);
		vTLOG.Logla("Firma Adi:" + fir_adi ,"", BAGLAN_LOG.adrLogDizin);

		//SQLITE LOG DOSYASI OLUSTUR
		if (GLOBAL.dos_kontrol( GLOBAL.LOG_SURUCU + GLOBAL.char_degis( BAGLAN_LOG.adrLogDizin.mODUL)) == false)
		{
			String dsy =   GLOBAL.LOG_SURUCU +GLOBAL.char_degis( BAGLAN_LOG.adrLogDizin.mODUL) ;
			@SuppressWarnings("unused")
			Connection sQLITEconn = DriverManager.getConnection("jdbc:sqlite:" +dsy ) ;
			GLOBAL.create_table_log(dsy,fir_adi,BAGLAN_LOG.adrLogDizin);
		}
		//  TEXT DOSYASI ILK ACILIS
		ILOGER_KAYIT  tEXLOG = new TXT_LOG();
		 tEXLOG.Logla("Dosya Olusturuldu" ,"", BAGLAN_LOG.adrLogDizin);
		 tEXLOG.Logla("Firma Adi:" + fir_adi ,"", BAGLAN_LOG.adrLogDizin);
		//

		stmt.close();
		con.close();

	}
	@Override
	public void create_table(String fir_adi) throws SQLException {
		String sql = null;
		sql = "CREATE TABLE [dbo].[Adres]( "
				+ " [ID] [int] IDENTITY(1,1) NOT NULL ,"
				+ " [M_Kodu] [nvarchar](12)  NULL, "
				+ " [Adi] [nvarchar](35) NULL,"
				+ " [Adres_1] [nvarchar](35) NULL,"
				+ " [Adres_2] [nvarchar](35) NULL,"
				+ " [Semt] [nvarchar](25) NULL,"
				+ " [Sehir] [nvarchar](25) NULL,"
				+ " [Posta_Kodu] [nvarchar](10) NULL,"
				+ " [Vergi_Dairesi] [nvarchar](25) NULL,"
				+ " [Vergi_No] [nvarchar](15) NULL,"
				+ " [Fax] [nvarchar](25) NULL,"
				+ " [Tel_1] [nvarchar](25) NULL,"
				+ " [Tel_2] [nvarchar](25) NULL,"
				+ " [Tel_3] [nvarchar](25) NULL,"
				+ " [Ozel] [nvarchar](30) NULL,"
				+ " [Yetkili] [nvarchar](30) NULL,"
				+ " [E_Mail] [nvarchar](50) NULL,"
				+ " [Not_1] [nvarchar](30) NULL,"
				+ " [Not_2] [nvarchar](30) NULL,"
				+ " [Not_3] [nvarchar](30) NULL,"
				+ " [Aciklama] [nvarchar](50) NULL,"
				+ " [Sms_Gonder] [bit] NULL,"
				+ " [Mail_Gonder] [bit] NULL,"
				+ " [Ozel_Kod_1] [nvarchar](15) NULL,"
				+ " [Ozel_Kod_2] [nvarchar](15) NULL,"
				+ " [Web] [nvarchar](50) NULL,"
				+ " [USER] [nvarchar](15) NULL,"
				+ " [Resim] [image] NULL,"
				+ " CONSTRAINT [PKeyID] PRIMARY KEY CLUSTERED (	[ID] ASC "
				+ " )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, "
				+ " ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]) ON [PRIMARY] TEXTIMAGE_ON [PRIMARY]";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE OZEL(OZID int identity(1,1) CONSTRAINT PKeyOZID PRIMARY KEY,YONETICI nvarchar(25), YON_SIFRE nvarchar(15) , FIRMA_ADI nvarchar(50))";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE YETKILER(YETID int identity(1,1) CONSTRAINT PKeyYETID PRIMARY KEY,KULLANICI nvarchar(25), HESAP nvarchar(12), TAM_YETKI bit, GORUNTU bit )";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		// ***************OZEL NO YAZ ************
		sql = "INSERT INTO  OZEL(YONETICI,YON_SIFRE,FIRMA_ADI) VALUES ('" + GLOBAL.KULL_ADI   + "','12345' , '" + fir_adi + "')";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);

	}
	@Override
	public String adr_firma_adi() throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
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
	public void adr_firma_adi_kayit(String fadi) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
		String sql = "UPDATE OZEL SET FIRMA_ADI = N'" + fadi + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
	public ResultSet adres(String sira,String arama) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = " SELECT M_Kodu,Adi,Adres_1,Adres_2,Semt,Sehir,Vergi_Dairesi ," +
				" Vergi_No, Fax,Tel_1,Tel_2,Tel_3,Ozel,Yetkili,E_Mail,Not_1,Not_2,Not_3 ,Aciklama,Sms_Gonder,Mail_Gonder,Ozel_Kod_1,Ozel_Kod_2" +
				" ,Web ,Posta_Kodu ,Resim" +
				" FROM Adres " +
				arama +
				" ORDER by " + sira;
		Statement stmt = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		return rss;
	}
	public String kod_ismi(String kodu) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		PreparedStatement stmt = con.prepareStatement("SELECT Adi  FROM Adres WHERE M_Kodu =N'" + kodu + "'");
		rss = stmt.executeQuery();
		rss.next();
		int count=0;
		count = rss.getRow();
		String result="";
		if (count  != 0) 
		{
			result = rss.getString("Adi");
		}
		else
		{
			result = "";
		}
		return result;	
	}
	public void adres_kayit(ADRESS_DEGISKENLER aDEGIS) throws ClassNotFoundException, SQLException, IOException

	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql  = "INSERT INTO Adres (M_Kodu,Adi,Adres_1,Adres_2,Semt,Sehir,Posta_Kodu,Vergi_Dairesi,Vergi_No,Fax,Tel_1" +
				" ,Tel_2,Tel_3,Ozel,Yetkili,E_Mail,Not_1,Not_2,Not_3,Aciklama,Sms_Gonder,Mail_Gonder,Ozel_Kod_1,Ozel_Kod_2,Web,[USER],Resim) " +
				" VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)" ;
		PreparedStatement stmt = null;
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
			{
				bos.write(buf, 0, readNum);
			}
			byte[] bytes = bos.toByteArray();
			stmt.setBytes(27,bytes);
		}
		else
		{
			stmt.setBytes(27,null);
		}
		stmt.executeUpdate();
		stmt.close();
	}
	public void sil(String kod ,String adi) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
		String sql = " DELETE " +
				" FROM Adres WHERE M_Kodu = '" + kod.trim()   ;
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();

	}
	public ResultSet adr_hpl() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = "SELECT M_Kodu,Adi  FROM Adres  ORDER BY M_Kodu";
		Statement stmt = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		return rss;

	}
	public ResultSet sms_adr_hpl(String nerden) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = "SELECT " + nerden + " ,Adi ,'' AS GRUP ,'' AS DURUM ,M_Kodu ," + 
				" '' as GON_ZAMANI,[USER] FROM Adres   WHERE Mail_Gonder = 'TRUE' ORDER BY M_Kodu ";
		Statement stmt = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		return rss;
	}
	public ResultSet gdy_oku(String kod) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = " SELECT M_Kodu,Adi,Adres_1,Adres_2,Semt,Sehir,Vergi_Dairesi ," +
				" Vergi_No, Fax,Tel_1,Tel_2,Ozel,Yetkili,E_Mail,Not_1,Not_2,Not_3 " +
				" FROM Adres " +
				" WHERE M_Kodu = N'" + kod + "'";
		Statement stmt = con.createStatement( ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		rss = stmt.executeQuery(sql);
		return rss;
	}
	public String[] adres_oku (String kodu) throws ClassNotFoundException, SQLException
	{
		String[] bilgi = new String[6];
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = " SELECT M_Kodu,Adi,Adres_1,Adres_2,Semt,Sehir,Vergi_Dairesi ," +
				" Vergi_No, Fax,Tel_1,Tel_2,Ozel,Yetkili,E_Mail,Not_1,Not_2,Not_3 " +
				" FROM Adres " +
				" WHERE M_Kodu = N'" + kodu + "'" ;
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
	public void create_table_log() throws SQLException {
		// TODO Auto-generated method stub
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
}
