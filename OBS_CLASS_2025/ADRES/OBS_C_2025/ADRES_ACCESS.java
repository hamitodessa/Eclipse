package OBS_C_2025;

import java.sql.SQLException;

public class ADRES_ACCESS {

	private static IADRES  _IAdres;
	private static ILOGGER[] _Logger;
	
	public ADRES_ACCESS(IADRES _IAdres, ILOGGER[] _Logger)
	{
		this._IAdres = _IAdres;
		this._Logger = _Logger;
	}
	public static void baglan() throws SQLException
	{
	_IAdres.baglan();
	}
	public void aDR_SIF_L(String kod, String dizin_yeri, String dizin, String fir_adi, String ins, String kull,String sifre
			, String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_IAdres.aDR_SIF_L( kod,dizin_yeri,  dizin,  fir_adi,  ins,  kull, sifre);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(mesaj,evrak, dBILGI);
	}
	public void aDR_SIFIR_S(String server, String ins, String kull, String sifre, String kod, String fir_adi) throws ClassNotFoundException, SQLException
	{
		_IAdres.aDR_SIFIR_S(server, ins, kull, sifre, kod, fir_adi);
	}
}
