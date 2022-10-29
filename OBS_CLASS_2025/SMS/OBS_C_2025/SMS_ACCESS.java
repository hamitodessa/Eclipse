package OBS_C_2025;

import java.sql.SQLException;

public class SMS_ACCESS {
	private static ISMS  _ISms;
	private static ILOGGER[] _Logger;

	public SMS_ACCESS(ISMS _ISms, ILOGGER[] _Logger) {
		this._ISms = _ISms;
		this._Logger = _Logger;
	}

	public static void baglan() throws SQLException
	{
	_ISms.baglan();
	}
	public void sMS_SIFIR_L(String kod, String dizin_yeri, String dizin, String ins, String kull, String sifre
			, String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException 
	{
		_ISms.sMS_SIFIR_L(kod, dizin_yeri, dizin, ins, kull, sifre);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(mesaj,evrak, dBILGI);
	}
	public void sMS_SIFIR_S(String server, String ins, String kull, String sifre, String kod
			, String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_ISms.sMS_SIFIR_S(server, ins, kull, sifre, kod);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(mesaj,evrak, dBILGI);
	}
}
