package OBS_C_2025;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

public class SQL_BACKUP {
	static  Connection con ;
	static boolean result = false;
	private GLOBAL gLB = new GLOBAL();

	@SuppressWarnings("static-access")
	public void server_kayit_sil(String eismi) throws SQLException, ClassNotFoundException
	{
		Class.forName("org.sqlite.JDBC");
		PreparedStatement stmt = null;
		con =  gLB.myBackupConnection();
		String sql = "DELETE FROM SERVER  WHERE EMIR_ISMI =  ? ";
		stmt = con.prepareStatement(sql);
		stmt.setString(1,eismi);
		stmt.executeUpdate();
		stmt.close();
		con.close();
	}
	@SuppressWarnings("static-access")
	public void server_ismi_kayit(String eismi,String hangisql, String ins, boolean wi, boolean ser, String kull, String sif) throws ClassNotFoundException, SQLException, InvalidKeyException, NoSuchAlgorithmException, NoSuchPaddingException, UnsupportedEncodingException, IllegalBlockSizeException, BadPaddingException
	{
		Class.forName("org.sqlite.JDBC");
		PreparedStatement stmt = null;
		con = gLB.myBackupConnection();
		String sql = "INSERT INTO SERVER (EMIR_ISMI,HANGI_SQL,INSTANCE,WIN,SERV,KULLANICI,SIFRE) ";
		sql += "VALUES (?,?,?,?,?,?,?)";
		{
			stmt = con.prepareStatement(sql);
			stmt.setString(1, eismi);
			stmt.setString(2,  hangisql);
			stmt.setString(3, ins);
			stmt.setBoolean(4, wi);
			stmt.setBoolean(5, ser);
			stmt.setString(6, kull);
			stmt.setString(7, ENCRYPT_DECRYPT_STRING.eNCRYPT_manual(sif));
		}
		stmt.executeUpdate();
		stmt.close();
		con.close();
	}
}
