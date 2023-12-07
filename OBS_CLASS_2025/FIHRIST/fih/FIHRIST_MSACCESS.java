package fih;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.healthmarketscience.jackcess.Database;
import com.healthmarketscience.jackcess.DatabaseBuilder;
import OBS_C_2025.BAGLAN;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.Server_Bilgi;

public class FIHRIST_MSACCESS implements I_Fihrist{
	public static Connection con = null;


	@Override
	public void baglan() throws SQLException, ClassNotFoundException {
		boolean result = false ;
		result = GLOBAL.dos_kontrol(BAGLAN.fihDizin.cONN_STR );
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		if(result)
			con = DriverManager.getConnection("jdbc:ucanaccess://" + BAGLAN.fihDizin.cONN_STR ,"","" ) ;
		}

	@Override
	public void reh_sifirdan_L(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {

		File file = new File(sbilgi.getDizin() + "/" + "OK_Fih" + sbilgi.getKod() + ".accdb" );
		try {
			Database db;
			db = new DatabaseBuilder(file).setFileFormat(Database.FileFormat.V2019)	
					//.setCodecProvider(new CryptCodecProvider("oOk271972"))
					.create();
			db.close();
		} catch (Exception e) {
			
		}
		String sql = "CREATE TABLE FIHRIST("
				+ " ID AUTOINCREMENT PRIMARY KEY ,"
				+ " Adi TEXT(50)  , "
				+ " Tel_1 TEXT(25) ,"
				+ " Tel_2 TEXT(25) ,"
				+ " Tel_3 TEXT(25) ,"
				+ " Tel_4 TEXT(25) ,"
				+ " Fax TEXT(25) ,"
				+ " Note TEXT(50) ,"
				+ " Note2 TEXT(50) ,"
				+ " Mail TEXT(50) ) " ;
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		con = DriverManager.getConnection("jdbc:ucanaccess://" +  sbilgi.getDizin() + "/" + "OK_Fih" + sbilgi.getKod() + ".accdb" ,"","" ) ;
		Statement stmt = con.createStatement();  
		stmt.execute(sql);  
		sql = "CREATE INDEX IX_FIHRIST ON FIHRIST (Adi);" ;
		con = DriverManager.getConnection("jdbc:ucanaccess://" +  sbilgi.getDizin() + "/" + "OK_Fih" + sbilgi.getKod() + ".accdb" ,"","" ) ;
		stmt = con.createStatement();  
		stmt.execute(sql);  
		stmt.close();
		con.close();
		
	}

	@Override
	public void reh_SIFIR_S(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException {
		
		
	}

	@Override
	public void create_table(String fir_adi) throws SQLException {
	
	}

	@Override
	public ResultSet reh_doldur() throws ClassNotFoundException, SQLException {
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		ResultSet	rss = null;
		String sql = " SELECT Adi ,Tel_1,Tel_2,Tel_3,Tel_4,Fax ,Note As Not_,Note2 as Not_2,Mail ,ID  "  + 
				"  FROM FIHRIST   " + 
				"  ORDER BY Adi   ";
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	 
	}

	@Override
	public void reh_kayit(String adi, String t1, String t2, String t3, String t4, String fax,  String note,String note2 ,String mail)
			throws ClassNotFoundException, SQLException {
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
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
		String sql = "DELETE FROM FIHRIST WHERE ID ='" + cdi + "'" ;
		PreparedStatement stmt = con.prepareStatement(sql);
		stmt.executeUpdate();
		
	}

}
