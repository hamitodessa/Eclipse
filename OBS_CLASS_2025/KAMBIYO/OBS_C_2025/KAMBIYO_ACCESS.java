package OBS_C_2025;

import java.sql.SQLException;

public class KAMBIYO_ACCESS {
	
	private static IKAMBIYO  _IKambiyo;

	
	public KAMBIYO_ACCESS(IKAMBIYO _IKambiyo) {
		this._IKambiyo = _IKambiyo;
	}

	public static void baglan() throws SQLException
	{
	_IKambiyo.baglan();
	}
	public void kAM_SIFIR_L(String kod, String dizin_yeri, String dizin, String fir_adi, String ins, String kull,
			String sifre) throws ClassNotFoundException, SQLException
	{
		_IKambiyo.kAM_SIFIR_L(kod, dizin_yeri, dizin, fir_adi, ins, kull, sifre);
	}
	public void kAM_SIFIR_S(String server, String ins, String kull, String sifre, String kod, String fir_adi) throws ClassNotFoundException, SQLException
	{
		_IKambiyo.kAM_SIFIR_S(server, ins, kull, sifre, kod, fir_adi);
	}
}
