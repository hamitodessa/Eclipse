package fih;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import OBS_C_2025.BAGLAN;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.Server_Bilgi;

public class FIHRIST_SQLITE implements I_Fihrist{
	public static Connection con = null;
	static Statement stmt = null;

	@Override
	public void baglan() throws SQLException, ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");
		con = DriverManager.getConnection("jdbc:sqlite:" + BAGLAN.fihDizin.cONN_STR  ) ;
	}

	@Override
	public void reh_sifirdan_L(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		con = DriverManager.getConnection("jdbc:sqlite:" + GLOBAL.DBYERI + "OK_Fih" + sbilgi.getKod() + ".DB"  ) ;
		Statement stmt =con.createStatement();  
		String sql = "CREATE TABLE [FIHRIST]("
				+ " ID int identity(1,1) CONSTRAINT PKeyid PRIMARY KEY,"
				+ " Adi nvarchar(50) NOT NULL, "
				+ " Tel_1 nvarchar(25) NULL,"
				+ " Tel_2 nvarchar(25) NULL,"
				+ " Tel_3 nvarchar(25) NULL,"
				+ " Tel_4 nvarchar(25) NULL,"
				+ " Fax nvarchar(25) NULL,"
				+ " Ozel nvarchar(25) NULL,"
				+ " Mail nvarchar(25) NULL) ";
		stmt.execute(sql);
		stmt.close();
		con.close();
	}

	@Override
	public void reh_SIFIR_S(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create_table(String fir_adi) throws SQLException {
	
	}

}
