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


public class KUR_MSSQL implements IKUR{

	static Connection con = null;
	static Statement stmt = null;
	public void baglan() throws SQLException
	{
		String cumle = "jdbc:sqlserver://" + BAGLAN.kurDizin.cONN_STR + ";";
		DriverManager.setLoginTimeout(0);
		con = DriverManager.getConnection(cumle,BAGLAN.kurDizin.kULLANICI,BAGLAN.kurDizin.sIFRESI);
	}
	@Override
	public void kUR_SIFIR_L(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		con = null;  
		String cumle = "";
		if ( ! sbilgi.getPort().toString().equals("") )
		{
			sbilgi.setPort(  ":" + sbilgi.getPort() );
		}
		cumle = "jdbc:sqlserver://localhost"+ sbilgi.getPort() + ";instanceName=" + sbilgi.getIns() + ";";
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		String VERITABANI = "OK_Kur" + sbilgi.getKod();
		stmt = null;
		String sql =null;
		if (sbilgi.getDizin_yeri() == "default")
			sql = "CREATE DATABASE [" + VERITABANI + "]";
		else
			sql = "CREATE DATABASE [" + VERITABANI + "]  ON PRIMARY " + " ( NAME = N'" + VERITABANI + "', FILENAME = N'" + sbilgi.getDizin() 	+ "\\" + VERITABANI + ".mdf ' ) ";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		cumle = "jdbc:sqlserver://localhost" + sbilgi.getPort() + ";instanceName=" + sbilgi.getIns() + ";database=" + VERITABANI + ";";
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		create_table();
		//
		if (sbilgi.getDizin_yeri() == "default")
			sql = "CREATE DATABASE [" + VERITABANI + "_LOG" + "]";
		else
			sql = "CREATE DATABASE [" + VERITABANI + "_LOG" + "]  ON PRIMARY " + " ( NAME = N'" + VERITABANI + "_LOG"+ "', FILENAME = N'" + sbilgi.getDizin() 	+ "\\" + VERITABANI + "_LOG" + ".mdf ' ) ";
		cumle = "jdbc:sqlserver://localhost " + sbilgi.getPort() + ";instanceName=" + sbilgi.getIns() + ";";
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		cumle = "jdbc:sqlserver://localhost " + sbilgi.getPort() + ";instanceName=" + sbilgi.getIns() + ";database=" + VERITABANI + "_LOG" + ";";
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		create_table_log();
		//  VERITABANI DOSYASI ILK ACILIS
		ILOGER_KAYIT  vTLOG =  new DOSYA_MSSQL();
		lOG_BILGI lBILGI = new lOG_BILGI();
		lBILGI.setmESAJ("Dosya Olusturuldu");
		lBILGI.seteVRAK("");
		vTLOG.Logla(lBILGI, BAGLAN_LOG.kurLogDizin);
		//SQLITE LOG DOSYASI OLUSTUR
		if (GLOBAL.dos_kontrol(GLOBAL.LOG_SURUCU + VERITABANI + "_mSSQL" + ".DB") == false)
		{
			String dsy = GLOBAL.LOG_SURUCU + VERITABANI + "_mSSQL"+ ".DB" ;
			GLOBAL.create_table_log(dsy,"",BAGLAN_LOG.kurLogDizin);
		}
		//  TEXT DOSYASI ILK ACILIS
		ILOGER_KAYIT  tEXLOG = new TXT_LOG();
    	tEXLOG.Logla(lBILGI, BAGLAN_LOG.kurLogDizin);
		//
		stmt.close();
		con.close();
	}
	@Override
	public void kUR_SIFIR_S(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		con = null;  
		String VERITABANI = "OK_Kur" + sbilgi.getKod();
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
		create_table();
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
		vTLOG.Logla(lBILGI, BAGLAN_LOG.kurLogDizin);
		//SQLITE LOG DOSYASI OLUSTUR
		if (GLOBAL.dos_kontrol(  GLOBAL.LOG_SURUCU +GLOBAL.char_degis(  BAGLAN_LOG.kurLogDizin.mODUL) ) == false)
		{
			String dsy =  GLOBAL.LOG_SURUCU + GLOBAL.char_degis( BAGLAN_LOG.kurLogDizin.mODUL)  ;
			GLOBAL.create_table_log(dsy,"",BAGLAN_LOG.kurLogDizin);
		}
		//  TEXT DOSYASI ILK ACILIS
		ILOGER_KAYIT  tEXLOG = new TXT_LOG();
		 tEXLOG.Logla(lBILGI, BAGLAN_LOG.kurLogDizin);
		//
		stmt.close();
		con.close();
	}
	@Override
	public void create_table() throws SQLException {
		String sql = null;
		sql = "CREATE TABLE KURLAR( id int identity(1,1) CONSTRAINT PKeyid PRIMARY KEY ," 
				+ " Kur nvarchar(3),"
				+ " Tarih date ,"
				+ " MA float," 
				+ " MS float," 
				+ " SA float," 
				+ " SS float," 
				+ " BA float," 
				+ " BS float," 
				+ " INDEX IX_KUR NONCLUSTERED (Kur,Tarih ASC) INCLUDE([MA],[MS]) )";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);

	}
	public ResultSet  kur_liste(String tar) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = " SELECT Kur , MA , MS, SA, SS,BA, BS " +
				" FROM KURLAR  " +
				" WHERE Tarih ='" + tar + "' ORDER BY  Kur ";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet kur_oku (String tar , String kur) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = " SELECT Kur , MA , MS, SA, SS,BA, BS,  Tarih " +
				" FROM kurlar  " +
				" WHERE Tarih ='" + tar + "' AND Kur =N'" + kur + "'";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public void kur_sil (String tar,String kur) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql = " DELETE " +
				" FROM kurlar " +
				" WHERE Tarih ='" + tar + "' AND Kur =N'" + kur + "'" ;
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
	public void kur_kayit(String tar,String kur ,double  ma ,double ms ,double sa ,double ss ,double ba,double bs ) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql  = "INSERT INTO kurlar (Tarih,Kur,MA,MS,SA,SS,BA,BS) " +
				" VALUES (?,?,?,?,?,?,?,?)" ;
		PreparedStatement stmt = null;
		stmt = con.prepareStatement(sql);
		stmt.setString(1, tar);
		stmt.setString(2, kur);
		stmt.setDouble(3, ma);
		stmt.setDouble(4, ms);
		stmt.setDouble(5, sa);
		stmt.setDouble(6, ss);
		stmt.setDouble(7, ba);
		stmt.setDouble(8, bs);
		stmt.executeUpdate();
		stmt.close();
	}
	public ResultSet kur_rapor(String c1 ,String c2 ,String t1 ,String t2 ) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = "SELECT Tarih,Kur, MA, MS, BA, BS, SA, SS " +
				" FROM kurlar  " +
				" WHERE Tarih BETWEEN '" + t1 + "' AND '" + t2 + "'" +
				" AND Kur BETWEEN '" + c1 + "' AND '" + c2 + "' ORDER BY Tarih DESC, Kur ";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet kur_yil_graf_rapor(String c1  ,String t1 ,String t2 ) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = "SELECT DISTINCT DATEPART(YEAR,Tarih) as TARIH "  + 
				" FROM kurlar  " + 
				" WHERE Tarih >= '" + t1 + "' AND  Tarih <= '" + t2 + "'" + 
				" AND Kur = '" + c1 +"'    ORDER BY Tarih  ";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet kur_ay_graf_rapor(String c1  ,String t1 ,String t2 ) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = "SELECT DISTINCT DATEPART(MONTH,Tarih) as TARIH "  + 
				" FROM kurlar  " + 
				" WHERE Tarih >= '" + t1 + "' AND  Tarih <= '" + t2 + "'" + 
				" AND Kur = '" + c1 +"'    ORDER BY Tarih  ";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	public ResultSet kur_graf_rapor(String c1  ,String t1 ,String t2,String cins,String siralama ) throws ClassNotFoundException, SQLException
	{
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = "SELECT DATEPART(" + siralama + ",Tarih) as Tarih,Kur, " +  cins + " " + 
				" FROM kurlar  "  + 
				" WHERE Tarih >= '" + t1 +"' AND  Tarih <= '" + t2 + "'" + 
				" AND Kur = '" + c1 + "'  ORDER BY Tarih  ";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
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
}
