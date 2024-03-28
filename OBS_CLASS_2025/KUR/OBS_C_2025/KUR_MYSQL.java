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

public class KUR_MYSQL implements IKUR {

	static Connection con = null;
	static Statement stmt = null;

	public void baglan() throws SQLException, ClassNotFoundException
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String cumle = "jdbc:mysql://" + BAGLAN.kurDizin.cONN_STR ;
		DriverManager.setLoginTimeout(0);
		con = DriverManager.getConnection(cumle,BAGLAN.kurDizin.kULLANICI,BAGLAN.kurDizin.sIFRESI);
	}
	@Override
	public void kUR_SIFIR_L(Server_Bilgi sbilgi)
			throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = null;  
		String cumle = "";
		cumle = "jdbc:mysql://localhost:" + sbilgi.getPort() ;
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		String VERITABANI = "ok_kur" + sbilgi.getKod();
		stmt = null;
		String sql =null;
		sql = "CREATE DATABASE " + VERITABANI ;
		stmt = con.createStatement();  
		stmt.execute(sql);
		cumle = "jdbc:mysql://localhost/" +VERITABANI ;
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		create_table();
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
		vTLOG.Logla(lBILGI, BAGLAN_LOG.kurLogDizin);
		//SQLITE LOG DOSYASI OLUSTUR
		if (GLOBAL.dos_kontrol(GLOBAL.LOG_SURUCU + VERITABANI + "_mYSQL" + ".DB") == false)
		{
			String dsy =GLOBAL.LOG_SURUCU + VERITABANI + "_mYSQL"+ ".DB" ;
			GLOBAL.create_table_log(dsy,"",BAGLAN_LOG.kurLogDizin);
		}
		//SQLITE LOG DOSYASI OLUSTUR
		if (GLOBAL.dos_kontrol(GLOBAL.LOG_SURUCU + VERITABANI + "_mYSQL" + ".ACCDB") == false)
		{
			String dsy =GLOBAL.LOG_SURUCU + VERITABANI + "_mYSQL"+ ".ACCDB" ;
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
	public void kUR_SIFIR_S(Server_Bilgi sbilgi)
			throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = null;  
		String VERITABANI = "ok_kur" + sbilgi.getKod();
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
		create_table();
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
		vTLOG.Logla(lBILGI, BAGLAN_LOG.kurLogDizin);
		//SQLITE LOG DOSYASI OLUSTUR
		if (GLOBAL.dos_kontrol(  GLOBAL.LOG_SURUCU + GLOBAL.char_degis( BAGLAN_LOG.kurLogDizin.mODUL)) == false)
		{
			String dsy =  GLOBAL.LOG_SURUCU +GLOBAL.char_degis(  BAGLAN_LOG.kurLogDizin.mODUL)  ;
			GLOBAL.create_table_log(dsy,"",BAGLAN_LOG.kurLogDizin);		
		}
		//ACCESS LOG DOSYASI OLUSTUR
		if (GLOBAL.dos_kontrol(  GLOBAL.LOG_SURUCU + GLOBAL.char_degis( BAGLAN_LOG.kurLogDizin.mODULADI_ACCDB)) == false)
		{
			String dsy =  GLOBAL.LOG_SURUCU +GLOBAL.char_degis(  BAGLAN_LOG.kurLogDizin.mODULADI_ACCDB)  ;
			GLOBAL.create_table_log(dsy,"",BAGLAN_LOG.kurLogDizin);		
		}
		//  TEXT DOSYASI ILK ACILIS
		ILOGER_KAYIT  tEXLOG = new TXT_LOG();
		tEXLOG.Logla(lBILGI, BAGLAN_LOG.kurLogDizin);
		stmt.close();
		con.close();
	}
	@Override
	public void create_table() throws SQLException {
		String sql = null;
		sql = "CREATE TABLE `KURLAR`( id int AUTO_INCREMENT PRIMARY KEY ," 
				+ " `Kur` nvarchar(3),"
				+ " `Tarih` date ,"
				+ " `MA` DOUBLE," 
				+ " `MS` DOUBLE," 
				+ " `SA` DOUBLE," 
				+ " `SS` DOUBLE," 
				+ " `BA` DOUBLE," 
				+ " `BS` DOUBLE," 
				+ "  INDEX `IX_KUR` ( `Kur` ASC , Tarih ASC) VISIBLE);" ;
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
	}
	@Override
	public ResultSet kur_liste(String tar) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet rss = null;
		String sql =" SELECT Kur , MA , MS, SA, SS,BA, BS " +
				" FROM KURLAR USE INDEX (IX_KUR) " +
				" WHERE Tarih ='" + tar + "' ORDER BY  Kur ";
		kONTROL();
		Statement stmt =  con.prepareStatement(sql);
		rss = stmt.executeQuery(sql);
		return rss;	
	}
	@Override
	public ResultSet kur_oku(String tar, String kur) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet rss = null;
		String sql =" SELECT Kur , MA , MS, SA, SS,BA, BS,  Tarih " +
				" FROM KURLAR USE INDEX (IX_KUR)  " +
				" WHERE Tarih ='" + tar + "' AND Kur =N'" + kur + "'";
		kONTROL();
		Statement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery(sql);
		return rss;	
	}
	@Override
	public void kur_sil(String tar, String kur) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql =  " DELETE " +
				" FROM KURLAR " +
				" WHERE Tarih ='" + tar + "' AND Kur =N'" + kur + "'" ;
		kONTROL();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();

	}
	@Override
	public void kur_kayit(String tar, String kur, double ma, double ms, double sa, double ss, double ba, double bs) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql  ="INSERT INTO KURLAR (Tarih,Kur,MA,MS,SA,SS,BA,BS) " +
				" VALUES (?,?,?,?,?,?,?,?)" ;
		PreparedStatement stmt = null;
		kONTROL();
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
	@Override
	public ResultSet kur_rapor(String c1, String c2, String t1, String t2) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet rss = null;
		String sql = "SELECT Tarih,Kur, MA, MS, BA, BS, SA, SS " +
				" FROM KURLAR USE INDEX (IX_KUR)  " +
				" WHERE Tarih BETWEEN '" + t1 + "' AND '" + t2 + "'" +
				" AND Kur BETWEEN '" + c1 + "' AND '" + c2 + "' ORDER BY Tarih DESC, Kur ";
		kONTROL();
		Statement stmt =  con.prepareStatement(sql);
		rss = stmt.executeQuery(sql);
		return rss;	
	}
	@Override
	public ResultSet kur_yil_graf_rapor(String c1, String t1, String t2) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet rss = null;
		String sql = "SELECT DISTINCT YEAR(Tarih) as TARIH "  + 
				" FROM KURLAR USE INDEX (IX_KUR)  " + 
				" WHERE Tarih >= '" + t1 + "' AND  Tarih <= '" + t2 + "'" + 
				" AND Kur = '" + c1 +"'    ORDER BY Tarih  ";
		kONTROL();
		Statement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery(sql);
		return rss;	
	}
	@Override
	public ResultSet kur_graf_rapor(String c1, String t1, String t2, String cins, String siralama)
			throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet rss = null;
		String sql ="SELECT " + siralama + "(Tarih) as Tarih,Kur, " +  cins + " " + 
				" FROM KURLAR USE INDEX (IX_KUR)  "  + 
				" WHERE Tarih >= '" + t1 +"' AND  Tarih <= '" + t2 + "'" + 
				" AND Kur = '" + c1 + "'  ORDER BY Tarih  ";
		kONTROL();
		Statement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery(sql);
		return rss;	
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
	public ResultSet kur_ay_graf_rapor(String c1, String t1, String t2) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		ResultSet rss = null;
		String sql = "SELECT DISTINCT MONTH(Tarih) as TARIH "  + 
				" FROM kurlar USE INDEX (IX_KUR)   " + 
				" WHERE Tarih >= '" + t1 + "' AND  Tarih <= '" + t2 + "'" + 
				" AND Kur = '" + c1 +"'    ORDER BY Tarih  ";
		kONTROL();
		Statement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery(sql);
		return rss;	
	}
	private void kONTROL() throws SQLException, ClassNotFoundException
	{
		if(! con.isValid(0))
			baglan();
	}
}
