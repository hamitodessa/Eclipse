package OBS_C_2025;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import LOGER_KAYIT.DOSYA_MSSQL;
import LOGER_KAYIT.ILOGER_KAYIT;
import LOGER_KAYIT.TXT_LOG;

public class GUNLUK_MSSQL implements IGUNLUK {

	static Connection con = null;
	static Statement stmt = null;


	@Override
	public void baglan() throws SQLException {
		String cumle = "jdbc:sqlserver://" + BAGLAN.gunDizin.cONN_STR + ";";
		//DriverManager.setLoginTimeout(0);
		con = DriverManager.getConnection(cumle,BAGLAN.gunDizin.kULLANICI,BAGLAN.gunDizin.sIFRESI);
	}
	@Override
	public void gUN_SIFIR_L(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		con = null;  
		String cumle = "";
		if ( ! sbilgi.getPort().toString().equals("") )
		{
			sbilgi.setPort(":" + sbilgi.getPort())    ;
		}
		cumle = "jdbc:sqlserver://localhost"+ sbilgi.getPort() +";instanceName=" + sbilgi.getIns() + ";";
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		String VERITABANI = "OK_Gun" + sbilgi.getKod();
		stmt = null;
		String sql =null;
		if (sbilgi.getDizin_yeri() == "default")
			sql = "CREATE DATABASE [" + VERITABANI + "]";
		else
			sql = "CREATE DATABASE [" + VERITABANI + "]  ON PRIMARY " + " ( NAME = N'" + VERITABANI + "', FILENAME = N'" + sbilgi.getDizin() 	+ "\\" + VERITABANI + ".mdf  ) " + " LOG ON " + " ( NAME = N'" + VERITABANI + "_log', FILENAME = N'" + sbilgi.getDizin() + "\\" + VERITABANI + "_log.ldf' ) ";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		cumle = "jdbc:sqlserver://localhost" + sbilgi.getPort() + ";instanceName=" + sbilgi.getIns() + ";database=" + VERITABANI + ";";
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		create_table(sbilgi.getFir_adi());
		//
		if (sbilgi.getDizin_yeri() == "default")
			sql = "CREATE DATABASE [" + VERITABANI + "_LOG" + "]";
		else
			sql = "CREATE DATABASE [" + VERITABANI + "_LOG" + "]  ON PRIMARY " + " ( NAME = N'" + VERITABANI + "_LOG" + "', FILENAME = N'" + sbilgi.getDizin() 	+ "\\" + VERITABANI + "_LOG"+ ".mdf  ) " + " LOG ON " + " ( NAME = N'" + VERITABANI + "_LOG" + "_log', FILENAME = N'" + sbilgi.getDizin() + "\\" + VERITABANI + "_LOG" + "_log.ldf' ) ";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		cumle = "jdbc:sqlserver://localhost" + sbilgi.getPort() + ";instanceName=" + sbilgi.getIns() + ";database=" + VERITABANI + "_LOG" + ";";
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		create_table_log();
		//  VERITABANI DOSYASI ILK ACILIS
		ILOGER_KAYIT  vTLOG =  new DOSYA_MSSQL();
		lOG_BILGI lBILGI = new lOG_BILGI();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		lBILGI.seteVRAK("");
		vTLOG.Logla(lBILGI, BAGLAN_LOG.gunLogDizin);
		lBILGI.setmESAJ("Firma Adi:" + sbilgi.getFir_adi());
		vTLOG.Logla(lBILGI, BAGLAN_LOG.gunLogDizin);
		//SQLITE LOG DOSYASI OLUSTUR
		if (GLOBAL.dos_kontrol(GLOBAL.LOG_SURUCU + VERITABANI + "_mSSQL"+  ".DB") == false)
		{
			String dsy = GLOBAL.LOG_SURUCU + VERITABANI + "_mSSQL"+ ".DB" ;
			GLOBAL.create_table_log(dsy,sbilgi.getFir_adi(),BAGLAN_LOG.gunLogDizin);
		}
		//  TEXT DOSYASI ILK ACILIS
		ILOGER_KAYIT  tEXLOG = new TXT_LOG();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		tEXLOG.Logla(lBILGI, BAGLAN_LOG.gunLogDizin);
		lBILGI.setmESAJ("Firma Adi:" + sbilgi.getFir_adi());
		tEXLOG.Logla(lBILGI, BAGLAN_LOG.gunLogDizin);
		//
		stmt.close();
		con.close();

	}

	@Override
	public void gUN_SIFIR_S(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		con = null;  
		String VERITABANI = "OK_Gun" + sbilgi.getKod();
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
		vTLOG.Logla(lBILGI, BAGLAN_LOG.gunLogDizin);
		lBILGI.setmESAJ("Firma Adi:" + sbilgi.getFir_adi());
		vTLOG.Logla(lBILGI, BAGLAN_LOG.gunLogDizin);
		//SQLITE LOG DOSYASI OLUSTUR
		if (GLOBAL.dos_kontrol(  GLOBAL.LOG_SURUCU + GLOBAL.char_degis( BAGLAN_LOG.gunLogDizin.mODUL)) == false)
		{
			String dsy =  GLOBAL.LOG_SURUCU + GLOBAL.char_degis(BAGLAN_LOG.gunLogDizin.mODUL) ;
			GLOBAL.create_table_log(dsy,sbilgi.getFir_adi(),BAGLAN_LOG.gunLogDizin);
		}
		//  TEXT DOSYASI ILK ACILIS
		ILOGER_KAYIT  tEXLOG = new TXT_LOG();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		tEXLOG.Logla(lBILGI, BAGLAN_LOG.gunLogDizin);
		lBILGI.setmESAJ("Firma Adi:" + sbilgi.getFir_adi());
		tEXLOG.Logla(lBILGI, BAGLAN_LOG.gunLogDizin);
		//
		stmt.close();
		con.close();
	}

	@Override
	public void create_table(String fir_adi) throws SQLException {
		String sql = null;
		sql = "CREATE TABLE GOREV ([GID] [int] IDENTITY(1,1) NOT NULL , BASL_TARIH DATE , BASL_SAAT nvarchar(5), BIT_TARIH DATE , BIT_SAAT nvarchar(5),TEKRARLA bit,ISIM nvarchar(30),GOREV nvarchar(30),YER nvarchar(30),MESAJ nvarchar(100) ,SECENEK nvarchar(10),DEGER int ,[USER] nvarchar(15) NULL)" ;  
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE NONCLUSTERED INDEX [IX_GOREV] ON [dbo].[GOREV]([ISIM] ASC ,	[GOREV] ASC "
				+ " )WITH (PAD_INDEX = OFF, STATISTICS_NORECOMPUTE = OFF, SORT_IN_TEMPDB = OFF, DROP_EXISTING = OFF, ONLINE = OFF, ALLOW_ROW_LOCKS = ON, ALLOW_PAGE_LOCKS = ON)";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE TABLE GUNLUK ( [GRVID] [int] IDENTITY(1,1) NOT NULL  ,  [GID] [int]  , TARIH DATE ,SAAT nvarchar(5),ISIM nvarchar(30),GOREV nvarchar(30),YER nvarchar(30),MESAJ nvarchar(100) ,[USER] nvarchar(15) NULL)" ;  
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
		String cumle = "jdbc:sqlserver://" +  BAGLAN.gunDizin.cONN_STR + ";";
		con = DriverManager.getConnection(cumle, BAGLAN.gunDizin.kULLANICI, BAGLAN.gunDizin.sIFRESI);
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
	@Override
	public void gorev_kayit(Gunluk_Bilgi gbilgi) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql  = "INSERT INTO GOREV (BASL_TARIH,BASL_SAAT,BIT_TARIH,BIT_SAAT ,TEKRARLA,ISIM,GOREV,YER,MESAJ,SECENEK,DEGER,[USER]) " +
				" VALUES (?,?,?,?,?,?,?,?,?,?,?,?)" ;
		PreparedStatement stmt = null;
		kONTROL();
		stmt = con.prepareStatement(sql);
		stmt.setString(1, gbilgi.tarih1);
		stmt.setString(2, gbilgi.saat1);
		stmt.setString(3, gbilgi.tarih2);
		stmt.setString(4, gbilgi.saat2);
		stmt.setBoolean(5, gbilgi.tekrarla);
		stmt.setString(6, gbilgi.isim);
		stmt.setString(7, gbilgi.gorev);
		stmt.setString(8, gbilgi.yer);
		stmt.setString(9, gbilgi.mesaj);
		stmt.setString(10, gbilgi.secenek);
		stmt.setInt(11, gbilgi.deger);
		stmt.setString(12, gbilgi.user);
		stmt.executeUpdate();
		stmt.close();

	}
	@Override
	public void gorev_sil(int id) throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql = "DELETE GOREV  WHERE  GID = " + id;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		sql = "DELETE GUNLUK  WHERE  GID = " + id;
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
	@Override
	public int gid_ogren(Gunluk_Bilgi gbilgi) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		int gid = 0;
		ResultSet	rss = null;
		String sql = "SELECT GID  " +
				" FROM GOREV  " +
				" WHERE ISIM =  N'" + gbilgi.isim + "' AND GOREV = N'" + gbilgi.gorev+ "'" +
				" AND BASL_TARIH = '" + gbilgi.tarih1 + "' AND BIT_TARIH = '" + gbilgi.tarih2 + "'";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		rss.next();
		int count=0;
		count = rss.getRow();

		if (count  != 0) 
		{
			gid =  rss.getInt("GID");
		}
		return gid;	
	}
	@Override
	public ResultSet gorev_oku(Gunluk_Bilgi gbilgi) throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = "SELECT FORMAT (TARIH, 'dd.MM.yyyy') as TARIH, SAAT,ISIM,GOREV,MESAJ  " +
				" FROM GUNLUK WITH (INDEX (IDX_GUNLUK))  " +
				" WHERE TARIH >=  '" + gbilgi.tarih1 + "'" + gbilgi.isim +
				" ORDER BY TARIH ,ISIM ";
		
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	@Override
	public ResultSet isim_oku() throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = "SELECT DISTINCT  ISIM  " +
				" FROM GOREV  " +
				" ORDER BY ISIM  ";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	@Override
	public ResultSet gorev_oku_tarih(Gunluk_Bilgi gbilgi) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = "SELECT    TARIH,SAAT,ISIM,GOREV,YER,MESAJ   " +
				" FROM GUNLUK WITH (INDEX (IDX_GUNLUK))  " +
				" WHERE TARIH =  '" + gbilgi.tarih1 + "' AND SAAT >='" + gbilgi.saat1 + "' AND SAAT  <= '" + gbilgi.saat2 + "'" +
				" GROUP BY  TARIH,SAAT,ISIM,GOREV,YER,MESAJ ORDER BY ISIM  ";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	@Override
	public int gorev_bul(Gunluk_Bilgi gbilgi) throws ClassNotFoundException, SQLException {
		
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		int gid = 0;
		ResultSet	rss = null;
		String sql = "SELECT GID  " +
				" FROM GOREV  " +
				" WHERE ISIM =  N'" + gbilgi.isim + "' AND GOREV = N'" + gbilgi.gorev +   "' AND YER = N'" + gbilgi.yer + "'" +
				" AND MESAJ = N'" + gbilgi.mesaj + "'";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		rss.next();
		int count=0;
		count = rss.getRow();
		if (count  != 0) 
		{
			gid =  rss.getInt("GID");
		}
		stmt.close();
		return gid;	
	}
	@Override
	public ResultSet gID_oku(Gunluk_Bilgi gbilgi) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = "SELECT *  " +
				" FROM GOREV  " +
				" WHERE GID =  " + gbilgi.gid + "";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	@Override
	public ResultSet hazir_gorevler(Gunluk_Bilgi gbilgi) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = "SELECT  *  " +
				" FROM GUNLUK WITH (INDEX (IDX_GUNLUK))  " +
				" WHERE TARIH >=  '" + gbilgi.tarih1 + "' AND TARIH <= '" + gbilgi.tarih2 +"' AND SAAT >= '" + gbilgi.saat1 + "' AND SAAT <= '" + gbilgi.saat2 + "'" +
				" AND ISIM " + gbilgi.isim + " " +
				" ORDER BY TARIH , SAAT ";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	@Override
	public void gorev_tek_sil(int id) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql = "DELETE GUNLUK  WHERE  GRVID = " + id;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		stmt.close();
	}
	@Override
	public void gunluk_farkli_kayit(Gunluk_Bilgi gbilgi) throws ClassNotFoundException, SQLException, ParseException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		//***********************KAYIT TEKRARINA GORE KAYIT YAP **************************
		Date son_tarih ;
		Date bas_tarih;
		if (gbilgi.secenek == "Saatte")
		{
			son_tarih = new SimpleDateFormat("yyyy.MM.dd HH:mm").parse(gbilgi.tarih2 + " "+ gbilgi.saat2);
			bas_tarih = new SimpleDateFormat("yyyy.MM.dd HH:mm").parse(gbilgi.tarih1 + " " + gbilgi.saat1);
		}
		else
		{
			son_tarih = new SimpleDateFormat("yyyy.MM.dd").parse(gbilgi.tarih2 + " " + gbilgi.saat2);
			bas_tarih = new SimpleDateFormat("yyyy.MM.dd").parse(gbilgi.tarih1 + " " + gbilgi.saat1);
		}
		Date secSAAT = new SimpleDateFormat("yyyy.MM.dd HH:mm").parse(gbilgi.tarih1 + " " + gbilgi.saat1);
		Calendar saatCalendar = Calendar.getInstance();
		
		SimpleDateFormat format1 = new SimpleDateFormat("yyyy.MM.dd");
		String  anl_tS =  format1.format(bas_tarih);
		String sql  = "INSERT INTO GUNLUK (GID,TARIH,SAAT,ISIM,GOREV,YER,MESAJ,[USER]) " +
					" VALUES (?,?,?,?,?,?,?,?)" ;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt = con.prepareStatement(sql);
		long anl_t = bas_tarih.getTime();
		long son_t = son_tarih.getTime();
		while (anl_t <= son_t)  
		{
			stmt.setInt(1, gbilgi.gid);
			stmt.setString(2, anl_tS);
			stmt.setString(3, gbilgi.saat1);
			stmt.setString(4, gbilgi.isim);
			stmt.setString(5, gbilgi.gorev);
			stmt.setString(6, gbilgi.yer);
			stmt.setString(7, gbilgi.mesaj);
			stmt.setString(8, gbilgi.user);
			stmt.addBatch();
	
			Calendar c = Calendar.getInstance(); 
			c.setTime(bas_tarih); 
			if(gbilgi.secenek =="Ayda")
			{
				c.add(Calendar.MONTH, gbilgi.deger);
				bas_tarih = c.getTime();
			}
			else if(gbilgi.secenek =="Haftada")
			{
				c.add(Calendar.DATE, gbilgi.deger * 7);
				bas_tarih = c.getTime();
			}
			else if(gbilgi.secenek =="Gunde")
			{
				c.add(Calendar.DATE, gbilgi.deger );
				bas_tarih = c.getTime();
			}
			else if(gbilgi.secenek =="Saatte")
			{
				saatCalendar.setTime(secSAAT);
				saatCalendar.add(Calendar.HOUR, gbilgi.deger );
				bas_tarih = saatCalendar.getTime();
				secSAAT = bas_tarih;
				SimpleDateFormat format2 = new SimpleDateFormat("HH:mm");
				gbilgi.saat1 =  format2.format(saatCalendar.getTime());
			}
			anl_tS =  format1.format(bas_tarih);
			anl_t = bas_tarih.getTime() ;
		}
		stmt.executeBatch();
		stmt.close();
	}
	@Override
	public ResultSet gorev_oku_aylik_grup(Gunluk_Bilgi gbilgi) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = "SELECT  TARIH,ISIM,GOREV,YER,MESAJ   " +
				" FROM GUNLUK WITH (INDEX (IDX_GUNLUK))  " +
				" WHERE "+ gbilgi.isim +" TARIH BETWEEN   '" + gbilgi.tarih1 + "' AND '" + gbilgi.tarih2 + "'" +
				" GROUP BY TARIH,ISIM,GOREV,YER,MESAJ  ORDER BY TARIH   ";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		
		return rss;	
	}
	@Override
	public ResultSet gorev_oku_sonraki(Gunluk_Bilgi gbilgi) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = "SELECT  TARIH, SAAT,ISIM,GOREV,MESAJ  " +
				" FROM GUNLUK WITH (INDEX (IDX_GUNLUK))  " +
				" WHERE TARIH >=  '" + gbilgi.tarih1 + "'" + gbilgi.isim +
				" ORDER BY TARIH  ";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	@Override
	public void gun_firma_adi_kayit(String fadi) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql  = "UPDATE OZEL SET FIRMA_ADI = N'" + fadi + "'" ;
		PreparedStatement stmt = null;
		kONTROL();
		stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		stmt.close();
	}
	@Override
	public ResultSet gorev_oku_yillik_pivot(Gunluk_Bilgi gbilgi) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = "SELECT *  FROM  (SELECT  datepart(dd,GUNLUK.TARIH) as Gun , datepart(mm,GUNLUK.TARIH) as  Aylar ,GUNLUK.TARIH " +
				" FROM GUNLUK WITH (INDEX (IDX_GUNLUK))  " +
				" WHERE "+ gbilgi.isim +" TARIH BETWEEN   '" + gbilgi.tarih1 + "' AND '" + gbilgi.tarih2 + "' ) as s" +
				" PIVOT  (  COUNT(TARIH)  FOR Aylar  IN ( [1] , [2] , [3] , [4] , [5] , [6] , [7] , [8] , [9] , [10] , [11] , [12] )     )  AS p " +
				" ORDER BY Gun   ";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	@Override
	public ResultSet gorev_oku_sonraki_yil(Gunluk_Bilgi gbilgi) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = "SELECT  TARIH, SAAT,ISIM,GOREV,MESAJ  " +
				" FROM GUNLUK WITH (INDEX (IDX_GUNLUK))  " +
				" WHERE TARIH >=  '" + gbilgi.tarih1 + "'" + gbilgi.isim +
				" ORDER BY TARIH  ";
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	private void kONTROL() throws SQLException, ClassNotFoundException
	{
		if(con.isClosed())    
			baglan();
	}
}
