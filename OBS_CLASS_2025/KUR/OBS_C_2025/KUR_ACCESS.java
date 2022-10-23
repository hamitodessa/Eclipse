package OBS_C_2025;

import java.sql.SQLException;

public class KUR_ACCESS {
	
	private static IKUR  _IKur;
	public KUR_ACCESS(IKUR _IKur)
	{
		this._IKur = _IKur;
	}
	public static void baglan() throws SQLException
	{
	_IKur.baglan();
	}
	public void kUR_SIFIR_L(String kod, String dizin_yeri, String dizin, String ins, String kull, String sifre) throws ClassNotFoundException, SQLException
	{
		_IKur.kUR_SIFIR_L(kod, dizin_yeri, dizin, ins, kull, sifre);
	}
	public void kUR_SIFIR_S(String server, String ins, String kull, String sifre, String kod) throws ClassNotFoundException, SQLException
	{
		_IKur.kUR_SIFIR_S(server, ins, kull, sifre, kod);
	}

}
