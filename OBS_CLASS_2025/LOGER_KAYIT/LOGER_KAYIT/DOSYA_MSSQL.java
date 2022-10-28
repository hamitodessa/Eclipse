package LOGER_KAYIT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import OBS_C_2025.BAGLAN;
import OBS_C_2025.GLOBAL;

public class DOSYA_MSSQL implements ILOGER_KAYIT{
	
	 Connection con = null;
	
	public void Logla(String mesaj, String evrak) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		BAGLAN bAGLAN = new BAGLAN();	
		bAGLAN.Connect(GLOBAL.KULL_ADI);
		String lOG_Conn_L = "localhost;instanceName=" + BAGLAN.cariDizin.iNSTANCE + " ; database=" + "OK_Car" +  BAGLAN.cariDizin.kOD + "_LOG" ;
		//String lOG_Conn_S = BAGLAN.cariDizin.sERVER + ";instanceName=" + BAGLAN.cariDizin.iNSTANCE + " ; database=" + "OK_Car" +  BAGLAN.cariDizin.kOD + "_LOG" ;
		
		System.out.println("cariconn=" + BAGLAN.cariDizin.cONN_STR);
		System.out.println("conn=" +lOG_Conn_L);
		String cumle = "jdbc:sqlserver://" + lOG_Conn_L + ";";
	    con = DriverManager.getConnection(cumle,BAGLAN.cariDizin.kULLANICI,BAGLAN.cariDizin.sIFRESI);
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
