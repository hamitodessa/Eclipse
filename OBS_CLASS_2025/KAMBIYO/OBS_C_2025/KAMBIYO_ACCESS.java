package OBS_C_2025;

import java.sql.ResultSet;
import java.sql.SQLException;

public class KAMBIYO_ACCESS {
	
	private static IKAMBIYO  _IKambiyo;
	private static ILOGGER[] _Logger;

	
	public KAMBIYO_ACCESS(IKAMBIYO _IKambiyo, ILOGGER[] _Logger) {
		this._IKambiyo = _IKambiyo;
		this._Logger = _Logger;
	}

	public static void baglan() throws SQLException
	{
	_IKambiyo.baglan();
	}
	public void kAM_SIFIR_L(String kod, String dizin_yeri, String dizin, String fir_adi, String ins, String kull,
			String sifre, String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_IKambiyo.kAM_SIFIR_L(kod, dizin_yeri, dizin, fir_adi, ins, kull, sifre);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(mesaj,evrak, dBILGI);
	}
	public void kAM_SIFIR_S(String server, String ins, String kull, String sifre, String kod, String fir_adi
			, String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_IKambiyo.kAM_SIFIR_S(server, ins, kull, sifre, kod, fir_adi);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(mesaj,evrak, dBILGI);
	}
	public ResultSet kam_bordno(String cins,String bno,String gircik) throws ClassNotFoundException, SQLException
	{
		return _IKambiyo.kam_bordno(cins, bno, gircik);
	}
}
