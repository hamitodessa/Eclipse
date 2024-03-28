package LOGER_KAYIT;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import javax.swing.table.DefaultTableModel;

import OBS_C_2025.DIZIN_BILGILERI;
import OBS_C_2025.GLOBAL;
import OBS_C_2025.lOG_BILGI;

public class DOSYA_ACCESS_ACCDB implements ILOGER_KAYIT{
	Connection con = null;
	@Override
	public void Logla(lOG_BILGI lBILGI, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException {
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		Connection aCCDBconn =  DriverManager.getConnection("jdbc:ucanaccess://" + GLOBAL.LOG_SURUCU +  dBILGI.mODULADI_ACCDB ) ;
		PreparedStatement stmt = null;
		String sql =  "INSERT INTO LOGLAMA (TARIH,MESAJ,EVRAK,USER_NAME) " +
				"VALUES (?,?,?,?)";
		stmt = aCCDBconn.prepareStatement(sql);
		Timestamp dATE =   new Timestamp(new Date().getTime());
		stmt.setTimestamp(1, dATE);
		stmt.setString(2, lBILGI.getmESAJ());
		stmt.setString(3,lBILGI.geteVRAK());
		stmt.setString(4, GLOBAL.KULL_ADI);
		stmt.executeUpdate();
		stmt.close();
		aCCDBconn.close();
	}

	@Override
	public ResultSet log_rapor(String t1, String t2, String aciklama, String evrak, String user, DIZIN_BILGILERI dBILGI)
			throws ClassNotFoundException, SQLException 
	{
		ResultSet	rss = null;
		Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
		StringBuilder stb = new StringBuilder();
		stb.append(" SELECT   Format(LOGLAMA.TARIH,'dd.MM.yyyy HH.mm.ss') AS TARIH  ,[MESAJ],[EVRAK],[USER_NAME] " ); 
		stb.append(" FROM   LOGLAMA  ") ; 
		stb.append(" WHERE  [MESAJ]  LIKE '" + aciklama + "'") ;
		if ( ! t1.equals(""))
			stb.append(" AND [TARIH] Between  '" + t1 + " 00:00:00.0' AND '" + t2 + " 23:59:59.0'") ;
		if ( ! evrak.equals("*"))
			stb.append(" AND [EVRAK]  LIKE '" + evrak + "'");
		if (  ! user.equals("*"))
			stb.append(" AND [USER_NAME]  LIKE '" + user + "'");
		stb.append(" ORDER BY [TARIH] ;") ;
		String sql = stb.toString() ;
		con =  DriverManager.getConnection("jdbc:ucanaccess://" + GLOBAL.LOG_SURUCU + dBILGI.mODULADI_ACCDB  ) ;
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
