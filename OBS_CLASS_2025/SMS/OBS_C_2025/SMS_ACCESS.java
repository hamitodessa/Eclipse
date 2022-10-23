package OBS_C_2025;

import java.sql.SQLException;

public class SMS_ACCESS {
	private static ISMS  _ISms;

	public SMS_ACCESS(ISMS _ISms) {
		this._ISms = _ISms;
	}

	public static void baglan() throws SQLException
	{
	_ISms.baglan();
	}
	public void sMS_SIFIR_L(String kod, String dizin_yeri, String dizin, String ins, String kull, String sifre) throws ClassNotFoundException, SQLException 
	{
		_ISms.sMS_SIFIR_L(kod, dizin_yeri, dizin, ins, kull, sifre);
	}
	public void sMS_SIFIR_S(String server, String ins, String kull, String sifre, String kod) throws ClassNotFoundException, SQLException
	{
		_ISms.sMS_SIFIR_S(server, ins, kull, sifre, kod);
	}
}
