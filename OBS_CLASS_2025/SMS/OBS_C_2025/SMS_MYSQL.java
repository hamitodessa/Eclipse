package OBS_C_2025;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SMS_MYSQL implements ISMS{

	static Connection con = null;
	static Statement stmt = null;
	
	public void baglan() throws SQLException
	{
		String cumle = "jdbc:sqlserver://" + BAGLAN.smsDizin.cONN_STR + ";";
	    con = DriverManager.getConnection(cumle,BAGLAN.smsDizin.kULLANICI,BAGLAN.smsDizin.sIFRESI);
	}
	@Override
	public void sMS_SIFIR_L(String kod, String dizin_yeri, String dizin, String ins, String kull, String sifre)
			throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
 		con = null;  
 		String cumle = "";
         cumle = "jdbc:mysql://localhost;instanceName=" + ins + ";";
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
         cumle = "jdbc:mysql://localhost;instanceName=" + ins + ";database=" + VERITABANI + ";";
         con = DriverManager.getConnection(cumle,kull,sifre);
         create_table();
         stmt.close();
         con.close();
		
	}

	@Override
	public void sMS_SIFIR_S(String server, String ins, String kull, String sifre, String kod)
			throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
  		con = null;  
  		String VERITABANI = "OK_Sms" + kod;
  		String cumle = "";
  		stmt = null;
        String sql =null;
  		cumle = "jdbc:mysql://" + server + ";instanceName=" + ins + ";";
  		con = DriverManager.getConnection(cumle,kull,sifre);
             sql = "CREATE DATABASE [" + VERITABANI + "]";
             stmt = con.createStatement();  
             stmt.executeUpdate(sql);
             cumle = "jdbc:mysql://" + server + ";instanceName=" + ins + ";database=" + VERITABANI + ";";
             con = DriverManager.getConnection(cumle,kull,sifre);
             create_table();
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
     sql = "CREATE TABLE GIDEN_RAPOR ( EID int identity(1,1) CONSTRAINT PKEID PRIMARY KEY , "
    		 + " [TARIH] DATETIME ,"
    		 + " [KONU] nvarchar(50),"
    		 + " [RAPOR] nvarchar(50),"
    		 + " [ALICI] nvarchar(50),"
    		 + " [GONDEREN] nvarchar(50),"
    		 + " [ACIKLAMA] nvarchar(300),"
    		 + "  [USER_NAME] nvarchar(20),) ";
     stmt = con.createStatement();  
     stmt.executeUpdate(sql);
   
		
	}
	@Override
	public ResultSet mail_giris_bak() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean kod_ismi(String kodu) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void mail_giris_sil(String mail) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mail_giris_yaz(String mail, String unv, String grpkod, String kod, boolean drm, String unm) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ResultSet mail_grup_bak() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet mail_alici_doldur() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet mail_giden_bak(String user) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void giden_rapor_yaz(String uname,String tar ,String msj,String mail,String hsp, String unv,String gond,String konu) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ResultSet sms_giris_bak() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet sms_grup_bak() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet sms_giden_bak(String user) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void sms_giris_sil(String tel) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void sms_giris_yaz(String tel, String unv, String grpkod, String kod, boolean drm, String unm)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ResultSet sms_alici_doldur() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void sms_yaz(String uname, String tar, String msj, String mobile, String hsp, String unv)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void create_table_log() throws SQLException {
		// TODO Auto-generated method stub
		
	}

}
