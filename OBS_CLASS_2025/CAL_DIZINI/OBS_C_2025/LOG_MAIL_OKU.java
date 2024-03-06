package OBS_C_2025;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@SuppressWarnings("static-access")

public class LOG_MAIL_OKU {
	static  Connection con ;
	private static GLOBAL gLB = new GLOBAL();
	public static  void mail_oku() throws SQLException, ClassNotFoundException

	{
		Class.forName("org.sqlite.JDBC");
		ResultSet	rss = null;
		PreparedStatement stmt = null;
		con = gLB.myConnection();
		String sql = "SELECT DISTINCT E_MAIL FROM LOG_MAIL  WHERE USER_NAME = '" + gLB.KULL_ADI + "'  and   AKTIV = '1' ORDER BY E_MAIL";
		stmt = con.prepareStatement(sql);
		rss = stmt.executeQuery();
		if (!rss.isBeforeFirst())
			gLB.Log_Mail ="";
		else
		{
			rss.next(); 
			gLB.Log_Mail = rss.getString("E_MAIL");
		}
		stmt.close();
		con.close();
		con = null;
	}
}
