package fih;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import OBS_C_2025.BAGLAN;
import OBS_C_2025.Server_Bilgi;

public class FIHRIST_MYSQL implements I_Fihrist{
	public static Connection con = null;
	static Statement stmt = null;
	@Override
	public void baglan() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String cumle = "jdbc:mysql://" + BAGLAN.fihDizin.cONN_STR ;
		DriverManager.setLoginTimeout(0);
		con = DriverManager.getConnection(cumle,BAGLAN.fihDizin.kULLANICI,BAGLAN.fihDizin.sIFRESI);

	}

	@Override
	public void reh_sifirdan_L(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = null;  
		String cumle = "";
		cumle = "jdbc:mysql://localhost:" + sbilgi.getPort() ;  // SERVER BAGLANDI
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());
		String VERITABANI = "ok_fih" + sbilgi.getKod();
		stmt = null;
		String sql =null;
		sql = "CREATE DATABASE " + VERITABANI ;
		stmt = con.createStatement();  
		stmt.execute(sql);
		cumle = "jdbc:mysql://localhost/" +VERITABANI ;
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());  // DATABASE BAGLANDI
		create_table(sbilgi.getFir_adi());
	}

	@Override
	public void reh_SIFIR_S(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		con = null;  
		String VERITABANI = "ok_fih" + sbilgi.getKod();
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
		create_table(sbilgi.getFir_adi());

	}

	@Override
	public void create_table(String fir_adi) throws SQLException {
		String sql = null;
		sql = "CREATE TABLE `FIHRIST`("
				+ " ID int AUTO_INCREMENT PRIMARY KEY,"
				+ " `Adi` nvarchar(50) NOT NULL, "
				+ " `Tel_1` nvarchar(25) NULL,"
				+ " `Tel_2` nvarchar(25) NULL,"
				+ " `Tel_3` nvarchar(25) NULL,"
				+ " `Tel_4` nvarchar(25) NULL,"
				+ " `Fax` nvarchar(25) NULL,"
				+ " `Note` nvarchar(50) NULL,"
				+ " `Note2` nvarchar(50) NULL,"
				+ " `Mail` nvarchar(50) NULL);";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
	}

	@Override
	public ResultSet reh_doldur() throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");

		ResultSet	rss = null;
		String sql = " SELECT Adi ,Tel_1,Tel_2,Tel_3,Tel_4,Fax ,Note,Note2,Mail ,ID  "  + 
				"  FROM FIHRIST   " + 
				"  ORDER BY Adi   ";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	 
	}

	@Override
	public void reh_kayit(String adi, String t1, String t2, String t3, String t4, String fax, String note,String note2 ,String mail)
			throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql  = "INSERT INTO FIHRIST (Adi,Tel_1,Tel_2,Tel_3,Tel_4,Fax,Note,Note2,Mail) " +
				" VALUES (?,?,?,?,?,?,?,?,?)" ;
		PreparedStatement stmt = null;
		stmt = con.prepareStatement(sql);
		stmt.setString(1, adi);
		stmt.setString(2, t1);
		stmt.setString(3, t2);
		stmt.setString(4, t3);
		stmt.setString(5, t4);
		stmt.setString(6, fax);
		stmt.setString(7, note);
		stmt.setString(8, note2);
		stmt.setString(9, mail);
		stmt.executeUpdate();
		stmt.close();
	}

	@Override
	public void reh_sil(int cdi) throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql = "DELETE FROM FIHRIST WHERE ID ='" + cdi + "'" ;
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
	}
}
