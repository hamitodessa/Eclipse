package LOGER_KAYIT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import OBS_C_2025.DIZIN_BILGILERI;
import OBS_C_2025.GLOBAL;


public class DOSYA_MSSQL implements ILOGER_KAYIT{

	Connection con = null;

	public void Logla(String mesaj, String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException {
		String cumle = "jdbc:sqlserver://" + dBILGI.cONN_STR + ";";
		con = DriverManager.getConnection(cumle,dBILGI.kULLANICI,dBILGI.sIFRESI);
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		String sql  = "INSERT LOGLAMA (TARIH,EVRAK,MESAJ,[USER_NAME]) " +
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

	@Override
	public ResultSet log_rapor(String t1, String t2, String aciklama, String evrak, String user, DIZIN_BILGILERI dBILGI)
			throws ClassNotFoundException, SQLException {

		StringBuilder stb = new StringBuilder();
		stb.append(" SELECT  TARIH ,MESAJ,EVRAK,[USER_NAME] " ); 
		stb.append(" FROM   loglama  WITH (INDEX (IX_LOGLAMA) )") ; 

		stb.append(" WHERE  loglama.mesaj  LIKE N'" + aciklama + "'") ;
		if ( ! t1.equals(""))
		{
			stb.append(" AND TARIH BETWEEN  '" + t1 + "' AND '" + t2 + " 23:59:59.998' ") ;
		}
		if ( ! evrak.equals("%") &&  ! evrak.equals("%%")) 
		{
			stb.append(" AND EVRAK  LIKE '" + evrak + "'");
		}
		if ( ! user.equals("%") &&  ! user.equals("%%")) 
		{
			stb.append(" AND USER_NAME  LIKE '" + user + "'");
		}
		stb.append(" ORDER BY TARIH ") ;
		String cumle = "jdbc:sqlserver://" + dBILGI.cONN_STR + ";";
		con = DriverManager.getConnection(cumle,dBILGI.kULLANICI,dBILGI.sIFRESI);
		ResultSet	rss = null;
		String sql = stb.toString() ;
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
}
