package OBS_PACKAGE;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class KUR_MYSQL implements IKUR {

	static Connection con = null;
	static Statement stmt = null;
	
	public void baglan() throws SQLException
	{
		String cumle = "jdbc:sqlserver://" + CONNECTION.kurdizinbilgi.conn_str + ";";
	    con = DriverManager.getConnection(cumle,CONNECTION.kurdizinbilgi.kullanici,CONNECTION.kurdizinbilgi.sifresi);
	}
	@Override
	public void kur_sifirdan_L(String kod, String dizin_yeri, String dizin, String ins, String kull, String sifre)
			throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
 		con = null;  
 		String cumle = "";
         cumle = "jdbc:mysql://localhost;instanceName=" + ins + ";";
         con = DriverManager.getConnection(cumle,kull,sifre);
         String VERITABANI = "OK_Kur" + kod;
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
	public void kur_sifirdan_S(String server, String ins, String kull, String sifre, String kod)
			throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.jdbc.Driver");
	  		con = null;  
	  		String VERITABANI = "OK_Kur" + kod;
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
        sql = "CREATE TABLE KURLAR( id int identity(1,1) CONSTRAINT PKeyid PRIMARY KEY ," 
       		 					+ " Kur nvarchar(3),"
       		 					+ " Tarih date ,"
       		 					+ " MA float," 
       		 					+ " MS float," 
       		 					+ " SA float," 
       		 					+ " SS float," 
       		 					+ " BA float," 
       		 					+ " BS float," 
       		 					+ " INDEX IX_KUR NONCLUSTERED (Kur,Tarih))";
        stmt = con.createStatement();  
        stmt.executeUpdate(sql);
		
	}

	@Override
	public ResultSet kur_liste(String tar) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ResultSet kur_oku(String tar, String kur) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void kur_sil(String tar, String kur) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void kur_kayit(String tar, String kur, double ma, double ms, double sa, double ss, double ba, double bs) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public ResultSet kur_rapor(String c1, String c2, String t1, String t2) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet kur_yil_graf_rapor(String c1, String t1, String t2) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public ResultSet kur_graf_rapor(String c1, String t1, String t2, String cins, String siralama)
			throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
