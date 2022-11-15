package OBS_C_2025;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface IConnection {

	public boolean Server_kontrol_L(String inst, String kull, String sifre) throws ClassNotFoundException;
	public boolean Server_kontrol_S(String server, String inst, String kull, String sifre)throws ClassNotFoundException;
	public boolean Dosyakontrol_L(String db, String kull, String sifre, String inst)throws ClassNotFoundException, SQLException;
	public boolean Dosyakontrol_S(String server, String inst, String kull, String sifre, String prog)throws ClassNotFoundException, SQLException;
	public ResultSet instance (String server, String user , String pwd) throws SQLException, ClassNotFoundException;

}
