package fih;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import OBS_C_2025.BAGLAN;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.Server_Bilgi;

@SuppressWarnings("unused")
public class FIHRIST_MSSQL implements I_Fihrist{
	public static Connection con = null;
	static Statement stmt = null;

	private GLOBAL gLB = new GLOBAL();
	@Override
	public void baglan() throws SQLException, ClassNotFoundException {
		String cumle = "jdbc:sqlserver://" + BAGLAN.fihDizin.cONN_STR + ";";
		DriverManager.setLoginTimeout(0);
		con = DriverManager.getConnection(cumle,BAGLAN.fihDizin.kULLANICI,BAGLAN.fihDizin.sIFRESI);
	}
	@Override
	public void reh_sifirdan_L(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

		con = null;  
		String cumle = "";
		if ( ! sbilgi.getPort().toString().equals("") )
		{
			sbilgi.setPort(  ":" + sbilgi.getPort() );
		}
		cumle = "jdbc:sqlserver://localhost" + sbilgi.getPort() + ";instanceName=" + sbilgi.getIns() + ";";
		con = DriverManager.getConnection(cumle,sbilgi.getKull(),sbilgi.getSifre());   // SERVER BAGLANDI
		String VERITABANI = "OK_Fih" +  sbilgi.getKod();
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
		stmt.close();
		con.close();


	}
	@Override
	public void reh_SIFIR_S(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		con = null;  
		String VERITABANI = "OK_Fih" +  sbilgi.getKod();
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
		stmt.close();
		con.close();
	}
	@Override
	public void create_table(String fir_adi) throws SQLException {
		String sql = null;
		sql = "CREATE TABLE [dbo].[FIHRIST]("
				+ " ID int identity(1,1) CONSTRAINT PKeyid PRIMARY KEY,"
				+ " Adi nvarchar(50) NOT NULL, "
				+ " Tel_1 nvarchar(25) NULL,"
				+ " Tel_2 nvarchar(25) NULL,"
				+ " Tel_3 nvarchar(25) NULL,"
				+ " Tel_4 nvarchar(25) NULL,"
				+ " Fax nvarchar(25) NULL,"
				+ " Note nvarchar(50) NULL,"
				+ " Note2 nvarchar(50) NULL,"
				+ " Mail nvarchar(50) NULL )";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
		sql = "CREATE NONCLUSTERED INDEX [IX_FIHRIST] "
				+"	ON [dbo].[FIHRIST] ([Adi])";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
	}
	@Override
	public ResultSet reh_doldur() throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		ResultSet	rss = null;
		String sql = " SELECT Adi ,Tel_1,Tel_2,Tel_3,Tel_4,Fax ,Note As Not_,Note2 as Not_2,Mail ,ID  "  + 
				"  FROM FIHRIST   " + 
				"  ORDER BY Adi   ";
		if(con.isClosed())    
			baglan();
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	 
	}
	@Override
	public void reh_kayit(String adi, String t1, String t2, String t3, String t4, String fax,  String note,String note2 ,String mail)
			throws ClassNotFoundException, SQLException {
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql  = "INSERT INTO FIHRIST (Adi,Tel_1,Tel_2,Tel_3,Tel_4,Fax,Note,Note2,Mail) " +
				" VALUES (?,?,?,?,?,?,?,?,?)" ;
		PreparedStatement stmt = null;
		if(con.isClosed())    
			baglan();
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
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql = "DELETE FROM FIHRIST WHERE ID ='" + cdi + "'" ;
		if(con.isClosed())    
			baglan();
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		
	}

}
