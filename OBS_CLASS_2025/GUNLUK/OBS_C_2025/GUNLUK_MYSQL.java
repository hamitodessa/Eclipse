package OBS_C_2025;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
         cumle = "jdbc:mysql://localhost;instanceName=" + ins + ";";
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
         cumle = "jdbc:mysql://localhost;instanceName=" + ins + ";database=" + VERITABANI + ";";
         con = DriverManager.getConnection(cumle,kull,sifre);
         create_table(fir_adi);
         stmt.close();
         con.close();
		
	}

	@Override
	public void gUN_SIFIR_S(String server, String ins, String kull, String sifre, String kod, String dizin_yeri,
			String dizin, String fir_adi) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
 		con = null;  
 		 String VERITABANI = "OK_Gun" + kod;
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
            create_table(fir_adi);
            stmt.close();
            con.close();
		
	}

	@Override
	public void create_table(String fir_adi) throws SQLException {
		String sql = null;
        sql = "SET ANSI_NULLS ON\r\n" + 
        		"\r\n" + 
        		"SET QUOTED_IDENTIFIER ON\r\n" + 
        		"\r\n" + 
        		"CREATE TABLE [dbo].[AppointmentsResources](\r\n" + 
        		"	[AppointmentID] [int] NOT NULL,\r\n" + 
        		"	[ResourceID] [int] NOT NULL,\r\n" + 
        		" CONSTRAINT [PK_AppointmentsResources] PRIMARY KEY CLUSTERED \r\n" + 
        		"(\r\n" + 
        		"	[AppointmentID] ASC,\r\n" + 
        		"	[ResourceID] ASC\r\n" + 
        		")WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]\r\n" + 
        		") ON [PRIMARY]\r\n" + 
        		"\r\n" + 
        		"ALTER TABLE [dbo].[AppointmentsResources]  WITH NOCHECK ADD  CONSTRAINT [AppointmentsResourcesAppointments] FOREIGN KEY([AppointmentID])\r\n" + 
        		"REFERENCES [dbo].[Appointments] ([ID])\r\n" + 
        		"\r\n" + 
        		"ALTER TABLE [dbo].[AppointmentsResources] NOCHECK CONSTRAINT [AppointmentsResourcesAppointments]" ;
        stmt = con.createStatement();  
        stmt.executeUpdate(sql);
        sql = "SET ANSI_NULLS ON\r\n" + 
        		"\r\n" + 
        		"SET QUOTED_IDENTIFIER ON\r\n" + 
        		"\r\n" + 
        		"SET ANSI_PADDING ON\r\n" + 
        		"\r\n" + 
        		"CREATE TABLE [dbo].[Resources](\r\n" + 
        		"      [ID] [int] IDENTITY(1,1) NOT NULL,\r\n" + 
        		"      [ResourceName] [nvarchar](255) COLLATE SQL_Latin1_General_CP1_CI_AS NOT NULL,\r\n" + 
        		"       CONSTRAINT [PK_Resources] PRIMARY KEY CLUSTERED\r\n" + 
        		"(\r\n" + 
        		"      [ID] ASC\r\n" + 
        		")WITH (PAD_INDEX  = OFF, IGNORE_DUP_KEY = OFF) ON [PRIMARY]\r\n" + 
        		") ON [PRIMARY]\r\n" + 
        		"\r\n" + 
        		"SET ANSI_PADDING OFF";
        stmt = con.createStatement();  
        stmt.executeUpdate(sql);
        sql = "SET ANSI_NULLS ON\r\n" + 
        		"SET QUOTED_IDENTIFIER ON\r\n" + 
        		"\r\n" + 
        		"CREATE TABLE [dbo].[Appointments](\r\n" + 
        		"	[ID] [int] IDENTITY(1,1) NOT NULL,\r\n" + 
        		"	[Summary] [nvarchar](255) NULL,\r\n" + 
        		"	[Start] [datetime] NOT NULL,\r\n" + 
        		"	[End] [datetime] NOT NULL,\r\n" + 
        		"	[RecurrenceRule] [nvarchar](255) NULL,\r\n" + 
        		"	[MasterEventID] [int] NULL,\r\n" + 
        		"	[Location] [nvarchar](255) NULL,\r\n" + 
        		"	[Description] [nvarchar](1000) NULL,\r\n" + 
        		"	[BackgroundID] [int] NOT NULL CONSTRAINT [DF_Appointments_BackgroundId]  DEFAULT ((1)),\r\n" + 
        		"	[StatusID] [int] NULL,\r\n" + 
        		"	[ParentID] [int] NULL,\r\n" + 
        		"	[Email] [nvarchar](255) NULL,\r\n" + 
        		"	[Visible] [bit] NOT NULL,\r\n" + 
        		" CONSTRAINT [PK_Appointments] PRIMARY KEY CLUSTERED \r\n" + 
        		"(\r\n" + 
        		"	[ID] ASC\r\n" + 
        		")WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, IGNORE_DUP_KEY = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON) ON [PRIMARY]\r\n" + 
        		") ON [PRIMARY]\r\n" + 
        		"\r\n" + 
        		"ALTER TABLE [dbo].[Appointments]  WITH NOCHECK ADD  CONSTRAINT [AppointmentsAppointments] FOREIGN KEY([ParentID])\r\n" + 
        		"REFERENCES [dbo].[Appointments] ([ID])\r\n" + 
        		"\r\n" + 
        		"ALTER TABLE [dbo].[Appointments] NOCHECK CONSTRAINT [AppointmentsAppointments]\r\n" + 
        		"";        stmt = con.createStatement();  
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
		// TODO Auto-generated method stub
		
	}

}
