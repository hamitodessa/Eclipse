package OBS_C_2025;

import java.sql.SQLException;

public interface IConnection {

	public boolean Server_kontrol_L(Server_Bilgi sbilgi) throws ClassNotFoundException;
	public boolean Server_kontrol_S(Server_Bilgi sbilgi)throws ClassNotFoundException;
	public boolean Dosyakontrol_L(Server_Bilgi sbilgi)throws ClassNotFoundException, SQLException;
	public boolean Dosyakontrol_S(Server_Bilgi sbilgi)throws ClassNotFoundException, SQLException;

}

//public boolean Server_kontrol_L(String inst, String kull, String sifre, String port) throws ClassNotFoundException;
//public boolean Server_kontrol_S(String server, String inst, String kull, String sifre, String port)throws ClassNotFoundException;
//public boolean Dosyakontrol_L(String db, String kull, String sifre, String inst , String port)throws ClassNotFoundException, SQLException;
//public boolean Dosyakontrol_S(String server, String inst, String kull, String sifre, String prog , String port)throws ClassNotFoundException, SQLException;
