package LOGER_KAYIT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JOptionPane;

import OBS_C_2025.DIZIN_BILGILERI;
import OBS_C_2025.GLOBAL;



public class DOSYA_MYSQL implements ILOGER_KAYIT{
	 Connection con = null;
	
	@Override
	public void Logla(String mesaj, String evrak, DIZIN_BILGILERI dBILGI)
			throws ClassNotFoundException, SQLException 
	{
		String cumle = "jdbc:mysql://" + dBILGI.cONN_STR ;
	    con = DriverManager.getConnection(cumle,dBILGI.kULLANICI,dBILGI.sIFRESI);
	    Class.forName("com.mysql.cj.jdbc.Driver");
        String sql  = "INSERT `LOGLAMA` (`TARIH`,`EVRAK`,`MESAJ`,`USER_NAME`) " +
    		   		  " VALUES (?,?,?,?)" ;
    	PreparedStatement stmt = null;
    	stmt = con.prepareStatement(sql);
   		stmt.setTimestamp(1, new java.sql.Timestamp(new java.util.Date().getTime()));
		stmt.setString(2, evrak);
		stmt.setString(3, mesaj);
		stmt.setString(4, GLOBAL.KULL_ADI);
		stmt.executeUpdate();
		stmt.close();
	}
	
	

}
