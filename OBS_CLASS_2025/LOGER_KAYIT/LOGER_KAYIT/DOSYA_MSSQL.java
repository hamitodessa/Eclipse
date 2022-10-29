package LOGER_KAYIT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import OBS_C_2025.BAGLAN;
import OBS_C_2025.BAGLAN_LOG;
import OBS_C_2025.DIZIN_BILGILERI;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.LOG_KAYIT_BILGILERI;

public class DOSYA_MSSQL implements ILOGER_KAYIT{
	
	 Connection con = null;
	
	public void Logla(String mesaj, String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException {
		

		System.out.println("1=" + dBILGI.cONN_STR);
		System.out.println("11=" + dBILGI.kULLANICI + "=="+dBILGI.sIFRESI);
		String cumle = "jdbc:sqlserver://" + dBILGI.cONN_STR + ";";
	    con = DriverManager.getConnection(cumle,dBILGI.kULLANICI,dBILGI.sIFRESI);
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String sql  = "INSERT LOGLAMA (TARIH,EVRAK,MESAJ,[USER_NAME]) " +
    		   		  " VALUES (?,?,?,?)" ;
    	PreparedStatement stmt = null;
    	stmt = con.prepareStatement(sql);
    	java.util.Date today = new java.util.Date();
        
		stmt.setTimestamp(1, new java.sql.Timestamp(today.getTime()));
		stmt.setString(2, evrak);
		stmt.setString(3, mesaj);
		stmt.setString(4, GLOBAL.KULL_ADI);
		
		stmt.executeUpdate();
		stmt.close();
		
	}

}
