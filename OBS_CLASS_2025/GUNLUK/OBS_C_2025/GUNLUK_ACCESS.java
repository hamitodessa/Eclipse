package OBS_C_2025;

import java.sql.SQLException;

public class GUNLUK_ACCESS {

	private static IGUNLUK  _IGunluk;
	
	public GUNLUK_ACCESS(IGUNLUK _IGunluk) {
		this._IGunluk = _IGunluk;
		
		
	}
	
	public static void baglan() throws SQLException
	{
	_IGunluk.baglan();
	}
	public void gUN_SIFIR_L(String kod, String dizin_yeri, String dizin, String fir_adi, String ins, String kull, String sifre) throws ClassNotFoundException, SQLException
	{
		_IGunluk.gUN_SIFIR_L(kod, dizin_yeri, dizin, fir_adi, ins, kull, sifre);
	}
	public void gUN_SIFIR_S(String server, String ins, String kull, String sifre, String kod, String dizin_yeri,
			String dizin, String fir_adi) throws ClassNotFoundException, SQLException
	{
		_IGunluk.gUN_SIFIR_S(server, ins, kull, sifre, kod, dizin_yeri, dizin, fir_adi);
	}
}
