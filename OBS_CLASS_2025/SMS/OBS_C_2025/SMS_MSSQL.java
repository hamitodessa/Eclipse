package OBS_C_2025;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SMS_MSSQL implements ISMS{

	static Connection con = null;
	static Statement stmt = null;

	public void baglan() throws SQLException
	{
		String cumle = "jdbc:sqlserver://" + BAGLAN.smsDizin.cONN_STR + ";";
		con = DriverManager.getConnection(cumle,BAGLAN.smsDizin.kULLANICI,BAGLAN.smsDizin.sIFRESI);
	}
	@Override
	public void sMS_SIFIR_L(String kod, String dizin_yeri, String dizin, String ins, String kull, String sifre,String port) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		con = null;  
		String cumle = "";
		cumle = "jdbc:sqlserver://localhost;instanceName=" + ins + ";";
		con = DriverManager.getConnection(cumle,kull,sifre);
		String VERITABANI = "OK_Sms" + kod;
		stmt = null;
		String sql =null;
		if (dizin_yeri == "default")
			sql = "CREATE DATABASE [" + VERITABANI + "]";
		else
			sql = "CREATE DATABASE [" + VERITABANI + "]  ON PRIMARY " + " ( NAME = N'" + VERITABANI + "', FILENAME = N'" + dizin 	+ "\\" + VERITABANI + ".mdf  ) " + " LOG ON " + " ( NAME = N'" + VERITABANI + "_log', FILENAME = N'" + dizin + "\\" + VERITABANI + "_log.ldf' ) ";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		cumle = "jdbc:sqlserver://localhost;instanceName=" + ins + ";database=" + VERITABANI + ";";
		con = DriverManager.getConnection(cumle,kull,sifre);
		create_table();
		//
		if (dizin_yeri == "default")
			sql = "CREATE DATABASE [" + VERITABANI + "_LOG" + "]";
		else
			sql = "CREATE DATABASE [" + VERITABANI + "_LOG" + "]  ON PRIMARY " + " ( NAME = N'" + VERITABANI + "_LOG" + "', FILENAME = N'" + dizin 	+ "\\" + VERITABANI + ".mdf  ) " + " LOG ON " + " ( NAME = N'" + VERITABANI + "_LOG" + "_log', FILENAME = N'" + dizin + "\\" + VERITABANI + "_LOG" + "_log.ldf' ) ";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		cumle = "jdbc:sqlserver://localhost;instanceName=" + ins + ";database=" + VERITABANI + "_LOG" + ";";
		con = DriverManager.getConnection(cumle,kull,sifre);
		create_table_log();

		//
		//SQLITE LOG DOSYASI OLUSTUR
		if (GLOBAL.dos_kontrol(GLOBAL.SURUCU + VERITABANI + ".DB") == false)
		{
			String dsy = GLOBAL.SURUCU + VERITABANI + "_mSSQL"+ ".DB" ;
			Connection sQLITEconn = DriverManager.getConnection("jdbc:sqlite:" + dsy   ) ;
			GLOBAL.create_table_log(dsy ,"",BAGLAN_LOG.smsLogDizin);
		}
		//
		stmt.close();
		con.close();
	}

	@Override
	public void sMS_SIFIR_S(String server, String ins, String kull, String sifre, String kod) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		con = null;  
		String VERITABANI = "OK_Sms" + kod;
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
		create_table();
		//
		sql = "CREATE DATABASE [" + VERITABANI + "_LOG" + "]";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		cumle = "jdbc:sqlserver://" + server + ";instanceName=" + ins + ";database=" + VERITABANI + "_LOG" + ";";
		con = DriverManager.getConnection(cumle,kull,sifre);
		create_table_log();
		//
		//SQLITE LOG DOSYASI OLUSTUR
		if (GLOBAL.dos_kontrol(GLOBAL.SURUCU + VERITABANI + ".DB") == false)
		{
			String dsy = GLOBAL.SURUCU + VERITABANI + "_mSSQL"+ ".DB" ;
			Connection sQLITEconn = DriverManager.getConnection("jdbc:sqlite:" + dsy  ) ;
			GLOBAL.create_table_log(dsy ,"",BAGLAN_LOG.smsLogDizin);
		}
		//
		stmt.close();
		con.close();

	}

	@Override
	public void create_table() throws SQLException {
		String sql = null;
		sql = "CREATE TABLE [dbo].[SMS_HESAP]( "
				+ " [TEL_NO] [nvarchar](15) NOT NULL,"
				+ "[UNVAN] [nvarchar](50) NULL,"
				+ "[GRUP] [nvarchar](15) NULL,"
				+ "[KODU] [nvarchar](12) NULL,"
				+ "[DURUM] [bit] NULL,"
				+ "[USER_NAME] [nvarchar](20) NULL,"
				+ "CONSTRAINT [IX_HESAP] PRIMARY KEY CLUSTERED ([TEL_NO] ASC )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE"
				+ " = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]) ON [PRIMARY]";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql= "CREATE NONCLUSTERED INDEX [IDX_SMS_HESAP] ON [dbo].[SMS_HESAP]( "
				+ "[TEL_NO] ASC, "
				+ " [UNVAN] ASC, "
				+ " [GRUP] ASC "
				+ " )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = "
				+ " OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY] ";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE [dbo].[SMS_BILGILERI]( "
				+ "[SID] [int] IDENTITY(1,1) NOT NULL,"
				+ " [USER_NAME] [nvarchar](20) NULL,"
				+ " [TARIH] [datetime] NULL,"
				+ " [MOBILE] [nvarchar](12) NULL,"
				+ " [MESAJ] [nvarchar](140) NULL,"
				+ " [HESAP] [nvarchar](12) NULL,"
				+ "  [UNVAN] [nvarchar](50) NULL,"
				+ "  CONSTRAINT [IX_SID] PRIMARY KEY CLUSTERED (	[SID] ASC"
				+ "  )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS ="
				+ "  ON) ON [PRIMARY]) ON [PRIMARY]";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql= "CREATE NONCLUSTERED INDEX [IDX_SMS_BILGILERI] ON [dbo].[SMS_BILGILERI]( "
				+ "  [TARIH] ASC,"
				+ "   [MOBILE] ASC,"
				+ "  [HESAP] ASC,"
				+ "  [UNVAN] ASC"
				+ "  )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE ="
				+ "  OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE [dbo].[MAIL_HESAP]( "
				+ "  [MAIL] [nvarchar](50) NOT NULL ,"
				+ "  [UNVAN] [nvarchar](50) NULL,"
				+ "  [GRUP] [nvarchar](15) NULL,"
				+ "  [KODU] [nvarchar](12) NULL,"
				+ "  [DURUM] [bit] NULL,"
				+ "  [USER_NAME] [nvarchar](20) NULL,"
				+ "  CONSTRAINT [IX_MAIL] PRIMARY KEY CLUSTERED (	[MAIL] ASC"
				+ "  )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) "
				+ "   ON [PRIMARY]) ON [PRIMARY]";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE NONCLUSTERED INDEX [IDX_MAIL_HESAP] ON [dbo].[MAIL_HESAP]( "
				+ "  [MAIL] ASC,"
				+ "  [UNVAN] ASC,"
				+ "  [GRUP] ASC,"
				+ "  [KODU] ASC"
				+ "  )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF,"
				+ "  ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE [dbo].[MAIL_BILGILERI]( "
				+ "  [MID] [int] IDENTITY(1,1) NOT NULL,"
				+ "  [TARIH] [datetime] NULL,"
				+ "  [MAIL] [nvarchar](50) NULL,"
				+ "  [KONU] [nvarchar](50) NULL,"
				+ "  [MESAJ] [nvarchar](300) NULL,"
				+ "   [HESAP] [nvarchar](12) NULL,"
				+ "  [UNVAN] [nvarchar](50) NULL,"
				+ "  [GONDEREN] [nvarchar](50) NULL,"
				+ "  [USER_NAME] [nvarchar](20) NULL,"
				+ "  CONSTRAINT [IX_MID] PRIMARY KEY CLUSTERED (	[MID] ASC"
				+ "   )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, "
				+ "  ALLOW_PAGE_LOCKS = ON) ON [PRIMARY] ) ON [PRIMARY]";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql= "CREATE NONCLUSTERED INDEX [IDX_MAIL_BILGILERI] ON [dbo].[MAIL_BILGILERI]( "
				+ " [TARIH] ASC,"
				+ " [MAIL] ASC,"
				+ " [HESAP] ASC,"
				+ " [UNVAN] ASC,"
				+ " [GONDEREN] ASC,"
				+ " [KONU] ASC"
				+ "  )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF,"
				+ "  ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);

	}
	public ResultSet mail_giris_bak() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = "SELECT *  FROM MAIL_HESAP ORDER BY UNVAN ";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}
	public boolean kod_ismi(String kodu) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		PreparedStatement stmt = con.prepareStatement("SELECT MAIL  FROM MAIL_HESAP WHERE MAIL =N'" + kodu + "'");
		rss = stmt.executeQuery();
		rss.next();
		int count=0;
		count = rss.getRow();
		boolean result;
		if (count  != 0) 
		{
			result = true;
		}
		else
		{
			result = false;
		}
		return result;	
	}
	public void mail_giris_sil(String mail) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
		String sql = "DELETE  FROM MAIL_HESAP WHERE MAIL ='" + mail + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
	public void mail_giris_yaz(String mail , String unv ,String grpkod ,String kod,boolean drm , String unm ) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql  = "INSERT INTO MAIL_HESAP (MAIL,UNVAN,GRUP,KODU,DURUM,USER_NAME) " +
				" VALUES (?,?,?,?,?,?)" ;
		PreparedStatement stmt = null;
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
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = "SELECT DISTINCT GRUP  FROM MAIL_HESAP ORDER BY GRUP ";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}
	public ResultSet mail_alici_doldur() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = "SELECT MAIL,UNVAN,GRUP,DURUM,KODU,'' AS GON_ZAMANI,USER_NAME AS [USER]  FROM MAIL_HESAP ORDER BY UNVAN ";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}
	public ResultSet mail_giden_bak(String user) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = "SELECT TARIH,MAIL,KONU,MESAJ,HESAP,UNVAN,USER_NAME AS [USER]  FROM MAIL_BILGILERI ";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}
	public void giden_rapor_yaz(String uname,String tar ,String msj,String mail,String hsp, String unv,String gond,String konu) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql = "INSERT INTO MAIL_BILGILERI (USER_NAME,TARIH,MAIL,KONU,MESAJ,HESAP,UNVAN,GONDEREN) " +
				"VALUES (?,?,?,?,?,?,?,?)";
		PreparedStatement stmt = null;
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
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql =  "SELECT *  FROM SMS_HESAP ORDER BY UNVAN ";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}
	public ResultSet sms_grup_bak() throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = "SELECT DISTINCT GRUP  FROM SMS_HESAP ORDER BY GRUP ";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}
	public ResultSet sms_giden_bak(String user) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = "SELECT TARIH,MOBILE,MESAJ,HESAP,UNVAN,USER_NAME AS [USER]  FROM SMS_BILGILERI ";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}
	public void sms_giris_sil(String tel) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver"); 
		String sql = "DELETE  FROM SMS_HESAP WHERE TEL_NO ='" + tel + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
	public void sms_giris_yaz(String tel , String unv ,String grpkod ,String kod,boolean drm , String unm ) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql  = "INSERT INTO SMS_HESAP (TEL_NO,UNVAN,GRUP,KODU,DURUM,USER_NAME) " +
				" VALUES (?,?,?,?,?,?)" ;
		PreparedStatement stmt = null;
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
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = "SELECT TEL_NO,UNVAN,GRUP,DURUM,KODU,'' AS GON_ZAMANI,USER_NAME as [USER]  FROM SMS_HESAP ORDER BY UNVAN ";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;
	}
	public void sms_yaz(String uname,String tar ,String msj,String mobile,String hsp, String unv) throws ClassNotFoundException, SQLException
	{

		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql =  "INSERT INTO SMS_BILGILERI (USER_NAME,TARIH,MOBILE,MESAJ,HESAP,UNVAN) " +
				"VALUES (?,?,?,?,?,?)";
		PreparedStatement stmt = null;
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
		sql = "CREATE NONCLUSTERED INDEX [IDX_LOGLAMA] ON [dbo].[LOGLAMA](	[TARIH] ASC,	[EVRAK] ASC , [USER_NAME] ASC "
				+ " )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
	}
}
