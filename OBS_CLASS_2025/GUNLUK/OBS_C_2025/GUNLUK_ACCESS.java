package OBS_C_2025;

import java.sql.SQLException;

public class GUNLUK_ACCESS {

	private static IGUNLUK  _IGunluk;
	private static ILOGGER[] _Logger;
	
	public GUNLUK_ACCESS(IGUNLUK _IGunluk, ILOGGER[] _Logger) {
		this._IGunluk = _IGunluk;
		this._Logger = _Logger;
		
	}
	
	public static void baglan() throws SQLException
	{
	_IGunluk.baglan();
	}
	public void gUN_SIFIR_L(String kod, String dizin_yeri, String dizin, String fir_adi, String ins, String kull, String sifre
			, String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_IGunluk.gUN_SIFIR_L(kod, dizin_yeri, dizin, fir_adi, ins, kull, sifre);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(mesaj,evrak, dBILGI);
	}
	public void gUN_SIFIR_S(String server, String ins, String kull, String sifre, String kod, String dizin_yeri,
			String dizin, String fir_adi, String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_IGunluk.gUN_SIFIR_S(server, ins, kull, sifre, kod, dizin_yeri, dizin, fir_adi);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(mesaj,evrak, dBILGI);
	}
	public String gun_firma_adi() throws ClassNotFoundException, SQLException
	{
		return _IGunluk.gun_firma_adi();
	}
}
