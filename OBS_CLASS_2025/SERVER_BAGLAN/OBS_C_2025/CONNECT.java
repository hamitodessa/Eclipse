package OBS_C_2025;

import java.sql.SQLException;

public class CONNECT {
	
	private IConnection _IConnect;
	
	public CONNECT(IConnection _IConnect) 
	{
		this._IConnect = _IConnect;
	}
	public boolean Server_kontrol_L(String inst, String kull, String sifre) throws ClassNotFoundException 
	{
		return _IConnect.Server_kontrol_L(inst, kull, sifre);
	}
	public boolean Dosya_kontrol_L(String db, String inst, String kull, String sifre) throws ClassNotFoundException, SQLException 
	{
		return _IConnect.Dosyakontrol_L(db,  inst,  kull,  sifre);
	}
	public boolean Server_kontrol_S(String server,  String inst,String kull, String sifre) throws ClassNotFoundException
	{
		return _IConnect.Server_kontrol_S(server,  inst,  kull,  sifre);
	}
	public boolean Dosya_kontrol_S(String server, String inst, String kull, String sifre, String prog) throws ClassNotFoundException, SQLException 
	{
		return _IConnect.Dosyakontrol_S(server,  inst,  kull,  sifre,prog);
	}
	
}
