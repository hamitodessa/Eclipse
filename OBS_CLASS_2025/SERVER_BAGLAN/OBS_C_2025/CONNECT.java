package OBS_C_2025;

import java.sql.SQLException;

public class CONNECT {

	private IConnection _IConnect;

	public CONNECT(IConnection _IConnect) 
	{
		this._IConnect = _IConnect;
	}
	public boolean Server_kontrol_L(Server_Bilgi sbilgi) throws ClassNotFoundException 
	{
		return _IConnect.Server_kontrol_L(sbilgi);
	}
	public boolean Dosya_kontrol_L(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException 
	{
		return _IConnect.Dosyakontrol_L(sbilgi);
	}
	public boolean Server_kontrol_S(Server_Bilgi sbilgi) throws ClassNotFoundException
	{
		return _IConnect.Server_kontrol_S(sbilgi);
	}
	public boolean Dosya_kontrol_S(Server_Bilgi sbilgi) throws ClassNotFoundException, SQLException 
	{
		return _IConnect.Dosyakontrol_S(sbilgi);
	}

}
