package fih;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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

	@Override
	public void reh_kayit(String adi, String t1, String t2, String t3, String t4, String fax, String mail, String note)
			throws ClassNotFoundException, SQLException {
		Class.forName("com.mysql.cj.jdbc.Driver");
		String sql  = "INSERT INTO FIHRIST (Adi,Tel_1,Tel_2,Tel_3,Tel_4,Fax,Ozel,Mail) " +
				" VALUES (?,?,?,?,?,?,?,?)" ;
		PreparedStatement stmt = null;
		stmt = con.prepareStatement(sql);
		stmt.setString(1, adi);
		stmt.setString(2, t1);
		stmt.setString(3, t2);
		stmt.setString(4, t3);
		stmt.setString(5, t4);
		stmt.setString(6, fax);
		stmt.setString(7, mail);
		stmt.setString(8, note);
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
