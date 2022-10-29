package OBS_C_2025;

import java.sql.SQLException;

public class KUR_ACCESS {
	
	private static IKUR  _IKur;
	private static ILOGGER[] _Logger;
	public KUR_ACCESS(IKUR _IKur, ILOGGER[] _Logger)
	{
		this._IKur = _IKur;
		this._Logger = _Logger;
	}
	public static void baglan() throws SQLException
	{
	_IKur.baglan();
	}
	public void kUR_SIFIR_L(String kod, String dizin_yeri, String dizin, String ins, String kull, String sifre
			, String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_IKur.kUR_SIFIR_L(kod, dizin_yeri, dizin, ins, kull, sifre);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(mesaj,evrak, dBILGI);
	}
	public void kUR_SIFIR_S(String server, String ins, String kull, String sifre, String kod
			, String mesaj,String evrak, DIZIN_BILGILERI dBILGI) throws ClassNotFoundException, SQLException
	{
		_IKur.kUR_SIFIR_S(server, ins, kull, sifre, kod);
		 for ( ILOGGER  _Logger : _Logger )
			  	_Logger.Logla(mesaj,evrak, dBILGI);

	}

}
