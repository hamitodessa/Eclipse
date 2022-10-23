package OBS_C_2025;

import java.sql.SQLException;

public class ADRES_ACCESS {

	private static IADRES  _IAdres;
	
	public ADRES_ACCESS(IADRES _IAdres)
	{
		this._IAdres = _IAdres;
	}
	public static void baglan() throws SQLException
	{
	_IAdres.baglan();
	}
	public void aDR_SIF_L(String kod, String dizin_yeri, String dizin, String fir_adi, String ins, String kull,String sifre) throws ClassNotFoundException, SQLException
	{
		_IAdres.aDR_SIF_L( kod,dizin_yeri,  dizin,  fir_adi,  ins,  kull, sifre);
	}
	public void aDR_SIFIR_S(String server, String ins, String kull, String sifre, String kod, String fir_adi) throws ClassNotFoundException, SQLException
	{
		_IAdres.aDR_SIFIR_S(server, ins, kull, sifre, kod, fir_adi);
	}
}
