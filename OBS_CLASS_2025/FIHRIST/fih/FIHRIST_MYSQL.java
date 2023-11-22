package fih;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import OBS_C_2025.BAGLAN;
import OBS_C_2025.Server_Bilgi;

public class FIHRIST_MYSQL implements I_Fihrist{
	public static Connection con = null;

	@Override
	public void baglan() throws SQLException, ClassNotFoundException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String cumle = "jdbc:mysql://" + BAGLAN.fihDizin.cONN_STR ;
		DriverManager.setLoginTimeout(0);
		con = DriverManager.getConnection(cumle,BAGLAN.fihDizin.kULLANICI,BAGLAN.fihDizin.sIFRESI);
		
	}

	@Override
	public void reh_sifirdan_L(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void reh_SIFIR_S(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void create_table(String fir_adi) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public ResultSet reh_doldur() throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}
