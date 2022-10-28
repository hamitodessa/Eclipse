package LOGER_KAYIT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import OBS_C_2025.BAGLAN;
import OBS_C_2025.GLOBAL;

public class DOSYA_MSSQL implements ILOGER_KAYIT{
	
	 Connection con = null;
	String lOG_Conn_L = "localhost;instanceName=" + BAGLAN.cariDizin.iNSTANCE + " ; database=" + "OK_Car" +  BAGLAN.cariDizin.kOD + "_LOG" ;
	String lOG_Conn_S = BAGLAN.cariDizin.sERVER + ";instanceName=" + BAGLAN.cariDizin.iNSTANCE + " ; database=" + "OK_Car" +  BAGLAN.cariDizin.kOD + "_LOG" ;
	
	public void Logla(String mesaj, String evrak) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
			
				
		String cumle = "jdbc:sqlserver://" + lOG_Conn_L + ";";
	    con = DriverManager.getConnection(cumle,BAGLAN.cariDizin.kULLANICI,BAGLAN.cariDizin.sIFRESI);
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        String sql  = "INSERT LOGLAMA (TARIH,EVRAK,MESAJ,[USER]) " +
    		   		  " VALUES (?,?,?,?,?)" ;
    	PreparedStatement stmt = null;
    	stmt = con.prepareStatement(sql);
		stmt.setDate(2, java.sql.Date.valueOf(java.time.LocalDate.now()));
		stmt.setString(2, evrak);
		stmt.setString(3, mesaj);
		stmt.setString(4, GLOBAL.KULL_ADI);
		
		stmt.executeUpdate();
		stmt.close();
		
	}

}
