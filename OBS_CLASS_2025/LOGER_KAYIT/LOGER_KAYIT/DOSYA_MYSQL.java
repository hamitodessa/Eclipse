package LOGER_KAYIT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.table.DefaultTableModel;

import OBS_C_2025.DIZIN_BILGILERI;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.lOG_BILGI;
public class DOSYA_MYSQL implements ILOGER_KAYIT{
	Connection con = null;
	@Override
	public void Logla(lOG_BILGI lBILGI, DIZIN_BILGILERI dBILGI)
			throws ClassNotFoundException, SQLException 
	{
		Class.forName("com.mysql.cj.jdbc.Driver");
		String cumle = "jdbc:mysql://" + dBILGI.cONN_STR ;
		con = DriverManager.getConnection(cumle,dBILGI.kULLANICI,dBILGI.sIFRESI);
		
		String sql  = "INSERT `LOGLAMA` (`TARIH`,`EVRAK`,`MESAJ`,`USER_NAME`) " +
				" VALUES (?,?,?,?)" ;
		PreparedStatement stmt = null;
		stmt = con.prepareStatement(sql);
		stmt.setTimestamp(1, new java.sql.Timestamp(new java.util.Date().getTime()));
		stmt.setString(2,lBILGI.geteVRAK());
		stmt.setString(3,lBILGI.getmESAJ());
		stmt.setString(4, GLOBAL.KULL_ADI);
		stmt.executeUpdate();
		stmt.close();
	}

	public ResultSet log_rapor(String t1, String t2, String aciklama, String evrak , String user, DIZIN_BILGILERI dBILGI)
			throws ClassNotFoundException, SQLException {
		StringBuilder stb = new StringBuilder();
		stb.append(" SELECT DATE_FORMAT(TARIH, '%d.%m.%Y %H:%i:%s') AS TARIH ,MESAJ,EVRAK,USER_NAME " ); 
		stb.append(" FROM   loglama  USE INDEX (IX_LOGLAMA) ") ; 
		stb.append(" WHERE  loglama.mesaj  LIKE N'" + aciklama + "'") ;
		if ( ! t1.equals(""))
			stb.append(" AND TARIH BETWEEN  '" + t1 + "' AND '" + t2 + " 23:59:59.998' ") ;
		if ( ! evrak.equals("%") &&  ! evrak.equals("%%"))
			stb.append(" AND EVRAK  LIKE '" + evrak + "'");
		if ( ! user.equals("%") &&  ! user.equals("%%"))
			stb.append(" AND USER_NAME  LIKE '" + user + "'");
		stb.append(" ORDER BY DATE(TARIH) ") ;
		String cumle = "jdbc:mysql://" + dBILGI.cONN_STR ;
		con = DriverManager.getConnection(cumle,dBILGI.kULLANICI,dBILGI.sIFRESI);
		ResultSet	rss = null;
		String sql = stb.toString() ;
		PreparedStatement stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		return rss;	
	}
	@Override
	public DefaultTableModel log_txt_rapor(String t1, String t2, String aciklama, String evrak, String user,
			DIZIN_BILGILERI dBILGI) {
		return null;
	}
}
