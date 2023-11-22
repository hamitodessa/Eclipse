package fih;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

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
		// TODO Auto-generated method stub
		
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
				+ " Ozel nvarchar(25) NULL,"
				+ " Mail nvarchar(25) NULL) ";
		stmt = con.createStatement();  
		stmt.executeUpdate(sql);
	}

}
